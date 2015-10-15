package com.zimplyshop.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZBaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    int toolbarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void openCartActivity() {
        Intent i = new Intent(this, ZCartActivity.class);
        startActivity(i);
    }
}
