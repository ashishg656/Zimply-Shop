package com.zimplyshop.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZBaseActivity;
import com.zimplyshop.app.activities.ZProductDescriptionActivity;
import com.zimplyshop.app.baseobjects.ZProductDescriptionProductOverviewObject;
import com.zimplyshop.app.widgets.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZProductDescriptionOverviewFragment extends ZBaseFragment implements View.OnClickListener {

    ViewPager productImagesViewPager;
    int deviceWidth;
    CirclePageIndicator circlePageIndicator;
    FrameLayout viewPagerContainerLayout;
    MyPagerAdapter viewPagerAdapter;
    TextView buyNowButton, addToCartButton;

    ZProductDescriptionProductOverviewObject productObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_product_description_overview_fragment_layout, container, false);

        productImagesViewPager = (ViewPager) v.findViewById(R.id.pager_launch);
        circlePageIndicator = (CirclePageIndicator) v.findViewById(R.id.circlepageindicatorr);
        viewPagerContainerLayout = (FrameLayout) v.findViewById(R.id.viewpagercontainer);
        buyNowButton = (TextView) v.findViewById(R.id.buynowbuttonproductoverview);
        addToCartButton = (TextView) v.findViewById(R.id.addtocartbuttonproductoverview);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deviceWidth = getActivity().getResources().getDisplayMetrics().widthPixels;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewPagerContainerLayout.getLayoutParams();
        params.height = deviceWidth;
        viewPagerContainerLayout.setLayoutParams(params);

        productObject = new ZProductDescriptionProductOverviewObject();
        List<String> image = new ArrayList<>();
        image.add("");
        image.add("");
        image.add("");
        image.add("");
        productObject.setProduct_images(image);

        viewPagerAdapter = new MyPagerAdapter(getActivity());
        productImagesViewPager.setAdapter(viewPagerAdapter);
        circlePageIndicator.setPageColor(getActivity().getResources().getColor(R.color.z_text_color_light));
        circlePageIndicator.setStrokeWidth(0);
        circlePageIndicator.setFillColor(getActivity().getResources().getColor(R.color.z_blue_color));
        circlePageIndicator.setViewPager(productImagesViewPager);

        addToCartButton.setOnClickListener(this);
        buyNowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addtocartbuttonproductoverview:
                showDialogForBookingDateTimeSelection();
                break;
            case R.id.buynowbuttonproductoverview:
                ((ZBaseActivity) getActivity()).openCartActivity();
                break;
        }
    }

    private void showDialogForBookingDateTimeSelection() {
        ((ZProductDescriptionActivity) getActivity()).showFragmentForSelectingDateTimeForProductBooking();
    }

    class MyPagerAdapter extends PagerAdapter {

        Context context;

        public MyPagerAdapter(Context c) {
            super();
            this.context = c;
        }

        @Override
        public int getCount() {
            return productObject.getProduct_images().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View imageLayout = LayoutInflater
                    .from(context)
                    .inflate(
                            R.layout.z_imageview_viewpager_item,
                            container, false);

            container.addView(imageLayout);
            return imageLayout;
        }
    }
}
