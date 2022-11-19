package com.biom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    private String recipient;

    private String sender;

    private String message;


    public ChatMessage(@JsonProperty("recipient")String recipient,
                       @JsonProperty("sender") String sender,
                       @JsonProperty("message") String message) {
        this.recipient = recipient;
        this.sender = sender;
        this.message = message;
    }
}
