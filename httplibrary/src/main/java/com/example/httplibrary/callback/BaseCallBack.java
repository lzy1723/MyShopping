package com.example.httplibrary.callback;

import android.util.Log;

import com.example.httplibrary.exceptiopn.ExceptionEngine;
import com.example.httplibrary.utils.LogUtils;
import com.google.gson.JsonElement;

public abstract class BaseCallBack<T> extends BaseObserver {

    boolean callSuccess = true;

    @Override
    public void onNext(Object t) {
        super.onNext(t);
        Log.e("TAG====","1111:"+t.toString());
        T parse = parse((String)t);
        if (parse!=null){
            Log.e("TAG====","2222:"+parse.toString());
        }else {
            Log.e("TAG====","我为空");
        }
        if (callSuccess && isCodeSuccess()){
            onSuccess(parse);
        }
    }
    protected abstract T onConvert(String result);
    private T parse(String result) {
        T data = null;
        try {
            data = onConvert(result);
            callSuccess =true;
        }catch (Exception e){
            e.printStackTrace();
            callSuccess = false;
            onError("数据解析错误", ExceptionEngine.ANALYTIC_SERVER_DATA_ERROR);
        }
        return data;
    }

    public abstract void onSuccess(T t);

    public abstract boolean isCodeSuccess();

    public abstract T convert(JsonElement result);
}
