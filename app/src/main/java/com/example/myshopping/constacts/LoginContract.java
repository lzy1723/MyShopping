package com.example.myshopping.constacts;

import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.bean.LoginBean;
import com.example.myshopping.requestbean.LoginParmesan;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface LoginContract {
    interface Model extends BaseModel {
        void getDataLogin(LoginParmesan loginParmesan, LoginCallBack loginCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccess(LoginBean loginBean);
    }

    interface Presenter extends LoginCallBack{
        void getDataLogin(LoginParmesan loginParmesan);
    }

    interface LoginCallBack{
        void onSuccess(LoginBean loginBean);
        void onFail(String error);
        void onCancal();
    }
}
