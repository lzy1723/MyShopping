package com.example.mvplibrary.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplibrary.utils.ImageLoad;


/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：BaseViewHolder
 * 创建者：liangxq
 * 创建时间：2020/3/18  9:09
 * 描述：TODO
 *
 * 主要做的是控件的绑定和初始化
 */
public class BaseViewHolder extends RecyclerView.ViewHolder{
    private View itemRootView;
    private Context context;
    //存储你当前布局的所有的控件，根据id存储
    private SparseArray sparseArray=new SparseArray<>();
    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        itemRootView=itemView;
        this.context=context;
    }

    //根据id找控件
    public <T extends View> T getView(int viewId){
        if(sparseArray.get(viewId)==null){
            View viewById = itemRootView.findViewById(viewId);
            sparseArray.put(viewId,viewById);
        }
        return (T) sparseArray.get(viewId);
    }


    public BaseViewHolder setTvContent(int textViewId, final String text, final OnItemClickListener onItemClickListener){
        TextView textView=getView(textViewId);
        if(onItemClickListener!=null){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(text);
                }
            });
        }
        textView.setText(text);
        return this;
    }


    //找到TextView控件并且赋值
    public BaseViewHolder setTextViewContent(int textViewId, final String text ){
        TextView textView=getView(textViewId);
        textView.setText(text);
        return this;
    }
    public BaseViewHolder setIvContent(int textViewId, final String path, final OnItemClickListener onItemClickListener){
        ImageView imageView=getView(textViewId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(path);
            }
        });
        ImageLoad imageLoad=new ImageLoad(context);
        imageLoad.loadImage(imageView,path);
        return this;
    }

    public BaseViewHolder setImageViewContent(int textViewId, final String path){
        ImageView imageView=getView(textViewId);
        ImageLoad imageLoad=new ImageLoad(context);
        imageLoad.loadImage(imageView,path);
        return this;
    }


    interface OnItemClickListener{
        void onItemClick(String itemString);
    }
}
