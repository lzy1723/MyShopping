package com.example.myshopping.constacts;


import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.model.ModelBaseCallback;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.bean.CategoryList;
import com.example.myshopping.requestbean.CategoryPrams;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface CategoryConstacts {

    interface CategoryView extends BaseView {
        void showCategoryTab(List<String> tabs);
        void showCategoryList(List<CategoryList> categoryLists);

    }

    interface CategoryPresenter {
        void getCategoryTab(CategoryPrams categoryPrams);
        void getCategoryList(CategoryPrams categoryPrams);

    }

    interface CategoryModel extends BaseModel {
        void getCategoryTab(CategoryPrams categoryPrams, ModelBaseCallback<List<String>> modelBaseCallback, LifecycleProvider lifecycleProvider);
        void getCategoryList(CategoryPrams categoryPrams, ModelBaseCallback<List<CategoryList>> modelBaseCallback, LifecycleProvider lifecycleProvider);
        void onError(String msg, int code);
    }
}
