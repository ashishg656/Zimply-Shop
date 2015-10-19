package com.zimplyshop.app.activities;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZHomeViewPagerTabsObject;
import com.zimplyshop.app.extras.ZAnimatorListener;
import com.zimplyshop.app.extras.ZAppConstants;
import com.zimplyshop.app.fragments.ZHomeProductListFragment;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZHomeActivity extends ZBaseActivity implements ViewPager.OnPageChangeListener, ZAppConstants {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ZHomeViewPagerTabsObject zHomeViewPagerTabsObject;
    ViewPager viewPager;
    ZHomeActivityPagerAdapter pagerAdapter;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    public static final int TRANSLATION_DURATION = 200;
    boolean isToolbarAnimRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_home_activity_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.pager_launch);
        tabLayout = (TabLayout) findViewById(R.id.indicator);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

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

        zHomeViewPagerTabsObject = getIntent().getParcelableExtra("tabs_object");

        setDrawerActionBarToggle();
        setDrawerItemClickListener();

        viewPager.addOnPageChangeListener(this);

        pagerAdapter = new ZHomeActivityPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setDrawerItemClickListener() {
        navigationView
                .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        switch (item.getItemId()) {
                            case R.id.wishlist_navdrawer:
                                openWishlistActivity();
                                return true;

                            default:
                                return true;
                        }
                    }
                });
    }

    private void setDrawerActionBarToggle() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.z_open_drawer,
                R.string.z_close_drawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setItemIconTintList(null);
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

    class ZHomeActivityPagerAdapter extends FragmentPagerAdapter {

        public ZHomeActivityPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return zHomeViewPagerTabsObject.getViewPagerItems().get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("page_id", zHomeViewPagerTabsObject.getViewPagerItems().get(position).getId());
            return ZHomeProductListFragment.newInstance(bundle);
        }

        @Override
        public int getCount() {
            return zHomeViewPagerTabsObject.getViewPagerItems().size();
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Z_ADD_TO_WISHLIST_START_ACTIVITY_FOR_RESULT_REQUEST && resultCode == RESULT_OK) {
            int wishlistBookID = data.getExtras().getInt("wishlist_product_id");
            Toast.makeText(this, "Id was " + wishlistBookID, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.z_home_activity_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            openCartActivity();
            return true;
        } else if (id == R.id.action_search) {

            return true;
        } else if (id == R.id.action_filter) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
