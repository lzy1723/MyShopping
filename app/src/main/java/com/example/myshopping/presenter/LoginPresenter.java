package com.example.myshopping.presenter;


import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.myshopping.bean.LoginBean;
import com.example.myshopping.constacts.LoginContract;
import com.example.myshopping.model.LoginModel;
import com.example.myshopping.requestbean.LoginParmesan;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void getDataLogin(LoginParmesan loginParmesan) {
        LoginModel loginModel = ModelFractory.creatModle(LoginModel.class);
        loginModel.getDataLogin(loginParmesan,this,getLifeCycle());
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        mView.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        mView.onError(error,0);
    }

    @Override
    public void onCancal() {
        mView.onCancle();
    }
}
