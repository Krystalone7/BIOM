package com.biom;

import com.biom.domain.ChatMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnitPlatform.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class tst {
     @Autowired
    MockMvc mockMvc;

    @Value("${local.server.port}")
    private int port;

    private static final String SUBSCRIBE_ENDPOINT = "/user/queue/messages";
    private static final String SEND_URL="/app/sendToUser";

    private String URL;


    private CompletableFuture<ChatMessage> completableFuture;

    @BeforeAll
    public void setup() {
        completableFuture = new CompletableFuture<>();
        URL = "ws://localhost:" + port + "/ws";
    }

    @Test
    public void testPrincipal() throws Exception{
        String token=getToken();
        System.out.println( mockMvc.perform(get("/user/get-all"+"?CookieToken="+token)).andReturn());
    }

    @Test
    public void testCreateGameEndpoint() throws Exception {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        String token=getToken();
        WebSocketHttpHeaders header = new WebSocketHttpHeaders();
        header.add("Token",token);//добавление заголовка с токеном
        StompSession stompSession= stompClient.connect("ws://localhost:" + port + "/ws",
                header, new StompHeaders(), new StompSessionHandlerAdapter() {}).get(2,SECONDS);
        stompSession.subscribe(SUBSCRIBE_ENDPOINT,new CreateGameStompFrameHandler());
        stompSession.send(SEND_URL,
                ChatMessage.builder().message("Pdfv").recipient("ivan").sender("danil").build());//отправление сообщения

        ChatMessage chatMessage = completableFuture.get(5,SECONDS);

        System.out.println(chatMessage.getMessage());

        assertNotNull(chatMessage);
    }

    private String getToken() throws  Exception{
        MvcResult result = mockMvc.perform( post("/auth")
                        .queryParam("username","danil")
                        .queryParam("password","danil"))
                .andReturn();
        //  .andDo(print())
        //.andExpect(content().string(containsString("Вы успешно вошли в систему!")));
        String token = result.getResponse().getCookie("CookieToken").getValue();
        return token;
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    private class CreateGameStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return ChatMessage.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((ChatMessage) o);
        }
    }
}

