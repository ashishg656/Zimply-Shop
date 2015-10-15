package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zimplyshop.app.R;
import com.zimplyshop.app.adapters.ZAddressListAdapter;
import com.zimplyshop.app.baseobjects.ZAddressListingObject;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZAddressListFragment extends ZBaseFragment {

    ListView listView;
    ZAddressListAdapter adapter;
    ZAddressListingObject addressObject;

    public static ZAddressListFragment newInstance(Bundle b) {
        ZAddressListFragment frg = new ZAddressListFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_address_list_fragment_layout, container, false);

        listView = (ListView) v.findViewById(R.id.listaddresslist);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addressObject = getArguments().getParcelable("addresslist");

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.z_address_list_list_header_layout, listView, false);
        listView.addHeaderView(headerView);

        adapter = new ZAddressListAdapter(getActivity(), addressObject);
        listView.setAdapter(adapter);
    }
}
