package com.example.myshopping.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.myshopping.Adapter.CartAdapter;
import com.example.myshopping.Event.UpDateTotalPrice;
import com.example.myshopping.ItmeActivity;
import com.example.myshopping.R;
import com.example.myshopping.bean.CartGoods;
import com.example.myshopping.constacts.CartConstacts;
import com.example.myshopping.presenter.CartPresenterIml;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopFragment extends BaseMvpFragment<CartConstacts.CartView, CartPresenterIml> implements CartConstacts.CartView {


    @BindView(R.id.cartRv)
    RecyclerView cartRv;
    @BindView(R.id.mAllCheckedCb)
    CheckBox mAllCheckedCb;
    @BindView(R.id.mTotalPriceTv)
    TextView mTotalPriceTv;
    @BindView(R.id.mSettleAccountsBtn)
    Button mSettleAccountsBtn;
    @BindView(R.id.mDeleteBtn)
    Button mDeleteBtn;

    //编辑和完成的标识
    public static boolean isCheck = false;

    List<CartGoods> goods = new ArrayList<>();
    @BindView(R.id.tv_save_edit)
    TextView tvSaveEdit;
    @BindView(R.id.toolbar_car)
    Toolbar toolbarCar;
    @BindView(R.id.mBottomView)
    RelativeLayout mBottomView;
    private CartAdapter cartAdapter;

    public ShopFragment() {

    }


    @Override
    protected int initLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void onError(String msg, int code) {

    }

    @Override
    public void onCancle() {

    }

    @Override
    public void showCartGoodList(List<CartGoods> goods) {
        this.goods.addAll(goods);
        cartAdapter.setNewData(this.goods);
        Log.e("TAG==ShopFragment===", "showCartGoodList: " + goods.toString());
    }

    @Override
    protected void initEventAndData() {
        EventBus.getDefault().register(this);
        mPresenter.getCartListP();
        cartAdapter = new CartAdapter(goods, mActivity);
        cartRv.setLayoutManager(new LinearLayoutManager(mActivity));
        cartRv.setAdapter(cartAdapter);

        mTotalPriceTv.setVisibility(View.VISIBLE);
        mTotalPriceTv.setText("合计￥0.0");
        tvSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCheck) {
                    tvSaveEdit.setText("完成");
                    isCheck = true;
                    mDeleteBtn.setVisibility(View.VISIBLE);
                    mSettleAccountsBtn.setVisibility(View.VISIBLE);
                    mTotalPriceTv.setVisibility(View.GONE);
                } else {
                    tvSaveEdit.setText("编辑");
                    mTotalPriceTv.setVisibility(View.VISIBLE);
                    mTotalPriceTv.setText("￥0.0");
                    //删除按钮
                    mDeleteBtn.setVisibility(View.GONE);
                    //结算按钮
                    mSettleAccountsBtn.setVisibility(View.VISIBLE);
                    isCheck = false;
                    int totalCount = 0;
                    for (CartGoods good : goods) {
                        if (good.isCheck()) {
                            totalCount += Integer.parseInt(good.getGoodsPrice()) * good.getGoodsCount();
                        }
                    }
                    mTotalPriceTv.setText("合计￥" + totalCount);
                    mSettleAccountsBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mActivity, ItmeActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected CartPresenterIml initPresenter() {
        return new CartPresenterIml();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setTotalPrice(UpDateTotalPrice upDateTotalPrice) {
        int totalPrice = 0;
        for (CartGoods good : goods) {
            if (good.isCheck()) {
                totalPrice += Integer.parseInt(good.getGoodsPrice()) * good.getGoodsCount();
            }
        }
        mTotalPriceTv.setText("合计￥" + totalPrice);
    }
}
