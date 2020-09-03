package com.example.myshopping.model;

import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;

import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.myshopping.app.HttpCallBack;
import com.example.myshopping.app.SpUtil;
import com.example.myshopping.constacts.AddCartConstacts;
import com.example.myshopping.requestbean.AddCartPrams;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;


public class AddCartModelIml implements AddCartConstacts.AddCartModel{
    @Override
    public void addCartM(AddCartPrams addCartPrams, final ModelBaseCallback<String> modelBaseCallback, LifecycleProvider lifecycleProvider) {
        HashMap<String, Object> heardmap = new HashMap<>();
        heardmap.put("token", SpUtil.getParam("token",0));
        new HttpClient.Builder().setJsonBody(JsonUtils.classToJson(addCartPrams),true)
                .setApiUrl("cart/add")
                .setHeadres(heardmap)
                .post()
                .setLifecycleProvider(lifecycleProvider)
                .build().request(new HttpCallBack<Integer>() {
            @Override
            public void onError(String message, int code) {
                modelBaseCallback.OnError(message,code);
            }

            @Override
            public void cancle() {

            }
            @Override
            public void onSuccess(Integer integer) {
                modelBaseCallback.onSucess(String.valueOf(integer));
            }
            @Override
            public Integer convert(JsonElement result) {
                return result.getAsNumber().intValue();
            }
        });
    }
}
