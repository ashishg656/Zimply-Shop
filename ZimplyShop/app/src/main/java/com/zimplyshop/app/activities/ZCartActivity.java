package com.zimplyshop.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.zimplyshop.app.R;
import com.zimplyshop.app.adapters.ZCartListAdapter;
import com.zimplyshop.app.baseobjects.ZCartListObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/14/2015.
 */
public class ZCartActivity extends ZBaseActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ZCartListAdapter adapter;
    FrameLayout checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_cart_activity_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.cartrecycler);
        checkoutButton = (FrameLayout) findViewById(R.id.checkoutbutton);

        checkoutButton.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addData();
    }

    private void addData() {
        ZCartListObject data = new ZCartListObject();
        List<ZCartListObject.ZCartItem> cart = new ArrayList<>();
        cart.add(new ZCartListObject().new ZCartItem());
        cart.add(new ZCartListObject().new ZCartItem());
        cart.add(new ZCartListObject().new ZCartItem());
        cart.add(new ZCartListObject().new ZCartItem());
        cart.add(new ZCartListObject().new ZCartItem());
        data.setCart_items(cart);
        adapter = new ZCartListAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkoutbutton:
                Intent i = new Intent(this, ZDeliveryAddressActivity.class);
                startActivity(i);
                break;
        }
    }
}
