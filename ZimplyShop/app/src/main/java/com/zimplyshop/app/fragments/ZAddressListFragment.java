package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZDeliveryAddressActivity;
import com.zimplyshop.app.adapters.ZAddressListAdapter;
import com.zimplyshop.app.baseobjects.ZAddressListingObject;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZAddressListFragment extends ZBaseFragment implements View.OnClickListener {

    ListView listView;
    ZAddressListAdapter adapter;
    ZAddressListingObject addressObject;
    FrameLayout selectAddressButton;

    public static ZAddressListFragment newInstance(Bundle b) {
        ZAddressListFragment frg = new ZAddressListFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_address_list_fragment_layout, container, false);

        listView = (ListView) v.findViewById(R.id.listaddresslist);
        selectAddressButton = (FrameLayout) v.findViewById(R.id.selectaddressbutton);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addressObject = getArguments().getParcelable("addresslist");

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.z_address_list_list_header_layout, listView, false);
        listView.addHeaderView(headerView);
        headerView.findViewById(R.id.addaddresbutton).setOnClickListener(this);

        adapter = new ZAddressListAdapter(getActivity(), addressObject);
        listView.setAdapter(adapter);

        selectAddressButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectaddressbutton:
                if (adapter.currentSelection == -1) {
                    Toast.makeText(getActivity(), "Please select an address", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.addaddresbutton:
                ((ZDeliveryAddressActivity) getActivity()).setAddAddressFragment(true);
                break;
        }
    }
}
