package com.example.myshopping;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.httplibrary.utils.JsonUtils;
import com.example.httplibrary.utils.LogUtils;
import com.example.myshopping.requestbean.RegisterParmesan;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_toolbar)
    Toolbar registerToolbar;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_verify)
    EditText registerVerify;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.register_pwd_ok)
    EditText registerPwdOk;
    @BindView(R.id.btn_register_ok)
    Button btnRegisterOk;
    private String rePhone;
    private String reVerify;
    private String rePwd;
    private String rePwdOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        registerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        rePhone = registerPhone.getText().toString().trim();
        reVerify = registerVerify.getText().toString().trim();
        rePwd = registerPwd.getText().toString().trim();
        rePwdOk = registerPwdOk.getText().toString().trim();

        if (!TextUtils.isEmpty(rePhone)) {
            if (!TextUtils.isEmpty(reVerify)) {
                if (!TextUtils.isEmpty(rePwd)) {
                    if (!TextUtils.isEmpty(rePwdOk)) {
                        if (rePwd.equals(rePwdOk)) {
                            initUpLoad();
                        } else {
                            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void initUpLoad() {
        RegisterParmesan registerParmesan = new RegisterParmesan();
        registerParmesan.setMobile(rePhone);
        registerParmesan.setPwd(rePwd);
        registerParmesan.setVerifyCode(reVerify);
        String toJson = JsonUtils.classToJson(registerParmesan);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , toJson);

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://169.254.207.29:8080/kotlin/userCenter/register")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtils.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        final com.example.myshopping.http.Response bean = JsonUtils.jsonToClass(string, com.example.myshopping.http.Response.class);
                        int status = bean.getStatus();
                        if (status==0){
                            LogUtils.e("成功");
                            Intent intent = new Intent();
                            intent.putExtra("rePhone", rePhone);
                            intent.putExtra("rePwd",rePwd);
                            setResult(300, intent);
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });

                        }
                    }
                });
    }

    @OnClick({R.id.btn_register_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_ok:
                initView();
                break;
        }
    }
}
