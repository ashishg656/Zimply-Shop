package com.zimplyshop.app.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zimplyshop.app.R;
import com.zimplyshop.app.fragments.ZProductDescriptionStoreRatingFragment;

/**
 * Created by praveen goel on 10/10/2015.
 */
public class ZStoreRatingActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_store_rating_activity_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources()
                .getColor(R.color.PrimaryColor));
        getSupportActionBar().setTitle("Zimply");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = new Bundle();
        bundle.putInt("opened_from_store_rating_activity", 5);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentholder, ZProductDescriptionStoreRatingFragment.newInstance(new Bundle())).commit();
    }
}
