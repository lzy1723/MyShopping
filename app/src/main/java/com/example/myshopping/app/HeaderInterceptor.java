package com.example.myshopping.app;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        builder.addHeader("Time-Stamp", time_stamp);
//        builder.addHeader("Device-Key", device_key);
//        builder.addHeader("Version", getVersion());
//        builder.addHeader("Authorization", ChannelUtil.getAPIKEY() + ":" + "Android");
//        builder.addHeader("channel", ChannelUtil.getChannel());
        builder.removeHeader("User-Agent");
        builder.addHeader("User-Agent", "apps/api");
        Request build = builder.build();
        return chain.proceed(build);
    }
}
