package com.zimplyshop.app.activities;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;

import com.zimplyshop.app.R;
import com.zimplyshop.app.extras.ZAnimatorListener;
import com.zimplyshop.app.fragments.ZBookProductSelectDateTimeFragment;
import com.zimplyshop.app.fragments.ZProductDescriptionOverviewFragment;
import com.zimplyshop.app.fragments.ZProductDescriptionProductRatingFragment;
import com.zimplyshop.app.fragments.ZProductDescriptionStoreRatingFragment;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZProductDescriptionActivity extends ZBaseActivity implements ViewPager.OnPageChangeListener {

    int productId;
    ViewPager viewPager;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    public static final int TRANSLATION_DURATION = 200;
    boolean isToolbarAnimRunning;
    ZProductDescriptionPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_product_description_activity_layout);

        viewPager = (ViewPager) findViewById(R.id.pager_launch);
        tabLayout = (TabLayout) findViewById(R.id.indicator);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        productId = getIntent().getExtras().getInt("product_id");

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources()
                .getColor(R.color.PrimaryColor));
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onGlobalLayout() {
                        try {
                            toolbar.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        } catch (Exception e) {
                            toolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        toolbarHeight = toolbar.getHeight();
                    }
                });

        viewPager.addOnPageChangeListener(this);

        adapter = new ZProductDescriptionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (appBarLayout.getTranslationY() != 0 && !isToolbarAnimRunning) {
            animateToolbarLayout(0);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressLint("NewApi")
    public void scrollToolbarBy(int dy) {
        float requestedTranslation = appBarLayout.getTranslationY() + dy;
        if (requestedTranslation < -toolbarHeight) {
            requestedTranslation = -toolbarHeight;
            appBarLayout.setTranslationY(requestedTranslation);
        } else if (requestedTranslation > 0) {
            requestedTranslation = 0;
            appBarLayout.setTranslationY(requestedTranslation);
        } else if (requestedTranslation >= -toolbarHeight
                && requestedTranslation <= 0) {
            appBarLayout.setTranslationY(requestedTranslation);
        }
    }

    @SuppressLint("NewApi")
    public void scrollToolbarAfterTouchEnds() {
        float currentTranslation = -appBarLayout.getTranslationY();
        if (currentTranslation > toolbarHeight / 2) {
            animateToolbarLayout(-toolbarHeight);
        } else {
            animateToolbarLayout(0);
        }
    }

    public void setToolbarTranslation(View firstChild) {
        if (firstChild.getTop() > appBarLayout.getHeight()) {
            animateToolbarLayout(0);
        } else {
            scrollToolbarAfterTouchEnds();
        }
    }

    public void setToolbarTranslationFromPositionOfTopChildAfterTouchEnd(int pos) {
        if (pos > appBarLayout.getHeight()) {
            animateToolbarLayout(0);
        } else {
            scrollToolbarAfterTouchEnds();
        }
    }

    void animateToolbarLayout(int trans) {
        appBarLayout.animate().translationY(trans)
                .setDuration(TRANSLATION_DURATION).setInterpolator(new DecelerateInterpolator()).setListener(new ZAnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                isToolbarAnimRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isToolbarAnimRunning = false;
            }
        });
    }

    class ZProductDescriptionPagerAdapter extends FragmentPagerAdapter {

        String[] names;

        public ZProductDescriptionPagerAdapter(FragmentManager fm) {
            super(fm);
            names = getResources().getStringArray(R.array.z_product_description_tab_array);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new ZProductDescriptionOverviewFragment();
                    break;
                case 1:
                    fragment = new ZProductDescriptionProductRatingFragment();
                    break;
                case 2:
                    fragment = new ZProductDescriptionStoreRatingFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return names[position];
        }
    }

    public void showFragmentForSelectingDateTimeForProductBooking() {
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmenthodlerproductdesc, ZBookProductSelectDateTimeFragment.newInstance(bundle)).addToBackStack("TAG").commit();
    }
}
