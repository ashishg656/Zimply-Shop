package com.zimplyshop.app.adapters;

import android.content.Context;
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
import com.zimplyshop.app.baseobjects.ZShopMapsObject;
import com.zimplyshop.app.extras.ZAppConstants;

import java.util.List;

/**
 * Created by praveen goel on 10/26/2015.
 */
public class ZShopMapsShopFragmentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ZAppConstants {

    Context context;
    int imageHeight;
    List<ZShopMapsObject.MapsShopProductObject> products;

    public ZShopMapsShopFragmentListAdapter(Context context, List<ZShopMapsObject.MapsShopProductObject> products) {
        this.context = context;
        this.products = products;
        imageHeight = context.getResources().getDisplayMetrics().widthPixels / 2 - context.getResources().getDimensionPixelSize(R.dimen.z_margin_mini);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return Z_SHOP_MAPS_SHOP_FRAGMENT_LIST_HEADER;
        else
            return Z_SHOP_MAPS_SHOP_FRAGMENT_LIST_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Z_SHOP_MAPS_SHOP_FRAGMENT_LIST_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_shop_maps_shop_fragment_list_header, parent, false);
            ShopMapsShopHeaderHolder holder = new ShopMapsShopHeaderHolder(v);
            return holder;
        } else if (viewType == Z_SHOP_MAPS_SHOP_FRAGMENT_LIST_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.z_home_product_item_layout_with_white_bg, parent, false);
            ShopMapsShopProductHolder holder = new ShopMapsShopProductHolder(v);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderCom, int position) {
        position = holderCom.getAdapterPosition();
        if (getItemViewType(position) == Z_SHOP_MAPS_SHOP_FRAGMENT_LIST_ITEM) {
            ShopMapsShopProductHolder holder = (ShopMapsShopProductHolder) holderCom;

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.productImage.getLayoutParams();
            params.height = imageHeight;
            holder.productImage.setLayoutParams(params);

            holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else if (getItemViewType(position) == Z_SHOP_MAPS_SHOP_FRAGMENT_LIST_HEADER) {
            ShopMapsShopHeaderHolder holder = (ShopMapsShopHeaderHolder) holderCom;
        }
    }

    @Override
    public int getItemCount() {
        return products.size() + 1;
    }

    class ShopMapsShopHeaderHolder extends RecyclerView.ViewHolder {

        FrameLayout containerLayout;

        public ShopMapsShopHeaderHolder(View v) {
            super(v);
            containerLayout = (FrameLayout) v.findViewById(R.id.headerlistshopmapsfragmentlist);
        }
    }

    class ShopMapsShopProductHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView mrp;
        FrameLayout mainContainer, wishlistLayout, cartLayout;

        public ShopMapsShopProductHolder(View v) {
            super(v);
            productImage = (ImageView) v.findViewById(R.id.productimage);
            mrp = (TextView) v.findViewById(R.id.mrptextproduct);
            mainContainer = (FrameLayout) v.findViewById(R.id.prodyctconatunre);
            wishlistLayout = (FrameLayout) v.findViewById(R.id.addtowishlist);
            cartLayout = (FrameLayout) v.findViewById(R.id.addtocartlayout);
        }
    }
}
