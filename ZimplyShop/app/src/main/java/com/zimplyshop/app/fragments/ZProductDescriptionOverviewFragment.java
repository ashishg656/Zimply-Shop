package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zimplyshop.app.R;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZProductDescriptionOverviewFragment extends ZBaseFragment {

    ImageView productImage;
    int deviceWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_product_description_overview_fragment_layout, container, false);

        productImage = (ImageView) v.findViewById(R.id.productimage);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deviceWidth = getActivity().getResources().getDisplayMetrics().widthPixels;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) productImage.getLayoutParams();
        params.height = deviceWidth;
        productImage.setLayoutParams(params);
    }
}
