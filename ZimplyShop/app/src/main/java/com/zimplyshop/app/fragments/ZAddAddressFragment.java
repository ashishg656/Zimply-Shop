package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZAddressSingleAddressObject;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZAddAddressFragment extends ZBaseFragment implements View.OnClickListener {

    ZAddressSingleAddressObject editAddressObject;
    TextView cancelButton;

    public static ZAddAddressFragment newInstance(Bundle b) {
        ZAddAddressFragment frg = new ZAddAddressFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_add_address_fragment_layout, container, false);

        cancelButton = (TextView) v.findViewById(R.id.canceladdingaddress);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments().getParcelable("addressobject") != null) {
            editAddressObject = getArguments().getParcelable("addressobject");
        }

        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.canceladdingaddress:
                getActivity().onBackPressed();
                break;
        }
    }
}
