package com.example.myshopping.constacts;

import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.presenter.DitailPresenter;
import com.example.myshopping.requestbean.CateDetailPrams;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface DitailContract {
    interface DatailView extends BaseView {
        void showDatailView(CateDetailList cateDetailLists);
    }

    interface DatailModel extends BaseModel {
        void getDatail(CateDetailPrams cateDetailPrams, ModelBaseCallback modelBaseCallback,LifecycleProvider lifecycleProvider);
        void onErrer();
    }

    interface DatailPresenter {
        void getDatail(CateDetailPrams cateDetailPrams);
    }

}
