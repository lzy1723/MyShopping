package com.example.myshopping;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.example.myshopping.app.CameraUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyActivity extends AppCompatActivity {

    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.My_toolbar)
    Toolbar MyToolbar;
    @BindView(R.id.mArrowIv)
    ImageView mArrowIv;
    @BindView(R.id.mUserIconIv)
    CircleImageView mUserIconIv;
    @BindView(R.id.mUserIconView)
    RelativeLayout mUserIconView;
    @BindView(R.id.mUserNameEt)
    EditText mUserNameEt;
    @BindView(R.id.mGenderBoyRb)
    RadioButton mGenderBoyRb;
    @BindView(R.id.mGenderGirlRb)
    RadioButton mGenderGirlRb;
    @BindView(R.id.mUserPhoneTv)
    TextView mUserPhoneTv;
    @BindView(R.id.mUserSignEt)
    EditText mUserSignEt;

    private File cameraSavePath;//拍照照片路径
    private Uri uri;//照片uri
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        MyToolbar.setTitle("");
        setSupportActionBar(MyToolbar);
        MyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_save, R.id.mUserIconView, R.id.mUserNameEt, R.id.mGenderBoyRb, R.id.mGenderGirlRb, R.id.mUserPhoneTv, R.id.mUserSignEt,R.id.mUserIconIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                break;
            case R.id.mUserIconView:
                new AlertView("选择图片", null, "取消", null, new String[]{"拍照", "从相册中选择"}, this, AlertView.Style.ActionSheet,
                        new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {
                                switch (position) {
                                    case 0:
                                        CameraUtils.goCamera(MyActivity.this);
                                        break;
                                    case 1:
                                        goPhotoAlbum();
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.mUserNameEt:
                break;
            case R.id.mGenderBoyRb:
                break;
            case R.id.mGenderGirlRb:
                break;
            case R.id.mUserPhoneTv:
                break;
            case R.id.mUserSignEt:
                break;
            case R.id.mUserIconIv:
                break;
        }
    }

    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            Log.e("TAG1=========:", photoPath);
            Glide.with(MyActivity.this).load(photoPath).into(mUserIconIv);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            Log.e("TAG2=========", "onActivityResult: "+data.getData() );
            Glide.with(MyActivity.this).load(data.getData()).into(mUserIconIv);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



}
