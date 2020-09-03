package com.example.myshopping.Adapter;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.httplibrary.utils.LogUtils;
import com.example.myshopping.R;

import java.util.List;

public class ClassifyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SparseArray<Boolean>checks;
    public int isCheckPosition=-1;


    public ClassifyAdapter(@Nullable List<String> data) {
        super(R.layout.layout_classifytab, data);
        checks = new SparseArray<>();
        for (int i = 0; i < data.size(); i++) {
            checks.put(i,false);
        }
    }

    @Override
    public void setNewData(@Nullable List<String> data) {
        super.setNewData(data);
        if(checks!=null&&checks.size()==0){
            for (int i = 0; i < data.size(); i++) {
                checks.put(i,false);
            }
        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {
        TextView tv_classtab = baseViewHolder.getView(R.id.tv_classtab);
        int position = baseViewHolder.getPosition();
        if(checks.get(position)){
            isCheckPosition=position;
            tv_classtab.setSelected(true);
        }else{
            tv_classtab.setSelected(false);
        }
        tv_classtab.setText(s);
    }
}
