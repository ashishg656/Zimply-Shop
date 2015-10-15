package com.zimplyshop.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZCartListObject;
import com.zimplyshop.app.extras.ZAppConstants;

/**
 * Created by praveen goel on 10/14/2015.
 */
public class ZCartListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ZAppConstants {

    Context context;
    ZCartListObject mData;

    public ZCartListAdapter(Context context, ZCartListObject mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.getCart_items().size())
            return Z_CART_LIST_TYPE_FOOTER;
        else
            return Z_CART_LIST_TYPE_PRODUCT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Z_CART_LIST_TYPE_FOOTER) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_cart_list_footer_layout, parent, false);
            CartFooterHolder holder = new CartFooterHolder(v);
            return holder;
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.z_cart_list_item_layout, parent, false);
            CartProductHolder holder = new CartProductHolder(v);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int size = mData.getCart_items().size();
        return size == 0 ? 0 : size + 1;
    }

    class CartFooterHolder extends RecyclerView.ViewHolder {

        public CartFooterHolder(View v) {
            super(v);
        }
    }

    class CartProductHolder extends RecyclerView.ViewHolder {

        public CartProductHolder(View v) {
            super(v);
        }
    }
}
