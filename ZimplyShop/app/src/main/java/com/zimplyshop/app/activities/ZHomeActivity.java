package com.zimplyshop.app.activities;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZHomeViewPagerTabsObject;
import com.zimplyshop.app.extras.ZAnimatorListener;
import com.zimplyshop.app.extras.ZAppConstants;
import com.zimplyshop.app.fragments.ZHomeProductListFragment;

import java.util.HashMap;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZHomeActivity extends ZBaseActivity implements ViewPager.OnPageChangeListener, ZAppConstants, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ZHomeViewPagerTabsObject zHomeViewPagerTabsObject;
    ViewPager viewPager;
    ZHomeActivityPagerAdapter pagerAdapter;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    public static final int TRANSLATION_DURATION = 200;
    boolean isToolbarAnimRunning;

    LinearLayout searchBarLayout;
    HashMap<Integer, Fragment> fragmentHashMap;
    int materialButtonHeight;
    int searchButtonCenterX, searchButtonCenterY;
    int deviceWidth;
    int searchAnimDuration = 300;
    FrameLayout searchBarBackButton, searchClearButton;
    EditText searchEditText;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_home_activity_layout);

        fragmentHashMap = new HashMap<>();
        materialButtonHeight = getResources().getDimensionPixelSize(R.dimen.z_button_height);
        deviceWidth = getResources().getDisplayMetrics().widthPixels;

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.pager_launch);
        tabLayout = (TabLayout) findViewById(R.id.indicator);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchBarLayout = (LinearLayout) findViewById(R.id.searchlayout);
        searchBarBackButton = (FrameLayout) findViewById(R.id.searchbackbutton);
        searchClearButton = (FrameLayout) findViewById(R.id.crossbuttonhome);
        searchEditText = (EditText) findViewById(R.id.searchtexthomeactivity);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_normal);

        searchBarBackButton.setOnClickListener(this);
        searchClearButton.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

        searchBarLayout.setVisibility(View.GONE);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    searchClearButton.setVisibility(View.INVISIBLE);
                } else {
                    searchClearButton.setVisibility(View.VISIBLE);
                }
            }
        });

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.searchbackbutton) {
            onBackPressed();
        } else if (v.getId() == R.id.crossbuttonhome) {
            searchEditText.setText("");
            searchClearButton.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.fab_normal) {
            Intent i = new Intent(this, ZShopMapsActivity.class);
            startActivity(i);
        }
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

            Fragment fragment = ZHomeProductListFragment.newInstance(bundle);
            fragmentHashMap.put(position, fragment);
            return fragmentHashMap.get(position);
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
            showSearchBarLayout();
            return true;
        } else if (id == R.id.action_filter) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchBarLayout() {
        if (searchButtonCenterY == 0) {
            int loc[] = new int[2];
            findViewById(R.id.action_search).getLocationInWindow(loc);
            searchButtonCenterY = loc[1];
            searchButtonCenterX = loc[0] + findViewById(R.id.action_search).getWidth() / 2;
        }

        searchBarLayout.setVisibility(View.VISIBLE);
        searchClearButton.setVisibility(View.INVISIBLE);
        searchEditText.setText("");

        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);

        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(searchBarLayout, searchButtonCenterX, searchButtonCenterY, 0, deviceWidth);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(searchAnimDuration);
        animator.start();

        appBarLayout.setVisibility(View.GONE);
        for (int i = 0; i < fragmentHashMap.size(); i++) {
            if (fragmentHashMap.get(i) != null) {
                ((ZHomeProductListFragment) fragmentHashMap.get(i)).recyclerView.scrollBy(0, materialButtonHeight);
            }
        }
    }

    void hideSearchBarLayout() {
        appBarLayout.setVisibility(View.VISIBLE);

        searchEditText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(searchBarLayout, searchButtonCenterX, searchButtonCenterY, deviceWidth, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(searchAnimDuration);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                searchBarLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel() {
                searchBarLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat() {

            }
        });
        animator.start();

        for (int i = 0; i < fragmentHashMap.size(); i++) {
            if (fragmentHashMap.get(i) != null) {
                ((ZHomeProductListFragment) fragmentHashMap.get(i)).recyclerView.scrollBy(0, -materialButtonHeight);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (searchBarLayout.getVisibility() == View.VISIBLE) {
            hideSearchBarLayout();
        } else {
            super.onBackPressed();
        }
    }
}
