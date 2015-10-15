package com.zimplyshop.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zimplyshop.app.R;
import com.zimplyshop.app.baseobjects.ZAddressListingObject;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZAddressListAdapter extends BaseAdapter {

    Context context;
    ZAddressListingObject mData;

    public ZAddressListAdapter(Context context, ZAddressListingObject mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.getAddress().size();
    }

    @Override
    public Object getItem(int position) {
        return mData.getAddress().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddressListHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.z_address_list_list_item_layout, parent, false);
            holder = new AddressListHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (AddressListHolder) convertView.getTag();

        return convertView;
    }

    class AddressListHolder {

        public AddressListHolder(View v) {

        }
    }
}
