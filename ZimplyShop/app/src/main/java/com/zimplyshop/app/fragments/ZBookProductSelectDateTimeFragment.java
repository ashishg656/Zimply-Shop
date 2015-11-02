package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.zimplyshop.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 11/2/2015.
 */
public class ZBookProductSelectDateTimeFragment extends ZBaseFragment {

    Spinner dateSpinner, timeSpinner;

    public static ZBookProductSelectDateTimeFragment newInstance(Bundle b) {
        ZBookProductSelectDateTimeFragment frg = new ZBookProductSelectDateTimeFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_book_product_select_date_time_fragment_layout, container, false);

        dateSpinner = (Spinner) v.findViewById(R.id.datespinnerprodycbook);
        timeSpinner = (Spinner) v.findViewById(R.id.timespinnerprodycbook);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addSampleData();
    }

    private void addSampleData() {
        List<String> dates = new ArrayList<>();
        dates.add("7 feb 2015");
        dates.add("8 feb 2015");
        dates.add("9 feb 2015");
        dates.add("10 feb 2015");

        List<String> times = new ArrayList<>();
        times.add("5-6 pm");
        times.add("6-7 pm");
        times.add("7-8 pm");
        times.add("8-9 pm");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, dates);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);
    }
}
