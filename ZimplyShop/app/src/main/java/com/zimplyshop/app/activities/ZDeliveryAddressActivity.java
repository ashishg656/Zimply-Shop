package com.zimplyshop.app.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZAddressListingObject;
import com.zimplyshop.app.baseobjects.ZAddressSingleAddressObject;
import com.zimplyshop.app.fragments.ZAddAddressFragment;
import com.zimplyshop.app.fragments.ZAddressListFragment;
import com.zimplyshop.app.fragments.ZConfirmAddressFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZDeliveryAddressActivity extends ZBaseActivity {

    ZAddressListingObject addressObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_delivery_address_activity_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Address");

        addData();

        if (addressObject.getAddress().size() == 0) {
            setAddAddressFragment(false);
        } else {
            setConfirmAddressFragment();
        }
    }

    public void setConfirmAddressFragment() {
        Bundle b = new Bundle();
        b.putParcelable("addresslist", addressObject);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentholder, ZConfirmAddressFragment.newInstance(b)).commit();
    }

    public void setSelectAddressFragment() {
        Bundle b = new Bundle();
        b.putParcelable("addresslist", addressObject);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentholder, ZAddressListFragment.newInstance(b)).addToBackStack("select").commit();
    }

    public void setAddAddressFragment(boolean addToBackStack) {
        if (!addToBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentholder, ZAddAddressFragment.newInstance(new Bundle())).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentholder, ZAddAddressFragment.newInstance(new Bundle())).addToBackStack("add").commit();
        }
    }

    public void setEditAddressFragment(ZAddressSingleAddressObject obj) {
        Bundle b = new Bundle();
        b.putParcelable("addressobject", obj);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentholder, ZAddAddressFragment.newInstance(b)).addToBackStack("edit").commit();
    }

    private void addData() {
        addressObject = new ZAddressListingObject();
        List<ZAddressSingleAddressObject> adds = new ArrayList<>();
        adds.add(new ZAddressSingleAddressObject());
        adds.add(new ZAddressSingleAddressObject());
        adds.add(new ZAddressSingleAddressObject());
        adds.add(new ZAddressSingleAddressObject());
        addressObject.setAddress(adds);
    }
}
