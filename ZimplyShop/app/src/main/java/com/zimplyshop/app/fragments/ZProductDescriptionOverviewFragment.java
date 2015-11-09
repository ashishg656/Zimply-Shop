package com.zimplyshop.app.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
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
    FrameLayout viewPagerContainerLayout, floatingActionButtonBookContainer;
    FloatingActionButton floatingActionButtonBook;
    MyPagerAdapter viewPagerAdapter;
    TextView buyNowButton, addToCartButton;

    ZProductDescriptionProductOverviewObject productObject;
    LinearLayout addToCartButtonContainer;

    FloatingActionButtonProgressTask floatingActionButtonProgressTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_product_description_overview_fragment_layout, container, false);

        productImagesViewPager = (ViewPager) v.findViewById(R.id.pager_launch);
        circlePageIndicator = (CirclePageIndicator) v.findViewById(R.id.circlepageindicatorr);
        viewPagerContainerLayout = (FrameLayout) v.findViewById(R.id.viewpagercontainer);
        buyNowButton = (TextView) v.findViewById(R.id.buynowbuttonproductoverview);
        addToCartButton = (TextView) v.findViewById(R.id.addtocartbuttonproductoverview);
        floatingActionButtonBookContainer = (FrameLayout) v.findViewById(R.id.fabproductovercviewlayout);
        floatingActionButtonBook = (FloatingActionButton) v.findViewById(R.id.fabproductovercview);
        addToCartButtonContainer = (LinearLayout) v.findViewById(R.id.addtocartbuttonproductoverviewcontainer);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deviceWidth = getActivity().getResources().getDisplayMetrics().widthPixels;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewPagerContainerLayout.getLayoutParams();
        params.height = deviceWidth;
        viewPagerContainerLayout.setLayoutParams(params);

        floatingActionButtonBook.setOnClickListener(this);

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
                showFloatingActionButtonWithCancelButton();
                break;
            case R.id.buynowbuttonproductoverview:
                ((ZBaseActivity) getActivity()).openCartActivity();
                break;
            case R.id.fabproductovercview:
                if (floatingActionButtonProgressTask != null)
                    floatingActionButtonProgressTask.cancel(true);
                break;
        }
    }

    private void showFloatingActionButtonWithCancelButton() {
        floatingActionButtonProgressTask = new FloatingActionButtonProgressTask();
        floatingActionButtonProgressTask.execute();
    }

    class FloatingActionButtonProgressTask extends AsyncTask<Integer, Integer, Integer> {

        int progress = 0;

        @Override
        protected void onCancelled() {
            super.onCancelled();
            addToCartButtonContainer.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = 0;
            floatingActionButtonBook.setProgress(0, false);
            addToCartButtonContainer.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            addToCartButtonContainer.setVisibility(View.VISIBLE);
            showDialogForBookingDateTimeSelection();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            floatingActionButtonBook.setProgress(values[0], false);
            floatingActionButtonBook.invalidate();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            while (progress <= 100) {
                if (isCancelled())
                    break;
                progress++;
                publishProgress(progress);
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                }
            }
            return null;
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
