package com.zimplyshop.app.activities;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zimplyshop.app.R;
import com.zimplyshop.app.extras.ZAnimatorListener;
import com.zimplyshop.app.fragments.ZShopMapsShopFragment;
import com.zimplyshop.app.widgets.ActivitySwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZShopMapsActivity extends ZBaseActivity implements OnMapReadyCallback, ViewPager.OnPageChangeListener {

    List<String> shops;
    HashMap<Integer, Fragment> fragmentHashMap;
    MyPagerAdapter adapter;
    ViewPager viewPager;
    float pageWidthViewPager = 0.9f;
    View backgroundView;
    int deviceHeight;
    public int initialTopValueForBackgroundView = -1;
    private boolean isBackgroundViewAnimating;

    int statusBarHeight;
    View statusBarView;
    LinearLayout toolbarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_shop_maps_activity);

        statusBarView = (View) findViewById(R.id.viewforstatusbar);
        toolbarBackground = (LinearLayout) findViewById(R.id.toolbarbackgroundvoew);

        deviceHeight = getResources().getDisplayMetrics().heightPixels;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (checkIfLollipopOrGreater()) {
            statusBarHeight = getStatusBarHeight();
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) statusBarView.getLayoutParams();
            params.height = statusBarHeight;
            statusBarView.setLayoutParams(params);
        } else {
            statusBarView.setVisibility(View.GONE);
        }

        statusBarView.setAlpha(0);
        toolbarBackground.setAlpha(0);

        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        viewPager = (ViewPager) findViewById(R.id.pager_launch);
        backgroundView = (View) findViewById(R.id.backgroundview);

        backgroundView.setTranslationY(deviceHeight);

        fragmentHashMap = new HashMap<>();

        shops = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            shops.add("");

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.z_margin_medium));
        float paddingLeft = (1 - pageWidthViewPager) * getResources().getDisplayMetrics().widthPixels;
        viewPager.setPadding((int) paddingLeft, 0, (int) paddingLeft, 0);

        viewPager.addOnPageChangeListener(this);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    boolean checkIfLollipopOrGreater() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return true;
        return false;
    }

    @Override
    protected void onResume() {
        ActivitySwitcher.animationIn(findViewById(R.id.shopactivitymapscontainer),
                getWindowManager());
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.z_home_activity_toolbar_menu, menu);
        return true;
    }

    private void setUpMapIfNeeded() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int checks[] = {viewPager.getCurrentItem() + 1, viewPager.getCurrentItem() - 1};
        for (int i : checks) {
            try {
                if (fragmentHashMap.get(i) != null) {
                    ((ZShopMapsShopFragment) fragmentHashMap.get(i)).recyclerView.scrollToPosition(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment fragment = ZShopMapsShopFragment.newInstance(bundle);
            fragmentHashMap.put(position, fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return shops.size();
        }
    }

    public void setBackgroundViewTranslation(int trans) {
        backgroundView.setTranslationY(trans);
        if (trans == 0) {
            statusBarView.setAlpha(1);
            toolbarBackground.setAlpha(1);
        }
    }

    public void setBackgroundViewTranslationBasedOnItemTop(int top) {
        if (top < 0)
            top = 0;
        if (top == initialTopValueForBackgroundView) {
            statusBarView.setAlpha(0);
            toolbarBackground.setAlpha(0);
            if (!isBackgroundViewAnimating) {
                backgroundView.animate().translationY(deviceHeight).setDuration(300).setInterpolator(new AccelerateInterpolator()).setListener(new ZAnimatorListener() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isBackgroundViewAnimating = false;
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        isBackgroundViewAnimating = true;
                    }
                }).start();
            }
        } else {
            float trans = mapValuesFromSet(0, initialTopValueForBackgroundView, 0, deviceHeight, top);
            backgroundView.setTranslationY(trans);

            float alpha = mapValuesFromSet(0, initialTopValueForBackgroundView, 1, 0, top);
            statusBarView.setAlpha(alpha);
            toolbarBackground.setAlpha(alpha);
        }
    }

    public float mapValuesFromSet(float oldMin, float oldMax, float newMin, float newMax, float valueToChange) {
        float value = (newMax - newMin) * (valueToChange - oldMin) / (oldMax - oldMin) + newMin;
        return value;
    }
}
