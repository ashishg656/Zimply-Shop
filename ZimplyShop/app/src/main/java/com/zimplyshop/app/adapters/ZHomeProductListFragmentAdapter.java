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
import com.zimplyshop.app.activities.ZBaseActivity;
import com.zimplyshop.app.fragments.ZHomeFragment;
import com.zimplyshop.app.activities.ZLoginActivity;
import com.zimplyshop.app.activities.ZProductDescriptionActivity;
import com.zimplyshop.app.baseobjects.ZProductListObject;
import com.zimplyshop.app.extras.ZAppConstants;
import com.zimplyshop.app.preferences.ZPreferences;

import java.util.List;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZHomeProductListFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ZAppConstants {

    Context context;
    List<ZProductListObject.ZProductObject> mData;
    int imageHeight;
    MyClickListener clickListener;

    public ZHomeProductListFragmentAdapter(Context context, List<ZProductListObject.ZProductObject> mData) {
        this.context = context;
        this.mData = mData;
        imageHeight = context.getResources().getDisplayMetrics().widthPixels / 2 - context.getResources().getDimensionPixelSize(R.dimen.z_margin_mini);
        clickListener = new MyClickListener();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.z_home_product_item_layout, parent, false);
        ZProductHolder holder = new ZProductHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderCom, int position) {
        ZProductHolder holder = (ZProductHolder) holderCom;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.productImage.getLayoutParams();
        params.height = imageHeight;
        holder.productImage.setLayoutParams(params);

        holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.mainContainer.setTag(holder);
        holder.mainContainer.setOnClickListener(clickListener);

        holder.wishlistLayout.setTag(holder);
        holder.wishlistLayout.setOnClickListener(clickListener);

        holder.cartLayout.setTag(holder);
        holder.cartLayout.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ZProductHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView mrp;
        FrameLayout mainContainer, wishlistLayout, cartLayout;

        public ZProductHolder(View v) {
            super(v);
            productImage = (ImageView) v.findViewById(R.id.productimage);
            mrp = (TextView) v.findViewById(R.id.mrptextproduct);
            mainContainer = (FrameLayout) v.findViewById(R.id.prodyctconatunre);
            wishlistLayout = (FrameLayout) v.findViewById(R.id.addtowishlist);
            cartLayout = (FrameLayout) v.findViewById(R.id.addtocartlayout);
        }
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.prodyctconatunre:
                    Intent i = new Intent(context, ZProductDescriptionActivity.class);
                    ZProductHolder holder = (ZProductHolder) v.getTag();
                    int pos = holder.getAdapterPosition();
                    i.putExtra("product_id", mData.get(pos).getId());
                    context.startActivity(i);
                    break;
                case R.id.addtowishlist:
                    holder = (ZProductHolder) v.getTag();
                    pos = holder.getAdapterPosition();
                    if (ZPreferences.isUserLogin(context)) {

                    } else {
                        i = new Intent(context, ZLoginActivity.class);
                        i.putExtra("wishlist_product_id", mData.get(pos).getId());
                        ((ZHomeFragment) context).startActivityForResult(i, Z_ADD_TO_WISHLIST_START_ACTIVITY_FOR_RESULT_REQUEST);
                    }
                    break;
                case R.id.addtocartlayout:
                    ((ZBaseActivity) context).openCartActivity();
                    break;
            }
        }
    }
}
