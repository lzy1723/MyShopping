package com.example.myshopping.app;

import android.util.Log;

import com.example.httplibrary.callback.BaseCallBack;
import com.example.httplibrary.utils.JsonUtils;
import com.example.myshopping.http.Response;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


public abstract class HttpCallBack<T> extends BaseCallBack<T> {
    Response response;
    @Override
    protected T onConvert(String result) {
        T t = null;
        response = JsonUtils.jsonToClass(result, Response.class);
        JsonElement data = response.getData();
        int status = response.getStatus();
        String message = response.getMessage();
        switch (status) {
            case -1:
                onError(message,status);
                break;
            default:
                if (isCodeSuccess()) {
                    t = convert(data);
                }
                break;
        }
        return t;
    }
    @Override
    public boolean isCodeSuccess() {
        if (response != null) {
            return response.getStatus() == 0;
        }
        return false;
    }

}
