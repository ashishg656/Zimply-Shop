package com.zimplyshop.app.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.zimplyshop.app.R;
import com.zimplyshop.app.adapters.ZHomeProductListFragmentAdapter;
import com.zimplyshop.app.baseobjects.ZProductListObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by praveen goel on 10/19/2015.
 */
public class ZWishlistActivity extends ZBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ZHomeProductListFragmentAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_wishlist_activity_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Wishlist");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.homeproductfragmentrecycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.z_swipe_refresh_color_1, R.color.z_swipe_refresh_color_2,
                R.color.z_swipe_refresh_color_3);
        swipeRefreshLayout.setProgressViewOffset(
                false,
                getResources().getDimensionPixelSize(R.dimen.z_swipe_refresh_start_pos_small),
                getResources().getDimensionPixelSize(
                        R.dimen.z_swipe_refresh_rest_pos_small));

        addData();
    }

    private void addData() {
        ZProductListObject mData = new ZProductListObject();
        List<ZProductListObject.ZProductObject> products = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ZProductListObject.ZProductObject temp = mData.new ZProductObject();
            products.add(temp);
        }
        mData.setProducts(products);

        adapter = new ZHomeProductListFragmentAdapter(this, mData.getProducts());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ZWishlistActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ZWishlistActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }
}
