package com.example.myshopping.fragment.DetailFragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.myshopping.BannerImgLoader;
import com.example.myshopping.Event.UpDateCartCount;
import com.example.myshopping.LoginActivity;
import com.example.myshopping.R;
import com.example.myshopping.View.SkuBottomDialog;
import com.example.myshopping.app.SpUtil;
import com.example.myshopping.bean.AddCartGoods;
import com.example.myshopping.bean.CategoryDetailBean.CateDetailList;
import com.example.myshopping.bean.SkuChangedEvent;
import com.example.myshopping.constacts.AddCartConstacts;
import com.example.myshopping.presenter.AddCartPresenterIml;
import com.example.myshopping.requestbean.AddCartPrams;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class DetailCateFragment extends BaseMvpFragment<AddCartConstacts.AddCartView, AddCartPresenterIml> implements AddCartConstacts.AddCartView {


    @BindView(R.id.banner_detail)
    Banner bannerDetail;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_detail_price)
    TextView tvDetailPrice;
    @BindView(R.id.tv_detail_desc)
    TextView tvDetailDesc;
    @BindView(R.id.ll_detail_item)
    LinearLayout llDetailItem;
    private SkuBottomDialog bottomDialog;
    private String skuString;
    CateDetailList goodsDetail;
    //下单商品的数量
    private int numberCount=1;

    protected void initData() {
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        llDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.show();
            }
        });
    }

    @Override
    protected void initEventAndData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected AddCartPresenterIml initPresenter() {
        return new AddCartPresenterIml();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setData(CateDetailList cateDetailList) {
        ArrayList<String> images = new ArrayList<>();
        String goodsBanner = cateDetailList.getGoodsBanner();
        Log.e("TAG",goodsBanner);
        String[] split = goodsBanner.split(",");
        for (int i = 0; i < split.length; i++) {
            Log.e("TAG",split[i]);
            images.add(split[i]);
        }
        bannerDetail.setImageLoader(new BannerImgLoader())
                .setImages(images)
                .setDelayTime(2000)
                .setIndicatorGravity(7)
                .start();

        tvDetailTitle.setText(cateDetailList.getGoodsDesc());
        tvDetailPrice.setText("￥"+cateDetailList.getGoodsDefaultPrice());
        tvDetailDesc.setText(cateDetailList.getGoodsDefaultSku());
        bottomDialog = new SkuBottomDialog(mActivity, cateDetailList);
        skuString = cateDetailList.getGoodsDefaultSku();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_detail_cate;
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void changeSku(SkuChangedEvent skuChangedEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bottomDialog.getSkuChangedEvents().size(); i++) {
            stringBuilder.append(bottomDialog.getSkuChangedEvents().get(i));
            if (i != bottomDialog.getSkuChangedEvents().size() - 1) {
                stringBuilder.append(",");
            }
        }
        numberCount = bottomDialog.numberButton.getNumber();
        skuString = stringBuilder.toString();
        tvDetailDesc.setText(stringBuilder.toString() + numberCount + "件");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cartAddEvent(AddCartGoods addCartGoods) {
        int token = (int) SpUtil.getParam("token", 0);
        if (token != 0) {
            AddCartPrams addCartPrams = new AddCartPrams();
            addCartPrams.setGoodsDesc(this.goodsDetail.getGoodsDesc());
            addCartPrams.setGoodsIcon(goodsDetail.getGoodsDefaultIcon());
            addCartPrams.setGoodsPrice(Integer.parseInt(goodsDetail.getGoodsDefaultPrice()));
            addCartPrams.setGoodsId(goodsDetail.getId());
            addCartPrams.setGoodsCount(numberCount);
            addCartPrams.setGoodsSku(skuString);
            Log.e("TAG==DetailCateFrag==", "onClick: " + token);
            mPresenter.addCartP(addCartPrams);
        } else {
            Intent intent=new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showCount(String count) {
        SpUtil.setParam("cartCount",Integer.parseInt(count));
        Log.e("我是条目个数=========",SpUtil.getParam("cartCount",0)+"");
        UpDateCartCount upDateCartCount = new UpDateCartCount();
        upDateCartCount.setCartCount(Integer.parseInt(count));
        EventBus.getDefault().postSticky(upDateCartCount);
    }

    @Override
    public void onError(String msg, int code) {

    }

    @Override
    public void onCancle() {

    }
}
