package com.zhuoyue.researchManement.enums;

public enum CustomHttpStatus {

    OK(200, ""),
    BAD_REQUEST(400, "");

    private final int value;
    private final String reasonPhrase;

    CustomHttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
