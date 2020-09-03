package com.example.myshopping.model;



//M层和p层 都要写  别忘了


import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.myshopping.app.HttpCallBack;
import com.example.myshopping.bean.CategoryItemList.CateItemListBean;
import com.example.myshopping.constacts.CateItemConstacts;
import com.example.myshopping.requestbean.CategoryHot;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class CateItemModelIml implements CateItemConstacts.CateItemModel {
    @Override
    public void getCateItem(CategoryHot categoryHot, ModelBaseCallback modelBaseCallback, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setLifecycleProvider(lifecycleProvider)
                .setApiUrl("goods/getGoodsList")
                .setJsonBody(JsonUtils.classToJson(categoryHot),true)
                .post()
                .build().request(new HttpCallBack<List<CateItemListBean>>() {

            @Override
            public void onSuccess(List<CateItemListBean> cateItemListBeans) {
                modelBaseCallback.onSucess(cateItemListBeans);
            }

            @Override
            public List<CateItemListBean>  convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result,CateItemListBean.class);
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
}
