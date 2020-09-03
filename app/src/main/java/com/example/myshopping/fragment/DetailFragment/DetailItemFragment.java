package com.example.myshopping.fragment.DetailFragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myshopping.Event.DetailEvent;
import com.example.myshopping.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailItemFragment extends Fragment {


    @BindView(R.id.iv_icon1)
    ImageView ivIcon1;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    private Unbinder bind;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_detail_item, container, false);
        bind = ButterKnife.bind(this, inflate);
        initData();
        return inflate;
    }

    private void initData() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setData(DetailEvent data){
        String getGoodsDetailOne = data.getGetGoodsDetailOne();
        String getGoodsDetailTwo = data.getGetGoodsDetailTwo();
        Log.e("GoodDetail:",getGoodsDetailOne+getGoodsDetailTwo);

        Glide.with(getActivity()).load(getGoodsDetailOne).into(ivIcon1);
        Glide.with(getActivity()).load(getGoodsDetailTwo).into(ivIcon2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }
}
