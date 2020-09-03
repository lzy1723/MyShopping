package com.example.myshopping.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> discount_url;

    public HomeAdapter(Context context, List<String> discount_url) {
        this.context = context;
        this.discount_url = discount_url;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;

        Glide.with(context).load(discount_url.get(position)).into(holder1.iv_home);
        holder1.tv_home2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder1.tv_home1.setText("￥123.0");
        holder1.tv_home2.setText("$1000.00");
    }

    @Override
    public int getItemCount() {
        return discount_url.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_home;
        public TextView tv_home1;
        public TextView tv_home2;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_home = (ImageView) rootView.findViewById(R.id.iv_home);
            this.tv_home1 = (TextView) rootView.findViewById(R.id.tv_home1);
            this.tv_home2 = (TextView) rootView.findViewById(R.id.tv_home2);
        }

    }
}
