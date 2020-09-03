package com.example.myshopping.constacts;


import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.bean.CartGoods;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;


public interface CartConstacts {
    interface CartView extends BaseView {
        void showCartGoodList(List<CartGoods> goods);
    }
    interface CartModel extends BaseModel {
        void getCartListM(ModelBaseCallback<List<CartGoods>> modelBaseCallback, LifecycleProvider lifecycleProvider);
    }
    interface CartPresenter{
        void getCartListP();
    }
}
