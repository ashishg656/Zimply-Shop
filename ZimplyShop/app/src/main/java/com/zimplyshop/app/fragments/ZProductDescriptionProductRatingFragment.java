package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zimplyshop.app.R;
import com.zimplyshop.app.adapters.ZProductDescriptionProductRatingListAdapter;
import com.zimplyshop.app.baseobjects.ZProductDescriptionProductRatingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZProductDescriptionProductRatingFragment extends ZBaseFragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ZProductDescriptionProductRatingListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_product_description_product_rating_fragment_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.homeproductfragmentrecycler);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        addData();
    }

    private void addData() {
        List<ZProductDescriptionProductRatingObject.ProductRating> ratings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ratings.add((new ZProductDescriptionProductRatingObject()).new ProductRating());
        }
        ZProductDescriptionProductRatingObject.RatingHeader header = new ZProductDescriptionProductRatingObject().new RatingHeader();
        adapter = new ZProductDescriptionProductRatingListAdapter(getActivity(), ratings, header);
        recyclerView.setAdapter(adapter);
    }
}
