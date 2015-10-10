package com.zimplyshop.app.baseobjects;

import android.os.Parcel;
import android.os.Parcelable;

public class ZHomeSingleViewPagerObject implements Parcelable {

    String name;
    String id;

    public static final Creator<ZHomeSingleViewPagerObject> CREATOR = new Creator<ZHomeSingleViewPagerObject>() {
        @Override
        public ZHomeSingleViewPagerObject createFromParcel(Parcel in) {
            return new ZHomeSingleViewPagerObject(in);
        }

        @Override
        public ZHomeSingleViewPagerObject[] newArray(int size) {
            return new ZHomeSingleViewPagerObject[size];
        }
    };

    public ZHomeSingleViewPagerObject() {

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    public ZHomeSingleViewPagerObject(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }
}