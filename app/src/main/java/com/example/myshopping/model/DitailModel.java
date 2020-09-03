package com.example.myshopping.model;

import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.myshopping.app.HttpCallBack;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.constacts.DitailContract;
import com.example.myshopping.presenter.DitailPresenter;
import com.example.myshopping.requestbean.CateDetailPrams;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class DitailModel implements DitailContract.DatailModel {


    @Override
    public void getDatail(CateDetailPrams cateDetailPrams, ModelBaseCallback modelBaseCallback, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setApiUrl("goods/getGoodsDetail")
                .post()
                .setLifecycleProvider(lifecycleProvider)
                .setJsonBody(JsonUtils.classToJson(cateDetailPrams),true)
                .build().request(new HttpCallBack<CateDetailList>() {
            @Override
            public void onSuccess(CateDetailList cateDetailList) {
                modelBaseCallback.onSucess(cateDetailList);
            }

            @Override
            public CateDetailList convert(JsonElement result) {
                return JsonUtils.jsonToClass(result,CateDetailList.class);
            }

            @Override
            public void onError(String message, int code) {
                modelBaseCallback.OnError(message, code);
            }

            @Override
            public void cancle() {
                modelBaseCallback.onCancle();
            }
        });
    }

    @Override
    public void onErrer() {

    }
}
