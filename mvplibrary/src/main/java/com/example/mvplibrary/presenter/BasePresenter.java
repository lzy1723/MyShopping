package com.example.mvplibrary.presenter;

import com.example.mvplibrary.view.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends BaseView> {
    public V mView;
    private WeakReference<V> weakReference;
    //绑定
    public void attacthView(V v){
        weakReference =new WeakReference<V>(v);
        mView=weakReference.get();
    }
    //返回视图层对象
    public LifecycleProvider getLifeCycle(){
        return (LifecycleProvider) mView;
    }
    //解绑
    public void destoryView(){
        if(weakReference!=null){
            weakReference.clear();
        }
    }
}
