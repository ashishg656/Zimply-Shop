package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZDeliveryAddressActivity;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZConfirmAddressFragment extends ZBaseFragment implements View.OnClickListener {

    TextView showMoreAddressButton;

    public static ZConfirmAddressFragment newInstance(Bundle b) {
        ZConfirmAddressFragment frg = new ZConfirmAddressFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_address_confirm_address_fragment_layout, container, false);

        showMoreAddressButton = (TextView) v.findViewById(R.id.showmoreaddress);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showMoreAddressButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showmoreaddress:
                ((ZDeliveryAddressActivity) getActivity()).setSelectAddressFragment();
                break;
        }
    }
}
