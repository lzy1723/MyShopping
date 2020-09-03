package com.example.myshopping.model;

import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;

import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.myshopping.app.HttpCallBack;
import com.example.myshopping.bean.CategoryList;
import com.example.myshopping.bean.CategoryTab;
import com.example.myshopping.constacts.CategoryConstacts;
import com.example.myshopping.requestbean.CategoryPrams;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;


public class CategoryModelIml implements CategoryConstacts.CategoryModel{
    @Override
    public void getCategoryTab(final CategoryPrams categoryPrams, final ModelBaseCallback<List<String>> modelBaseCallback, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder().setApiUrl("category/getCategory")
                .setLifecycleProvider(lifecycleProvider)
                .setJsonBody(JsonUtils.classToJson(categoryPrams),true)
                .post()
                .build().request(new HttpCallBack<List<String>>() {
            @Override
            public void onError(String message, int code) {
                modelBaseCallback.OnError(message,code);
            }

            @Override
            public void cancle() {
                modelBaseCallback.onCancle();
            }

            @Override
            public void onSuccess(List<String> list) {
                modelBaseCallback.onSucess(list);
            }
            @Override
            public List<String> convert(JsonElement result) {
                List<CategoryTab> categoryTabs = JsonUtils.jsonToClassList(result, CategoryTab.class);
                List<String> tabs=new ArrayList<>();
                for (CategoryTab categoryTab : categoryTabs) {
                    tabs.add(categoryTab.getCategoryName());
                }
                return tabs;
            }
        });
    }

    @Override
    public void getCategoryList(CategoryPrams categoryPrams, ModelBaseCallback<List<CategoryList>> modelBaseCallback, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder().setApiUrl("category/getCategory")
                .setLifecycleProvider(lifecycleProvider)
                .setJsonBody(JsonUtils.classToJson(categoryPrams),true)
                .post()
                .build().request(new HttpCallBack<List<CategoryList>>() {
            @Override
            public void onSuccess(List<CategoryList> categoryLists) {
                modelBaseCallback.onSucess(categoryLists);
            }

            @Override
            public List<CategoryList> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result,CategoryList.class);
            }

            @Override
            public void onError(String message, int code) {
                modelBaseCallback.OnError(message,code);
            }

            @Override
            public void cancle() {
                modelBaseCallback.onCancle();
            }
        });
    }


    @Override
    public void onError(String msg, int code) {

    }
}
