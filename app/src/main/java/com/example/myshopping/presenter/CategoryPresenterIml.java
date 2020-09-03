package com.example.myshopping.presenter;


import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.myshopping.bean.CategoryList;
import com.example.myshopping.constacts.CategoryConstacts;
import com.example.myshopping.model.CategoryModelIml;
import com.example.myshopping.requestbean.CategoryPrams;

import java.util.List;


public class CategoryPresenterIml extends BasePresenter<CategoryConstacts.CategoryView> implements CategoryConstacts.CategoryPresenter, ModelBaseCallback<List<String>> {

    private final CategoryModelIml categoryModelIml;

    public CategoryPresenterIml(){
        categoryModelIml = ModelFractory.creatModle(CategoryModelIml.class);
    }

    @Override
    public void getCategoryTab(CategoryPrams categoryPrams) {
        categoryModelIml.getCategoryTab(categoryPrams,this,getLifeCycle());
    }

    @Override
    public void getCategoryList(CategoryPrams categoryPrams) {
        categoryModelIml.getCategoryList(categoryPrams, new ModelBaseCallback<List<CategoryList>>() {
            @Override
            public void onSucess(List<CategoryList> categoryLists) {
                mView.showCategoryList(categoryLists);
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



    @Override
    public void onSucess(List<String> strings) {
        if(mView!=null){
            mView.showCategoryTab(strings);
        }
    }

    @Override
    public void OnError(String msg, int code) {
        mView.onError(msg,code);
    }

    @Override
    public void onCancle() {
          mView.onCancle();
    }


}
