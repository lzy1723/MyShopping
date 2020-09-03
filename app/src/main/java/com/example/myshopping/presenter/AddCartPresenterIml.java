package com.example.myshopping.presenter;


import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.myshopping.constacts.AddCartConstacts;
import com.example.myshopping.model.AddCartModelIml;
import com.example.myshopping.requestbean.AddCartPrams;


public class AddCartPresenterIml extends BasePresenter<AddCartConstacts.AddCartView> implements AddCartConstacts.AddCartPresenter, ModelBaseCallback<String> {
    @Override
    public void addCartP(AddCartPrams addCartPrams) {
        ModelFractory.creatModle(AddCartModelIml.class).addCartM(addCartPrams,this,getLifeCycle());
    }

    @Override
    public void onSucess(String s) {
        mView.showCount(s);
    }

    @Override
    public void OnError(String msg, int code) {
        mView.onError(msg,code);
    }

    @Override
    public void onCancle() {

    }
}
