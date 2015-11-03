package com.zimplyshop.app.extras;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by praveen goel on 11/2/2015.
 */
public class ZViewPagerNonScrollable extends ViewPager {

    public ZViewPagerNonScrollable(Context context) {
        super(context);
    }

    public ZViewPagerNonScrollable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
