package com.example.myshopping.constacts;


import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.requestbean.AddCartPrams;
import com.trello.rxlifecycle2.LifecycleProvider;


public interface AddCartConstacts {

    interface AddCartView extends BaseView {
        void showCount(String count);
    }

    interface AddCartPresenter {
        void addCartP(AddCartPrams addCartPrams);
    }
    interface AddCartModel extends BaseModel {
        void addCartM(AddCartPrams addCartPrams, ModelBaseCallback<String> modelBaseCallback, LifecycleProvider lifecycleProvider);
    }

}
