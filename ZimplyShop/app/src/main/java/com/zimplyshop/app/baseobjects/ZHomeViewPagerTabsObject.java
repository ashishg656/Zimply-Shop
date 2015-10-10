package com.zimplyshop.app.baseobjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZHomeViewPagerTabsObject implements Parcelable {

    List<ZHomeSingleViewPagerObject> viewPagerItems;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(viewPagerItems);
    }

    public ZHomeViewPagerTabsObject(Parcel in) {
        viewPagerItems = new ArrayList<>();
        in.readList(viewPagerItems, ZHomeSingleViewPagerObject.class.getClassLoader());
    }

    public static final Creator<ZHomeViewPagerTabsObject> CREATOR = new Creator<ZHomeViewPagerTabsObject>() {

        public ZHomeViewPagerTabsObject createFromParcel(Parcel in) {
            return new ZHomeViewPagerTabsObject(in);
        }

        public ZHomeViewPagerTabsObject[] newArray(int size) {
            return new ZHomeViewPagerTabsObject[size];
        }
    };

    public ZHomeViewPagerTabsObject() {

    }

    public List<ZHomeSingleViewPagerObject> getViewPagerItems() {
        return viewPagerItems;
    }

    public void setViewPagerItems(List<ZHomeSingleViewPagerObject> viewPagerItems) {
        this.viewPagerItems = viewPagerItems;
    }
}
