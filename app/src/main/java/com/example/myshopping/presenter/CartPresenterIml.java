package com.example.myshopping.presenter;


import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.myshopping.bean.CartGoods;
import com.example.myshopping.constacts.CartConstacts;
import com.example.myshopping.model.CartModelIml;

import java.util.List;


public class CartPresenterIml extends BasePresenter<CartConstacts.CartView> implements CartConstacts.CartPresenter, ModelBaseCallback<List<CartGoods>> {
    @Override
    public void getCartListP() {
        ModelFractory.creatModle(CartModelIml.class).getCartListM(this,getLifeCycle());
    }

    @Override
    public void onSucess(List<CartGoods> goods) {
        mView.showCartGoodList(goods);
    }

    @Override
    public void OnError(String msg, int code) {
        mView.onError(msg,code);
    }

    @Override
    public void onCancle() {

    }
}
