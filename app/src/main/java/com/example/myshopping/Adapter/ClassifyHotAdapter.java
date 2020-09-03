package com.example.myshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;
import com.example.myshopping.bean.CategoryList;

import java.util.ArrayList;

public class ClassifyHotAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CategoryList> categoryLists;
    public MyOnClickListener myOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    public ClassifyHotAdapter(Context context, ArrayList<CategoryList> categoryLists) {
        this.context = context;
        this.categoryLists = categoryLists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv_hot.setText(categoryLists.get(position).getCategoryName());
        Glide.with(context).load(categoryLists.get(position).getCategoryIcon()).into(holder1.iv_hot);
        CategoryList categoryList = categoryLists.get(position);
        holder1.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListener.MyClick(position,categoryList.getParentId(),categoryList.getId());
            }
        });
    }

    public interface MyOnClickListener{
        void MyClick(int parentId,int Id,int position);
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }


    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_hot;
        public TextView tv_hot;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_hot = (ImageView) rootView.findViewById(R.id.iv_hot);
            this.tv_hot = (TextView) rootView.findViewById(R.id.tv_hot);
        }

    }
}
