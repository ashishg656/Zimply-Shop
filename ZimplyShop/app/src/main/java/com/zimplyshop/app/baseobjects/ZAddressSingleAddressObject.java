package com.zimplyshop.app.baseobjects;

import android.os.Parcel;
import android.os.Parcelable;

public class ZAddressSingleAddressObject implements Parcelable {

    String name;
    String address;
    String pincode;

    public ZAddressSingleAddressObject() {

    }

    protected ZAddressSingleAddressObject(Parcel in) {
        name = in.readString();
        address = in.readString();
        pincode = in.readString();
    }

    public static final Creator<ZAddressSingleAddressObject> CREATOR = new Creator<ZAddressSingleAddressObject>() {
        @Override
        public ZAddressSingleAddressObject createFromParcel(Parcel in) {
            return new ZAddressSingleAddressObject(in);
        }

        @Override
        public ZAddressSingleAddressObject[] newArray(int size) {
            return new ZAddressSingleAddressObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(pincode);
    }
}