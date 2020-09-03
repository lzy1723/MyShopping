package com.example.myshopping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.httplibrary.utils.LogUtils;
import com.example.mvplibrary.base.BaseMvpActivity;
import com.example.myshopping.app.SpUtil;
import com.example.myshopping.bean.LoginBean;
import com.example.myshopping.constacts.LoginContract;
import com.example.myshopping.presenter.LoginPresenter;
import com.example.myshopping.requestbean.LoginParmesan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.login_toolbar)
    Toolbar loginToolbar;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;
    private String phone;
    private String pwd;

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    private void initView() {
        phone = loginPhone.getText().toString().trim();
        pwd = loginPwd.getText().toString().trim();
    }

    private void initListener() {
        loginToolbar.setTitle("");
        setSupportActionBar(loginToolbar);
        loginToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 300) {
            String rePhone = data.getStringExtra("rePhone");
            String rePwd = data.getStringExtra("rePwd");
            loginPhone.setText(rePhone);
            loginPwd.setText(rePwd);
        }
    }

    @Override
    protected void initEventAndData() {
        initView();
        initListener();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onSuccess(LoginBean loginBean) {

        String userName = loginBean.getUserMobile();
        Toast.makeText(this, userName+"登录成功", Toast.LENGTH_SHORT).show();
        if (loginBean.getUserName()!=null){
            SpUtil.setParam("name",loginBean.getUserMobile());
        }
        if (loginBean.getId()!=0){
            SpUtil.setParam("loginid",loginBean.getId());
        }
        Log.e("Sp保存的名字============",SpUtil.getParam("name","")+"");
        Integer param = (Integer) SpUtil.getParam("loginid", 0);
        Log.e("Sp保存的ID============", param+"");
    }

    @Override
    public void onError(String msg, int code) {
        LogUtils.e(msg+code);
    }

    @Override
    public void onCancle() {
        mPresenter.onCancal();
    }

    @OnClick({R.id.tv_login_register, R.id.btn_register, R.id.forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.btn_register:
                String user = loginPhone.getText().toString().trim();
                String pass = loginPwd.getText().toString().trim();
                LoginParmesan loginParmesan = new LoginParmesan();
                loginParmesan.setMobile(user);
                loginParmesan.setPwd(pass);
                loginParmesan.setPushId("140fe1da9e25c9cc321");
                mPresenter.getDataLogin(loginParmesan);
                Intent intent1 = getIntent();
                intent1.putExtra("userName",user);
                setResult(200,intent1);
                finish();
                break;
            case R.id.forget_pwd:
                Toast.makeText(this, "没有找回密码功能，忘了就在注册一个吧", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
