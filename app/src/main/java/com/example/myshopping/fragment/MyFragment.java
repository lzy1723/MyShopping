package com.example.myshopping.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myshopping.LoginActivity;
import com.example.myshopping.MyActivity;
import com.example.myshopping.R;
import com.example.myshopping.SettingActivity;
import com.example.myshopping.app.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.cache.Sp;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    @BindView(R.id.iv_my_image)
    ImageView ivMyImage;
    @BindView(R.id.iv_my_header)
    ImageView ivMyHeader;
    @BindView(R.id.tv_my_wait)
    TextView tvMyWait;
    @BindView(R.id.tv_my_confirm)
    TextView tvMyConfirm;
    @BindView(R.id.tv_my_comleted)
    TextView tvMyComleted;
    @BindView(R.id.tv_my_order)
    TextView tvMyOrder;
    @BindView(R.id.tv_my_address)
    TextView tvMyAddress;
    @BindView(R.id.tv_my_shape)
    TextView tvMyShape;
    @BindView(R.id.tv_my_setting)
    TextView tvMySetting;
    @BindView(R.id.tv_my_header)
    TextView tvMyHeader;
    private Unbinder unbinder;
    private Integer token;


    public MyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        String name = (String) SpUtil.getParam("name", "");
        if (!TextUtils.isEmpty(name)){
            tvMyHeader.setText(name+"");
        }else {
            tvMyHeader.setText("登录/注册");
        }
        return inflate;
    }


    @OnClick({R.id.iv_my_image, R.id.iv_my_header, R.id.tv_my_header,R.id.tv_my_wait, R.id.tv_my_confirm, R.id.tv_my_comleted, R.id.tv_my_order, R.id.tv_my_address, R.id.tv_my_shape, R.id.tv_my_setting})
    public void onViewClicked(View view) {
        if (SpUtil.getParam("loginid",0) !=null){
            token = (Integer) SpUtil.getParam("loginid", 0);
            Log.e("TAG==================",token+"");
        }
        switch (view.getId()) {
            case R.id.iv_my_image:
                break;
            case R.id.iv_my_header:
                if (token==0) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,100);
                } else {
                    Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_my_header:
                if (token==0){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,100);
                }else {
                    Intent intent = new Intent(getActivity(), MyActivity.class);
                    startActivityForResult(intent,100);
                }


                break;
            case R.id.tv_my_wait:

                break;
            case R.id.tv_my_confirm:

                break;
            case R.id.tv_my_comleted:
                break;
            case R.id.tv_my_order:
                break;
            case R.id.tv_my_address:
                break;
            case R.id.tv_my_shape:
                Toast.makeText(getActivity(), "敬请期待~~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_my_setting:
                Intent setting_intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(setting_intent,300);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200){
            if (token==0){
                String userName = data.getStringExtra("userName");
                tvMyHeader.setText(userName);
            }
        }
        if (requestCode == 300 && resultCode == 400){
            String exit = data.getStringExtra("exit");
            tvMyHeader.setText(exit);
            SpUtil.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
