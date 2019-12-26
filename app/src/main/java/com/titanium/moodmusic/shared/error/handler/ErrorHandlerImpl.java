package com.titanium.moodmusic.shared.error.handler;

import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.error.exceptions.CommonException;
import com.titanium.moodmusic.shared.error.exceptions.NetworkException;

import javax.inject.Inject;

public class ErrorHandlerImpl implements ErrorHandler {

    @Inject
    public ErrorHandlerImpl() {
    }

    @Override
    public Message processError(Throwable throwable) {
        if (throwable instanceof NetworkException
                || throwable instanceof CommonException) {
            return new Message(throwable.getMessage());
        }

        return new Message("Неизвестная ошибка");
    }
}
