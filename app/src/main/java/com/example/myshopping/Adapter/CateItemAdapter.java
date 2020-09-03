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
import com.example.myshopping.bean.CategoryItemList.CateItemListBean;

import java.util.List;

public class CateItemAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CateItemListBean> itemListBeans;
    private MyCateItemClickListener myCateItemClickListener;

    public void setMyCateItemClickListener(MyCateItemClickListener myCateItemClickListener) {
        this.myCateItemClickListener = myCateItemClickListener;
    }

    public CateItemAdapter(Context context, List<CateItemListBean> itemListBeans) {
        this.context = context;
        this.itemListBeans = itemListBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cate_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        CateItemListBean itemListBean = itemListBeans.get(position);

        holder1.tv_cate_item_title.setText(itemListBean.getGoodsDesc());
        holder1.tv_cate_item_place.setText("￥"+itemListBean.getGoodsDefaultPrice());
        holder1.tv_cate_item_StockCount.setText("销量："+itemListBean.getGoodsStockCount());
        holder1.tv_cate_item_SalesCount.setText("库存"+itemListBean.getGoodsSalesCount());

        holder1.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCateItemClickListener.myCateItemClick(position);
            }
        });

        Glide.with(context).load(itemListBean.getGoodsDefaultIcon()).into(holder1.iv_cate_item);
    }

    public interface MyCateItemClickListener{
        void myCateItemClick(int i);
    }

    @Override
    public int getItemCount() {
        return itemListBeans.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_cate_item;
        public TextView tv_cate_item_title;
        public TextView tv_cate_item_place;
        public TextView tv_cate_item_SalesCount;
        public TextView tv_cate_item_StockCount;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_cate_item = (ImageView) rootView.findViewById(R.id.iv_cate_item);
            this.tv_cate_item_title = (TextView) rootView.findViewById(R.id.tv_cate_item_title);
            this.tv_cate_item_place = (TextView) rootView.findViewById(R.id.tv_cate_item_place);
            this.tv_cate_item_SalesCount = (TextView) rootView.findViewById(R.id.tv_cate_item_SalesCount);
            this.tv_cate_item_StockCount = (TextView) rootView.findViewById(R.id.tv_cate_item_StockCount);
        }

    }
}
