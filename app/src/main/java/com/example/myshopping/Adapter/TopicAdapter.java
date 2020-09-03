package com.example.myshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;

import java.util.List;

public class TopicAdapter extends PagerAdapter {

    private Context context;
    private List<String> topic_url;

    public TopicAdapter(Context context, List<String> topic_url) {
        this.context = context;
        this.topic_url = topic_url;
    }

    @Override
    public int getCount() {
        return topic_url.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_topic, null);
        ImageView iv_topic = view.findViewById(R.id.iv_topic);
        Glide.with(context).load(topic_url.get(position)).into(iv_topic);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
