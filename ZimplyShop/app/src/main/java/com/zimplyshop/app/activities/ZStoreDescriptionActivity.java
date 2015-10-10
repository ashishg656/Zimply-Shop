package com.zimplyshop.app.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zimplyshop.app.R;
import com.zimplyshop.app.adapters.ZStoreDescriptionListAdapter;
import com.zimplyshop.app.baseobjects.ZProductListObject;
import com.zimplyshop.app.baseobjects.ZStoreDescriptionObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/10/2015.
 */
public class ZStoreDescriptionActivity extends ZBaseActivity {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ZStoreDescriptionListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_store_description_activity_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.homeproductfragmentrecycler);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources()
                .getColor(R.color.PrimaryColor));
        getSupportActionBar().setTitle("Zimply");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                else
                    return 1;
            }
        });

        addData();
    }

    private void addData() {
        ZStoreDescriptionObject obj = new ZStoreDescriptionObject();
        List<ZProductListObject.ZProductObject> products = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ZProductListObject.ZProductObject temp = new ZProductListObject().new ZProductObject();
            products.add(temp);
        }
        obj.setProducts(products);

        obj.setHeaderData(new ZStoreDescriptionObject().new StoreHeaderObject());

        adapter = new ZStoreDescriptionListAdapter(this, obj.getProducts(), obj.getHeaderData());
        recyclerView.setAdapter(adapter);
    }
}
