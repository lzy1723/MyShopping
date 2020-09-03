package com.example.myshopping;


import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.myshopping.fragment.ClassifyFragment;
import com.example.myshopping.fragment.HomeFragment;
import com.example.myshopping.fragment.MsgFragment;
import com.example.myshopping.fragment.MyFragment;
import com.example.myshopping.fragment.ShopFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.bnb)
    BottomNavigationBar bnb;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private MsgFragment msgFragment;
    private MyFragment myFragment;
    private ShopFragment shopFragment;
    int lastselectedPosition;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyBottomNavigationBar();
        setBottomBar();
    }

    private void setBottomBar() {
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        shopFragment = new ShopFragment();
        msgFragment = new MsgFragment();
        myFragment = new MyFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(classifyFragment);
        fragmentList.add(shopFragment);
        fragmentList.add(msgFragment);
        fragmentList.add(myFragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl,homeFragment).commit();

        lastselectedPosition = 0;


        bnb.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void switchFragment(int position) {
        if (position == lastselectedPosition){
            return;
        }

        if (fragmentList == null || fragmentList.size() == 0){
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragmentList.get(lastselectedPosition));
        if (!fragmentList.get(position).isAdded()){
            transaction.add(R.id.fl,fragmentList.get(position)).show(fragmentList.get(position));
        }else {
            transaction.show(fragmentList.get(position));
        }
        transaction.commit();
        lastselectedPosition = position;
    }


    private void MyBottomNavigationBar() {

        bnb.setFirstSelectedPosition(lastselectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.colorBarSelect)
                .setBarBackgroundColor(R.color.colorBar)
                .addItem(new BottomNavigationItem(R.drawable.home_selector,"主页"))
                .addItem(new BottomNavigationItem(R.drawable.classify_selector,"分类"))
                .addItem(new BottomNavigationItem(R.drawable.shop_selector,"购物车"))
                .addItem(new BottomNavigationItem(R.drawable.msg_selector,"消息"))
                .addItem(new BottomNavigationItem(R.drawable.my_selector,"我的"))
                .initialise();
    }
}

