package com.zimplyshop.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimplyshop.app.R;
import com.zimplyshop.app.activities.ZDeliveryAddressActivity;
import com.zimplyshop.app.baseobjects.ZAddressListingObject;
import com.zimplyshop.app.baseobjects.ZAddressSingleAddressObject;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZAddressListAdapter extends BaseAdapter {

    Context context;
    ZAddressListingObject mData;
    public int currentSelection = -1;
    MyClickListener clickListener;

    public ZAddressListAdapter(Context context, ZAddressListingObject mData) {
        this.context = context;
        this.mData = mData;
        clickListener = new MyClickListener();
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

        if (position == currentSelection) {
            holder.selectedAddressLayout.setVisibility(View.VISIBLE);
        } else {
            holder.selectedAddressLayout.setVisibility(View.GONE);
        }

        holder.useThisAddressButton.setTag(position);
        holder.useThisAddressButton.setOnClickListener(clickListener);

        holder.editAddress.setTag(position);
        holder.editAddress.setOnClickListener(clickListener);

        return convertView;
    }

    class AddressListHolder {

        TextView useThisAddressButton;
        FrameLayout selectedAddressLayout;
        LinearLayout editAddress;

        public AddressListHolder(View v) {
            useThisAddressButton = (TextView) v.findViewById(R.id.usethisaddressbutton);
            selectedAddressLayout = (FrameLayout) v.findViewById(R.id.selectedaddressbg);
            editAddress = (LinearLayout) v.findViewById(R.id.editaddress);
        }
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.usethisaddressbutton:
                    currentSelection = (int) v.getTag();
                    notifyDataSetChanged();
                    break;
                case R.id.editaddress:
                    int pos = (int) v.getTag();
                    ZAddressSingleAddressObject obj = mData.getAddress().get(pos);
                    ((ZDeliveryAddressActivity) context).setEditAddressFragment(obj);
                    break;
            }
        }
    }
}
