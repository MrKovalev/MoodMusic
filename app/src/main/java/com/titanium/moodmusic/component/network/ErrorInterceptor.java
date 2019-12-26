package com.titanium.moodmusic.component.network;

import com.titanium.moodmusic.shared.error.exceptions.CommonException;
import com.titanium.moodmusic.shared.error.exceptions.NetworkException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {

    private static final String message = "Операция не может быть выполнена";
    private static final String messageNoInternet = "Отсутствует соединение с интернетом";

    @Override
    public Response intercept(Chain chain) throws IOException {

        try {
            Request request = chain.request();
            Response response = chain.proceed(request);

            if (response.code() == HttpURLConnection.HTTP_OK) {
                return response;
            } else {
                throw new CommonException(message);
            }
        } catch (IOException exception) {
            throw parseException(exception);
        }
    }

    private RuntimeException parseException(IOException exception) {
        if (exception instanceof UnknownHostException
                || exception instanceof ConnectException
                || exception instanceof NoRouteToHostException) {
            return new NetworkException(messageNoInternet);
        } else {
            return new CommonException(message);
        }
    }
}
