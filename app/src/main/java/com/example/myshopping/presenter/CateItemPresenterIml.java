package com.example.myshopping.presenter;

import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.bean.CategoryItemList.CateItemListBean;
import com.example.myshopping.constacts.CateItemConstacts;
import com.example.myshopping.model.CateItemModelIml;
import com.example.myshopping.requestbean.CateDetailPrams;
import com.example.myshopping.requestbean.CategoryHot;
import com.google.gson.JsonElement;

import java.util.List;

public class CateItemPresenterIml extends BasePresenter<CateItemConstacts.CateItemView> implements CateItemConstacts.CateItemPresenter {

    private final CateItemModelIml cateItemModelIml;

    public CateItemPresenterIml(){
        cateItemModelIml = ModelFractory.creatModle(CateItemModelIml.class);
    }

    @Override
    public void getCateItem(CategoryHot categoryHot) {
        cateItemModelIml.getCateItem(categoryHot, new ModelBaseCallback<List<CateItemListBean>>() {
            @Override
            public void onSucess(List<CateItemListBean> cateItemListBeans) {
                mView.showCateCom(cateItemListBeans);
            }

            @Override
            public void OnError(String msg, int code) {
                mView.onError(msg, code);
            }

            @Override
            public void onCancle() {
                mView.onCancle();
            }
        },getLifeCycle());
    }
}
