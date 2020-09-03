package com.example.myshopping.model;


import android.util.Log;

import com.example.httplibrary.callback.BaseCallBack;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.myshopping.app.HttpCallBack;
import com.example.myshopping.app.SpUtil;
import com.example.myshopping.bean.LoginBean;
import com.example.myshopping.constacts.LoginContract;
import com.example.myshopping.requestbean.LoginParmesan;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class LoginModel implements LoginContract.Model {

    private int status;

    @Override
    public void getDataLogin(LoginParmesan loginParmesan, final LoginContract.LoginCallBack loginCallBack, LifecycleProvider lifecycleProvider) {

        new HttpClient.Builder().setApiUrl("userCenter/login")
                .setJsonBody(JsonUtils.classToJson(loginParmesan),true)
                .post().build()
                .request(new BaseCallBack<LoginBean>() {
                    @Override
                    protected LoginBean onConvert(String result) {
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            status = jsonObject.getInt("status");
                            if(status ==0){
                                JSONObject data = jsonObject.getJSONObject("data");
                                LoginBean userBean = JsonUtils.jsonToClass(data.toString(), LoginBean.class);

                                return userBean;
                            }else if(status ==-1){
                                onError(jsonObject.getString("message"), status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public boolean isCodeSuccess() {
                        return status==0;
                    }

                    @Override
                    public void onSuccess(LoginBean userBean) {
                        Log.e("TAG===LoginModel==", "onSuccess: "+userBean.toString() );
                        loginCallBack.onSuccess(userBean);
                    }

                    @Override
                    public LoginBean convert(JsonElement result) {
                        return null;
                    }
                    @Override
                    public void onError(String message, int code) {
                        Log.e("TAG===LoginModel==", "onError: "+message );
                        loginCallBack.onFail(message+code);
                    }

                    @Override
                    public void cancle() {
                        loginCallBack.onCancal();
                    }
                });
    }
}
