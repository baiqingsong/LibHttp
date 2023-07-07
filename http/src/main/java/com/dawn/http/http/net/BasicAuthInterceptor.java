package com.dawn.http.http.net;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class BasicAuthInterceptor implements Interceptor {
    private String AUTH_UM = "";
    private String AUTH_PW = "";
    public BasicAuthInterceptor(String AUTH_UM, String AUTH_PW) {
        super();
        this.AUTH_UM = TextUtils.isEmpty(AUTH_UM) ? "" : AUTH_UM;
        this.AUTH_PW = TextUtils.isEmpty(AUTH_PW) ? "" : AUTH_PW;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", Credentials.basic(AUTH_UM, AUTH_PW)).build();
        return chain.proceed(authenticatedRequest);
    }
}