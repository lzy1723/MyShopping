package com.example.myshopping.fragment;


import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.mvplibrary.view.BaseView;
import com.example.myshopping.Adapter.HomeAdapter;
import com.example.myshopping.Adapter.TopicAdapter;
import com.example.myshopping.BannerImgLoader;
import com.example.myshopping.R;
import com.example.myshopping.zxing.ScanActivity;
import com.example.myshopping.zxing.utils.BitmapUtil;
import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<BaseView, BasePresenter<BaseView>> implements BaseView {

    private int SCAN_REQUEST_CODE=200;
    private int SELECT_IMAGE_REQUEST_CODE=201;
    protected final int PERMS_REQUEST_CODE = 202;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_rlv)
    RecyclerView homeRlv;
    @BindView(R.id.home_vp)
    ViewPager homeVp;
    @BindView(R.id.pagerContainer)
    PagerContainer pagerContainer;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    private Unbinder unbinder;
    private View view;

    //轮播图假资源
    private String HOME_BANNER_URL1 = "https://www.wanandroid.com/blogimgs/8e95ad05-a6f5-4c65-8a89-f8d4b819aa80.jpeg";
    private String HOME_BANNER_URL2 = "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png";
    private String HOME_BANNER_URL3 = "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png";
    private String HOME_BANNER_URL4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg";
    private List<String> banner_url = Arrays.asList(new String[]{HOME_BANNER_URL1, HOME_BANNER_URL2, HOME_BANNER_URL3, HOME_BANNER_URL4});

    //画廊假资源
    private String HOME_TOPIC_URL1 = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png";
    private String HOME_TOPIC_URL2 = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    private String HOME_TOPIC_URL3 = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png";
    private String HOME_TOPIC_URL4 = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    private String HOME_TOPIC_URL5 = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png";
    private String HOME_TOPIC_URL6 = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    private List<String> topic_url = Arrays.asList(new String[]{HOME_TOPIC_URL1, HOME_TOPIC_URL2, HOME_TOPIC_URL3, HOME_TOPIC_URL4, HOME_TOPIC_URL5, HOME_TOPIC_URL6});

    //折扣假资源
    private String HOME_DISCOUNT_ONE = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
    private String HOME_DISCOUNT_TWO = "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg";
    private String HOME_DISCOUNT_THREE = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
    private String HOME_DISCOUNT_FOUR = "https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg";
    private String HOME_DISCOUNT_FIVE = "https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg";
    private List<String> discount_url = Arrays.asList(new String[]{HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE});

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        String[] permissions=new String[]{Manifest.permission.
                WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(permissions,PERMS_REQUEST_CODE);
        }
        initBanner();
        initRlv();
        initTopic();
        initListener();
        return view;
    }

    private void initListener() {
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ScanActivity.class);
                startActivityForResult(intent,SCAN_REQUEST_CODE);
            }
        });
    }


    private void initTopic() {
        TopicAdapter topicAdapter = new TopicAdapter(getActivity(), topic_url);
        homeVp.setAdapter(topicAdapter);
        homeVp.setCurrentItem(1);
        homeVp.setOffscreenPageLimit(6);
        new CoverFlow.Builder().with(homeVp).pagerMargin(-30.0f).scale(0.3f).spaceSize(0.0f).build();
    }

    private void initRlv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeRlv.setLayoutManager(layoutManager);

        HomeAdapter homeAdapter = new HomeAdapter(context, discount_url);
        homeRlv.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }


    private void initBanner() {
        banner.setImageLoader(new BannerImgLoader())
                .setImages(banner_url)
                .setDelayTime(2000)
                .setIndicatorGravity(7)
                .start();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SELECT_IMAGE_REQUEST_CODE){//从图库选择图片
            String[] proj = {MediaStore.Images.Media.DATA};
            // 获取选中图片的路径
            Cursor cursor = getContext().getContentResolver().query(data.getData(),proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String photoPath = cursor.getString(columnIndex);
                String result= BitmapUtil.parseQRcode(photoPath);
                if (!TextUtils.isEmpty(result)) {
                    Toast.makeText(mActivity, "从图库选择的图片识别结果:"+result, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mActivity, "从图库选择的图片不是二维码图片", Toast.LENGTH_SHORT).show();
                }
            }
            cursor.close();
        }else if (requestCode == SCAN_REQUEST_CODE && resultCode == -1) {
            String input = data.getStringExtra(ScanActivity.INTENT_EXTRA_RESULT);
            Toast.makeText(mActivity, "扫描结果:"+input, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected BasePresenter<BaseView> initPresenter() {
        return new BasePresenter<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onError(String msg, int code) {

    }

    @Override
    public void onCancle() {

    }
}
