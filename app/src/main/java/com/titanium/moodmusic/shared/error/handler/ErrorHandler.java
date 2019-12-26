package com.titanium.moodmusic.shared.error.handler;

import com.titanium.moodmusic.shared.error.Message;

public interface ErrorHandler {

    Message processError(Throwable throwable);
}