package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZShopMapsActivity;
import com.zimplyshop.app.adapters.ZShopMapsShopFragmentListAdapter;
import com.zimplyshop.app.baseobjects.ZShopMapsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/26/2015.
 */
public class ZShopMapsShopFragment extends ZBaseFragment {

    public RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ZShopMapsShopFragmentListAdapter adapter;
    int recyclerViewTopPadding;

    public static ZShopMapsShopFragment newInstance(Bundle b) {
        ZShopMapsShopFragment frg = new ZShopMapsShopFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_shop_maps_shop_fragment_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.homeproductfragmentrecycler);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewTopPadding = getActivity().getResources().getDisplayMetrics().heightPixels / 2;
        recyclerView.setPadding(0, recyclerViewTopPadding, 0, 0);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                return 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.findViewHolderForAdapterPosition(0) != null && recyclerView.findViewHolderForAdapterPosition(0).itemView != null) {
                    int top = recyclerView.findViewHolderForAdapterPosition(0).itemView.getTop();
                    if (top > 0 && ((ZShopMapsActivity) getActivity()).initialTopValueForBackgroundView == -1) {
                        ((ZShopMapsActivity) getActivity()).initialTopValueForBackgroundView = top;
                    }
                    Log.w("as", " top  " + top);
                    if (top < 0) {
                        ((ZShopMapsActivity) getActivity()).setBackgroundViewTranslation(0);
                    } else {
                        ((ZShopMapsActivity) getActivity()).setBackgroundViewTranslationBasedOnItemTop(top);
                    }
                } else {
                    ((ZShopMapsActivity) getActivity()).setBackgroundViewTranslation(0);
                }
            }
        });

        List<ZShopMapsObject.MapsShopProductObject> products = new ArrayList<>();
        for (int i = 0; i < 30; i++)
            products.add(new ZShopMapsObject().new MapsShopProductObject());
        adapter = new ZShopMapsShopFragmentListAdapter(getActivity(), products);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            ((ZShopMapsActivity) getActivity()).setBackgroundViewTranslationBasedOnItemTop(((ZShopMapsActivity) getActivity()).initialTopValueForBackgroundView);
        }
    }
}
