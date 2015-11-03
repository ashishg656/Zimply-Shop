package com.zimplyshop.app.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zimplyshop.app.R;
import com.zimplyshop.app.adapters.ZHomeProductListFragmentAdapter;
import com.zimplyshop.app.baseobjects.ZProductListObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZHomeProductListFragment extends ZBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public RecyclerView recyclerView;
    public GridLayoutManager layoutManager;
    ZHomeProductListFragmentAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public static ZHomeProductListFragment newInstance(Bundle b) {
        ZHomeProductListFragment frg = new ZHomeProductListFragment();
        frg.setArguments(b);
        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.z_home_product_list_fragment_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.homeproductfragmentrecycler);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.z_swipe_refresh_color_1, R.color.z_swipe_refresh_color_2,
                R.color.z_swipe_refresh_color_3);
        swipeRefreshLayout.setProgressViewOffset(
                false,
                getResources().getDimensionPixelSize(R.dimen.z_swipe_refresh_start_pos),
                getResources().getDimensionPixelSize(
                        R.dimen.z_swipe_refresh_rest_pos));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int pos = layoutManager.findFirstVisibleItemPosition();
                    if (pos == 0) {
                        ((ZHomeFragment) getActivity()).setToolbarTranslation(recyclerView
                                .getChildAt(0));
                    } else
                        ((ZHomeFragment) getActivity()).scrollToolbarAfterTouchEnds();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ((ZHomeFragment) getActivity()).scrollToolbarBy(-dy);
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        addData();
    }

    private void addData() {
        ZProductListObject mData = new ZProductListObject();
        List<ZProductListObject.ZProductObject> products = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ZProductListObject.ZProductObject temp = mData.new ZProductObject();
            products.add(temp);
        }
        mData.setProducts(products);

        adapter = new ZHomeProductListFragmentAdapter(getActivity(), mData.getProducts());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }
}
