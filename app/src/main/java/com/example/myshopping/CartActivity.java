package com.example.myshopping;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvplibrary.base.BaseActivity;
import com.example.myshopping.fragment.ShopFragment;

public class CartActivity extends BaseActivity {

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
//        ShopFragment shopFragment = new ShopFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();/*获取到FragmentManager*/
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();/*开启事务*/
//        fragmentTransaction.add(R.id.content, shopFragment);
//        fragmentTransaction.commit();/*提交事务*/
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_cart;
    }


}
