package com.example.httplibrary.disposble;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.Disposable;

public class RequestManagerlml implements RequestManager{

    private static volatile RequestManagerlml instance;
    private static Map<String,Disposable> mMap = new HashMap<>();
    private RequestManagerlml(){}

    public static RequestManagerlml getInstance(){
        if (instance == null){
            synchronized (RequestManagerlml.class){
                if (instance == null){
                    instance = new RequestManagerlml();
                }
            }
        }

        return instance;
    }

    @Override
    public void add(String tag, Disposable disposable) {
        if (!TextUtils.isEmpty(tag)){
            mMap.put(tag,disposable);
        }
    }

    @Override
    public void remove(String tag) {
        if (!TextUtils.isEmpty(tag)){
            if (mMap.isEmpty()){
                return;
            }
            mMap.remove(tag);
        }
    }

    @Override
    public void cancle(String tag) {
        if (!TextUtils.isEmpty(tag)){
            if (!mMap.isEmpty()){
                if (mMap.get(tag)!=null){
                    Disposable disposable = mMap.get(tag);
                    if (!disposable.isDisposed()){
                        disposable.dispose();
                    }
                    mMap.remove(tag);
                }
            }
        }
    }

    @Override
    public void cancleAll() {
        Disposable disposable = null;
        if (!mMap.isEmpty()){
            Set<String> keys = mMap.keySet();
            for (String key : keys){
                if (mMap.get(key)!=null){
                    disposable = mMap.get(key);
                }
                if (disposable!=null&&!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
        }
        mMap.clear();
    }

    public boolean isDispose(String tag){
        if (!mMap.isEmpty() && mMap.get(tag)!= null){
            return mMap.get(tag).isDisposed();
        }
        return false;
    }
}
