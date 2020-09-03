package com.example.myshopping.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.myshopping.Adapter.ClassifyAdapter;
import com.example.myshopping.Adapter.ClassifyHotAdapter;
import com.example.myshopping.ClassifyItemActivity;
import com.example.myshopping.R;
import com.example.myshopping.bean.CategoryList;
import com.example.myshopping.constacts.CategoryConstacts;
import com.example.myshopping.presenter.CategoryPresenterIml;
import com.example.myshopping.requestbean.CategoryHot;
import com.example.myshopping.requestbean.CategoryPrams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseMvpFragment<CategoryConstacts.CategoryView, CategoryPresenterIml> implements CategoryConstacts.CategoryView {
    @BindView(R.id.classToolbar)
    Toolbar classToolbar;
    @BindView(R.id.classify_rlv)
    RecyclerView classifyRlv;
    @BindView(R.id.hot_classify_rlv)
    RecyclerView hotClassifyRlv;
    private CategoryPrams categoryPrams;
    private ClassifyAdapter classifyAdapter;

    private List<String>tabs=new ArrayList<>();
    private ArrayList<CategoryList> listArrayList;
    private ClassifyHotAdapter classifyHotAdapter;
    private CategoryHot categoryHot;

    @Override
    public void onError(String msg, int code) {

    }

    @Override
    public void onCancle() {

    }

    @Override
    protected void initEventAndData() {
        categoryPrams = new CategoryPrams();
        categoryPrams.setParentId("0");
        mPresenter.getCategoryTab(categoryPrams);
        initRecyclerView();
        initHot();
    }

    @SuppressLint("WrongConstant")
    private void initHot() {
        listArrayList = new ArrayList<>();
        classifyHotAdapter = new ClassifyHotAdapter(mActivity, listArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        hotClassifyRlv.setLayoutManager(gridLayoutManager);
        hotClassifyRlv.setAdapter(classifyHotAdapter);

        classifyHotAdapter.setMyOnClickListener(new ClassifyHotAdapter.MyOnClickListener() {
            @Override
            public void MyClick(int parentId, int Id, int position) {
                Intent intent = new Intent(getActivity(), ClassifyItemActivity.class);
                intent.putExtra("ParentId",parentId);
                intent.putExtra("Id",Id );
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }


    private void initRecyclerView() {
        classifyRlv.setLayoutManager(new LinearLayoutManager(mActivity));
        classifyAdapter = new ClassifyAdapter(tabs);
        classifyRlv.setAdapter(classifyAdapter);

        classifyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                listArrayList.clear();

                if (position < 2){
                    int i = position + 1;
                    categoryPrams.setParentId(i+"");
                }else {
                    categoryPrams.setParentId("1");
                }

                mPresenter.getCategoryList(categoryPrams);

                classifyAdapter.checks.put(position,true);
                if(classifyAdapter.isCheckPosition!=-1){
                    classifyAdapter.checks.put(classifyAdapter.isCheckPosition,false);
                    classifyAdapter.notifyItemChanged(classifyAdapter.isCheckPosition);
                }
                classifyAdapter.notifyItemChanged(position);
            }
        });



    }

    @Override
    protected CategoryPresenterIml initPresenter() {
        return new CategoryPresenterIml();
    }

    @Override
    public void showCategoryTab(List<String> tabs) {
        this.tabs.addAll(tabs);
        classifyAdapter.setNewData(tabs);
    }

    @Override
    public void showCategoryList(List<CategoryList> categoryLists) {
        listArrayList.addAll(categoryLists);
        classifyHotAdapter.notifyDataSetChanged();
    }
    @Override
    protected int initLayoutId() {
        return R.layout.fragment_classify;
    }

}

