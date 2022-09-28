package com.biom.pojo;

public class ResponseDto<T> {

    private final String code;
    private final String description;
    private final T body;

    public ResponseDto(String code, String description, T body) {
        this.code = code;
        this.description = description;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public T getBody() {
        return body;
    }
}
