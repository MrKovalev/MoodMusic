package com.titanium.moodmusic.shared.error;

public class Message {

    private final String errorText;

    public Message(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
