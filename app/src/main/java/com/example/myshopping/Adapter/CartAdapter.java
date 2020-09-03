package com.example.myshopping.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import com.example.mvplibrary.base.adapter.BaseAdapter;
import com.example.myshopping.Event.UpDateTotalPrice;
import com.example.myshopping.R;
import com.example.myshopping.bean.CartGoods;
import com.example.myshopping.fragment.ShopFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ren.qinc.numberbutton.NumberButton;



/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.adapter
 * 文件名：CartAdapter
 * 创建者：liangxq
 * 创建时间：2020/8/30  20:17
 * 描述：TODO
 */
public class CartAdapter extends BaseAdapter<CartGoods> {

    public CartAdapter(List<CartGoods> datas, Context context) {
        super(datas, context, R.layout.cart_item);
    }

    @Override
    protected void bindData(com.example.mvplibrary.base.adapter.BaseViewHolder holder, CartGoods cartGoods) {
        String goodsDesc = cartGoods.getGoodsDesc();
        holder.setImageViewContent(R.id.mGoodsIconIv,cartGoods.getGoodsIcon());
        holder.setTextViewContent(R.id.mGoodsDescTv,goodsDesc);
        holder.setTextViewContent(R.id.mGoodsSkuTv,cartGoods.getGoodsSku());
        holder.setTextViewContent(R.id.mGoodsPriceTv,cartGoods.getGoodsPrice());
        NumberButton numberButton = holder.getView(R.id.mGoodsCountBtn);
        numberButton.setCurrentNumber(cartGoods.getGoodsCount());
        CheckBox checkBox = holder.getView(R.id.mCheckedCb);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
               if(isCheck){
                   cartGoods.setCheck(true);
               }else{
                   cartGoods.setCheck(false);
               }
               if(!ShopFragment.isCheck){
                   EventBus.getDefault().postSticky(new UpDateTotalPrice());
               }
            }
        });
        EditText numberEditText = numberButton.findViewById(R.id.text_count);
        numberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cartGoods.setGoodsCount(Integer.parseInt(charSequence.toString()));
                if(!ShopFragment.isCheck){
                    EventBus.getDefault().postSticky(new UpDateTotalPrice());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
