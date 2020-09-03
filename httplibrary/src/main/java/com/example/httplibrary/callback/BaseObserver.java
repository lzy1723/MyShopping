package com.example.httplibrary.callback;

import android.text.TextUtils;

import com.example.httplibrary.HttpGlobalConfig;
import com.example.httplibrary.disposble.RequestManagerlml;
import com.example.httplibrary.exceptiopn.ApiException;
import com.example.httplibrary.exceptiopn.ExceptionEngine;
import com.example.httplibrary.utils.ThreadUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    String tag;
    @Override
    public void onSubscribe(Disposable d) {
        if (!TextUtils.isEmpty(tag)){
            RequestManagerlml.getInstance().add(tag,d);
        }
    }

    @Override
    public void onNext(T t) {
        if (!TextUtils.isEmpty(tag)){
            RequestManagerlml.getInstance().remove(tag);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException){
            ApiException apiException = (ApiException) e;
            onError(apiException.getMsg(),apiException.getCode());
        }else {
            onError("未知异常", ExceptionEngine.UN_KNOWN_ERROR);
        }
        if (!TextUtils.isEmpty(tag)){
            RequestManagerlml.getInstance().remove(tag);
        }
    }

    @Override
    public void onComplete() {
        if (!RequestManagerlml.getInstance().isDispose(tag)){
            RequestManagerlml.getInstance().cancle(tag);
        }
    }

    public abstract void onError(String message, int code);
    public abstract void cancle();

    public void canclend(){
        if (!ThreadUtils.isMainThread()){
            HttpGlobalConfig.getInsance().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    cancle();
                }
            });
        }
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
