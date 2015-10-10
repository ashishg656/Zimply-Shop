package com.zimplyshop.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZStoreDescriptionActivity;
import com.zimplyshop.app.baseobjects.ZProductDescriptionProductRatingObject;
import com.zimplyshop.app.extras.ZAppConstants;

import java.util.List;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZProductDescriptionStoreRatingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ZAppConstants {

    Context context;
    List<ZProductDescriptionProductRatingObject.ProductRating> ratings;
    ZProductDescriptionProductRatingObject.RatingHeader header;
    MyClickListener clickListener;

    public ZProductDescriptionStoreRatingListAdapter(Context context, List<ZProductDescriptionProductRatingObject.ProductRating> ratings, ZProductDescriptionProductRatingObject.RatingHeader header) {
        this.context = context;
        this.ratings = ratings;
        this.header = header;
        clickListener = new MyClickListener();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_product_description_store_rating_list_header_layout, parent, false);
            StoreRatingHeaderHolder holder = new StoreRatingHeaderHolder(v);
            return holder;
        } else if (viewType == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_product_description_product_rating_list_item_layout, parent, false);
            StoreRatingItemHolder holder = new StoreRatingItemHolder(v);
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
            StoreRatingHeaderHolder holder = (StoreRatingHeaderHolder) holderCom;
            holder.mainContainer.setOnClickListener(clickListener);
        } else if (getItemViewType(position) == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM) {

        }
    }

    @Override
    public int getItemCount() {
        return ratings.size() + 1;
    }

    class StoreRatingHeaderHolder extends RecyclerView.ViewHolder {

        FrameLayout mainContainer;

        public StoreRatingHeaderHolder(View v) {
            super(v);
            mainContainer = (FrameLayout) v.findViewById(R.id.storeheader);
        }
    }

    class StoreRatingItemHolder extends RecyclerView.ViewHolder {

        public StoreRatingItemHolder(View v) {
            super(v);
        }
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.storeheader:
                    Intent i = new Intent(context, ZStoreDescriptionActivity.class);
                    context.startActivity(i);
                    break;
            }
        }
    }
}
