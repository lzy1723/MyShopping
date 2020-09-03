package com.example.myshopping.constacts;

import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.bean.CategoryItemList.CateItemListBean;
import com.example.myshopping.requestbean.CateDetailPrams;
import com.example.myshopping.requestbean.CategoryHot;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface CateItemConstacts {

    interface CateItemView extends BaseView{
        void showCateCom(List<CateItemListBean> cateItemListBeans);

    }

    interface CateItemModel extends BaseModel{
        void getCateItem(CategoryHot categoryHot, ModelBaseCallback modelBaseCallback, LifecycleProvider lifecycleProvider);

    }

    interface CateItemPresenter{
        void getCateItem(CategoryHot categoryHot);
    }


}
