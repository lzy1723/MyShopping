package com.example.mvplibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.mvplibrary.presenter.BasePresenter;
import com.example.mvplibrary.view.BaseView;

public abstract class BaselazyFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseFragment {
    boolean mIsPrepare = false;        //初始化视图
    boolean mIsVisible = false;        //不可见
    boolean mIsFirstLoad = true;    //第一次加载
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mIsPrepare = true;
        lazyLoad();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisible = true;
            lazyLoad();
        } else {
            mIsVisible = false;
        }
    }

    protected abstract void lazyinitData();

    private void lazyLoad() {
        if (mIsPrepare && mIsVisible && mIsFirstLoad) {
            lazyinitData();
            mIsFirstLoad=false;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsPrepare = false;
        mIsVisible = false;
        mIsFirstLoad = true;
    }

}
