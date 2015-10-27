package com.zimplyshop.app.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by praveen goel on 10/26/2015.
 */
public class ZShopMapsActivityFrameLayout extends FrameLayout {

    public ZShopMapsActivityFrameLayout(Context context) {
        super(context);
    }

    public ZShopMapsActivityFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZShopMapsActivityFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return findViewById(R.id.map).onTouchEvent(event);
//    }
}
