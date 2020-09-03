package com.example.myshopping.app;

import android.util.Log;

import com.example.httplibrary.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        String header = proceed.header("Set-Cookie");
        LogUtils.e(header);
        if (header!=null){

        }
        return proceed;
    }
}
