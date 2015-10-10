package com.zimplyshop.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZProductDescriptionProductRatingObject;
import com.zimplyshop.app.extras.ZAppConstants;

import java.util.List;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZProductDescriptionProductRatingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ZAppConstants {

    Context context;
    List<ZProductDescriptionProductRatingObject.ProductRating> ratings;
    ZProductDescriptionProductRatingObject.RatingHeader header;

    public ZProductDescriptionProductRatingListAdapter(Context context, List<ZProductDescriptionProductRatingObject.ProductRating> ratings, ZProductDescriptionProductRatingObject.RatingHeader header) {
        this.context = context;
        this.ratings = ratings;
        if (header != null)
            this.header = header;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_product_description_product_rating_list_header_layout, parent, false);
            ProductRatingHeaderHolder holder = new ProductRatingHeaderHolder(v);
            return holder;
        } else if (viewType == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_product_description_product_rating_list_item_layout, parent, false);
            ProductRatingItemHolder holder = new ProductRatingItemHolder(v);
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_HEADER) {

        } else if (getItemViewType(position) == Z_PRODUCT_DESCRIPTION_PRODUCT_RATING_LIST_TYPE_ITEM) {

        }
    }

    @Override
    public int getItemCount() {
        return ratings.size() + 1;
    }

    class ProductRatingHeaderHolder extends RecyclerView.ViewHolder {

        public ProductRatingHeaderHolder(View v) {
            super(v);
        }
    }

    class ProductRatingItemHolder extends RecyclerView.ViewHolder {

        public ProductRatingItemHolder(View v) {
            super(v);
        }
    }
}
