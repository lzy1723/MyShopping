package com.example.myshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myshopping.app.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.setting_toolbar)
    Toolbar settingToolbar;
    @BindView(R.id.tv_setting1)
    TextView tvSetting1;
    @BindView(R.id.tv_setting2)
    TextView tvSetting2;
    @BindView(R.id.tv_setting3)
    TextView tvSetting3;
    @BindView(R.id.exit_login)
    Button exitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        settingToolbar.setTitle("");
        setSupportActionBar(settingToolbar);
        settingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_setting1, R.id.tv_setting2, R.id.tv_setting3, R.id.exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setting1:
                Toast.makeText(this, tvSetting1.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting2:
                Toast.makeText(this, tvSetting2.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting3:
                Toast.makeText(this, tvSetting3.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_login:
                Intent setting_intent = getIntent();
                setting_intent.putExtra("exit","登录/注册");
                setResult(400,setting_intent);
                finish();
                break;
        }
    }
}
