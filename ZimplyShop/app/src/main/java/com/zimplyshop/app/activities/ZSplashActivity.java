package com.zimplyshop.app.activities;

import android.content.Intent;
import android.os.Bundle;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZHomeSingleViewPagerObject;
import com.zimplyshop.app.baseobjects.ZHomeViewPagerTabsObject;

import java.util.ArrayList;
import java.util.List;

public class ZSplashActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_splash_activity_layout);

        makeRequestForHomePagerTabNames();
    }

    private void makeRequestForHomePagerTabNames() {
        // TODO add request and then on request complete call intent
        addArbitraryDataAndCallActivity();
    }

    void addArbitraryDataAndCallActivity() {
        ZHomeViewPagerTabsObject obj = new ZHomeViewPagerTabsObject();
        List<ZHomeSingleViewPagerObject> items = new ArrayList<>();
        String[] names = {"Latest", "Kitchen", "Storage", "Light", "Wallpaper and Stickers", "Bedroom", "Bathroom", "Clock and Mirror", "Pillow & Rugs"};
        for (int i = 0; i < names.length; i++) {
            ZHomeSingleViewPagerObject temp = new ZHomeSingleViewPagerObject();
            temp.setName(names[i]);
            items.add(temp);
        }
        obj.setViewPagerItems(items);

        Intent intent = new Intent(this, ZHomeActivity.class);
        intent.putExtra("tabs_object", obj);
        startActivity(intent);
        this.finish();
    }
}
