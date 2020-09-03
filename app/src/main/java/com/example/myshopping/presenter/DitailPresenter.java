package com.example.myshopping.presenter;


import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.constacts.DitailContract;
import com.example.myshopping.model.DitailModel;
import com.example.myshopping.requestbean.CateDetailPrams;

import java.util.List;

public class DitailPresenter extends BasePresenter<DitailContract.DatailView> implements DitailContract.DatailPresenter {

    private final DitailModel ditailModel;

    public DitailPresenter() {
        ditailModel = ModelFractory.creatModle(DitailModel.class);
    }

    @Override
    public void getDatail(CateDetailPrams cateDetailPrams) {
        ditailModel.getDatail(cateDetailPrams, new ModelBaseCallback<CateDetailList>() {

            @Override
            public void onSucess(CateDetailList cateDetailList) {
                mView.showDatailView(cateDetailList);
            }

            @Override
            public void OnError(String msg, int code) {
                mView.onError(msg, code);
            }

            @Override
            public void onCancle() {
                mView.onCancle();
            }
        },getLifeCycle());
    }
}
