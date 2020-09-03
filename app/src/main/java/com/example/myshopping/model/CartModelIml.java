package com.example.myshopping.model;

import android.util.Log;

import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;

import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.myshopping.app.HttpCallBack;
import com.example.myshopping.app.SpUtil;
import com.example.myshopping.bean.CartGoods;
import com.example.myshopping.constacts.CartConstacts;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;

public class CartModelIml implements CartConstacts.CartModel{
    @Override
    public void getCartListM(final ModelBaseCallback<List<CartGoods>> modelBaseCallback, LifecycleProvider lifecycleProvider) {
        HashMap<String, Object> tokenmap = new HashMap<>();
        tokenmap.put("token", SpUtil.getParam("token",0));
        Log.e("TAG===",SpUtil.getParam("token",0)+"");
        new HttpClient.Builder()
                .setJsonBody("{}",true)
                .setHeadres(tokenmap)
                .setApiUrl("cart/getList")
                .post().build().request(new HttpCallBack<List<CartGoods>>() {
            @Override
            public void onError(String message, int code) {
                Log.e("TAG===CartModelIml===", "onError: "+message );
             modelBaseCallback.OnError(message,code);
            }

            @Override
            public void cancle() {

            }

            @Override
            public void onSuccess(List<CartGoods> goods) {
                modelBaseCallback.onSucess(goods);
            }

            @Override
            public List<CartGoods> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result,CartGoods.class);
            }
        });
    }
}
