package com.example.myshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httplibrary.utils.LogUtils;
import com.example.mvplibrary.base.BaseMvpActivity;
import com.example.myshopping.Adapter.CateItemAdapter;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.bean.CategoryItemList.CateItemListBean;
import com.example.myshopping.bean.CategoryList;
import com.example.myshopping.constacts.CateItemConstacts;
import com.example.myshopping.constacts.CategoryConstacts;
import com.example.myshopping.fragment.DetailFragment.DetailCateFragment;
import com.example.myshopping.presenter.CateItemPresenterIml;
import com.example.myshopping.presenter.CategoryPresenterIml;
import com.example.myshopping.requestbean.CategoryHot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifyItemActivity extends BaseMvpActivity<CateItemConstacts.CateItemView, CateItemPresenterIml> implements CateItemConstacts.CateItemView {

    @BindView(R.id.item_toolbar)
    Toolbar itemToolbar;
    @BindView(R.id.item_rlv)
    RecyclerView itemRlv;
    @BindView(R.id.tv_hot_item)
    TextView tvHotItem;
    private int parentId;
    private int id;
    private int position;
    private ArrayList<CateItemListBean> itemListBeans;
    private CateItemAdapter cateItemAdapter;

    @Override
    protected void initEventAndData() {
        itemToolbar.setTitle("");
        setSupportActionBar(itemToolbar);

        Intent intent = getIntent();
        position = intent.getIntExtra("ParentId", 0);
        parentId = intent.getIntExtra("Id", 0);
        id = intent.getIntExtra("position", 0);

        CategoryHot categoryHot = new CategoryHot();
        if (parentId == 1 && position < 2) {
            tvHotItem.setVisibility(View.GONE);
            categoryHot.setCategoryId(id);
            categoryHot.setPageNo(1);
            mPresenter.getCateItem(categoryHot);
        } else if (parentId == 2 && position == 0) {
            tvHotItem.setVisibility(View.GONE);
            categoryHot.setCategoryId(id);
            categoryHot.setPageNo(1);
            mPresenter.getCateItem(categoryHot);
        } else {
            tvHotItem.setVisibility(View.VISIBLE);
        }

        itemListBeans = new ArrayList<>();
        itemRlv.setLayoutManager(new GridLayoutManager(this,2));
        cateItemAdapter = new CateItemAdapter(this,itemListBeans);
        itemRlv.setAdapter(cateItemAdapter);

        //点击监听
        initListener();
        //返回监听
        itemToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListener() {
        cateItemAdapter.setMyCateItemClickListener(new CateItemAdapter.MyCateItemClickListener() {
            @Override
            public void myCateItemClick(int i) {
                Intent intent = new Intent(ClassifyItemActivity.this, CateShopActivity.class);
                intent.putExtra("ShopId",itemListBeans.get(i).getId());
                intent.putExtra("DetailOne",itemListBeans.get(i).getGoodsDetailOne());
                intent.putExtra("DetailTwo",itemListBeans.get(i).getGoodsDetailTwo());
                startActivity(intent);
            }
        });
    }

    @Override
    protected CateItemPresenterIml initPresenter() {
        return new CateItemPresenterIml();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showCateCom(List<CateItemListBean> cateItemListBeans) {
        itemListBeans.addAll(cateItemListBeans);
        cateItemAdapter.notifyDataSetChanged();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_classify_item;
    }


    @Override
    public void onError(String msg, int code) {
        LogUtils.e(msg + code);
    }

    @Override
    public void onCancle() {

    }

}
