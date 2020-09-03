package com.example.myshopping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.httplibrary.utils.LogUtils;
import com.example.mvplibrary.base.BaseMvpActivity;
import com.example.myshopping.Adapter.DetailTabAdapter;
import com.example.myshopping.Event.DetailEvent;
import com.example.myshopping.Event.UpDateCartCount;
import com.example.myshopping.app.SpUtil;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.constacts.DitailContract;
import com.example.myshopping.fragment.DetailFragment.DetailCateFragment;
import com.example.myshopping.fragment.DetailFragment.DetailItemFragment;
import com.example.myshopping.presenter.DitailPresenter;
import com.example.myshopping.requestbean.CateDetailPrams;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.cache.Sp;
import q.rorbin.badgeview.QBadgeView;

public class CateShopActivity extends BaseMvpActivity<DitailContract.DatailView, DitailPresenter> implements DitailContract.DatailView {

    @BindView(R.id.DetailTab)
    TabLayout DetailTab;
    @BindView(R.id.DetailToolbar)
    Toolbar DetailToolbar;
    @BindView(R.id.DetailVp)
    ViewPager DetailVp;
    @BindView(R.id.ll_detail_shape)
    LinearLayout llDetailShape;
    @BindView(R.id.ll_detail_shopcat)
    LinearLayout llDetailShopcat;
    @BindView(R.id.ll_detail_addshopcat)
    TextView llDetailAddshopcat;
    private DetailItemFragment detailItemFragment;
    private DetailCateFragment detailCateFragment;
    private int shopId;
    private QBadgeView qBadgeView;
    private Integer cartCount;


    @Override
    protected void initData() {
        Intent intent = getIntent();
        shopId = intent.getIntExtra("ShopId", 0);
        Log.e("TAG", shopId + "");
        CateDetailPrams cateDetailPrams = new CateDetailPrams();
        cateDetailPrams.setGoodsId(shopId);
        Log.e("TAG===", cateDetailPrams.toString());
        mPresenter.getDatail(cateDetailPrams);
        qBadgeView = new QBadgeView(this);
        EventBus.getDefault().register(this);
        cartCount = (Integer) SpUtil.getParam("cartCount", 0);
        initView();
        initListener();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_cate_shop;
    }

    private void initListener() {
        DetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        DetailToolbar.setTitle("");
        setSupportActionBar(DetailToolbar);

        ArrayList<Fragment> fragments = new ArrayList<>();
        detailCateFragment = new DetailCateFragment();
        detailItemFragment = new DetailItemFragment();

        fragments.add(detailCateFragment);
        fragments.add(detailItemFragment);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("商品");
        strings.add("详情");

        DetailTabAdapter detailTabAdapter = new DetailTabAdapter(getSupportFragmentManager(), fragments, strings);
        DetailTab.setupWithViewPager(DetailVp);
        DetailVp.setAdapter(detailTabAdapter);
    }

    @Override
    protected void initEventAndData() {


    }

    @Override
    protected DitailPresenter initPresenter() {
        return new DitailPresenter();
    }

    @Override
    public void onError(String msg, int code) {
        LogUtils.e(msg + code);
    }

    @Override
    public void onCancle() {

    }

    @Override
    public void showDatailView(CateDetailList cateDetailLists) {
        EventBus.getDefault().postSticky(cateDetailLists);
        DetailEvent detailEvent = new DetailEvent();
        detailEvent.setGetGoodsDetailOne(cateDetailLists.getGoodsDetailOne());
        detailEvent.setGetGoodsDetailTwo(cateDetailLists.getGoodsDetailTwo());
        EventBus.getDefault().postSticky(detailEvent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_detail_shape, R.id.ll_detail_shopcat, R.id.ll_detail_addshopcat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_detail_shape:
                break;
            case R.id.ll_detail_shopcat:
                Intent intent=new Intent(this,CartActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_detail_addshopcat:
                break;
        }
    }

    public void setCartCount(int count){
        //位于控件的左上角
        qBadgeView.setBadgeGravity(Gravity.END|Gravity.TOP);
        qBadgeView.setGravityOffset(22f,-2f,true);
        qBadgeView.setBadgeTextSize(10f,true);
        qBadgeView.bindTarget(llDetailShopcat).setBadgeNumber(count);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateCartCount(UpDateCartCount upDateCartCount){
        cartCount = (Integer) SpUtil.getParam("cartCount", 0);
        if(cartCount!=0){
            setCartCount(cartCount);
        }
    }

}