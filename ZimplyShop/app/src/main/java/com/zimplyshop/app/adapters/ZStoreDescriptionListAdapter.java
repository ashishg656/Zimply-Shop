package com.zimplyshop.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZProductDescriptionActivity;
import com.zimplyshop.app.baseobjects.ZProductListObject;
import com.zimplyshop.app.baseobjects.ZStoreDescriptionObject;
import com.zimplyshop.app.extras.ZAppConstants;

import java.util.List;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZStoreDescriptionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ZAppConstants {

    Context context;
    List<ZProductListObject.ZProductObject> products;
    ZStoreDescriptionObject.StoreHeaderObject header;
    int imageHeight;
    MyClickListener clickListener;

    public ZStoreDescriptionListAdapter(Context context, List<ZProductListObject.ZProductObject> products, ZStoreDescriptionObject.StoreHeaderObject header) {
        this.context = context;
        this.products = products;
        this.header = header;
        imageHeight = context.getResources().getDisplayMetrics().widthPixels / 2 - context.getResources().getDimensionPixelSize(R.dimen.z_margin_mini);
        clickListener = new MyClickListener();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_store_description_list_header_layout, parent, false);
            StoreDescriptionHeaderHolder holder = new StoreDescriptionHeaderHolder(v);
            return holder;
        } else if (viewType == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_home_product_item_layout, parent, false);
            StoreDescriptionItemHolder holder = new StoreDescriptionItemHolder(v);
            return holder;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_HEADER;
        else
            return Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderCom, int position) {
        if (getItemViewType(position) == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_HEADER) {

        } else if (getItemViewType(position) == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM) {
            StoreDescriptionItemHolder holder = (StoreDescriptionItemHolder) holderCom;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.productImage.getLayoutParams();
            params.height = imageHeight;
            holder.productImage.setLayoutParams(params);

            holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.mainContainer.setTag(position);
            holder.mainContainer.setOnClickListener(clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return products.size() + 1;
    }

    class StoreDescriptionHeaderHolder extends RecyclerView.ViewHolder {

        public StoreDescriptionHeaderHolder(View v) {
            super(v);
        }
    }

    class StoreDescriptionItemHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView mrp;
        FrameLayout mainContainer;

        public StoreDescriptionItemHolder(View v) {
            super(v);
            productImage = (ImageView) v.findViewById(R.id.productimage);
            mrp = (TextView) v.findViewById(R.id.mrptextproduct);
            mainContainer = (FrameLayout) v.findViewById(R.id.prodyctconatunre);
        }
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.prodyctconatunre:
                    Intent i = new Intent(context, ZProductDescriptionActivity.class);
                    int pos = (int) v.getTag();
                    i.putExtra("product_id", products.get(pos - 1).getId());
                    context.startActivity(i);
                    break;
            }
        }
    }
}
