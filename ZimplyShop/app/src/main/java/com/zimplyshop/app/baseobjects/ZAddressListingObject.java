package com.zimplyshop.app.baseobjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by praveen goel on 10/15/2015.
 */
public class ZAddressListingObject implements Parcelable {

    List<ZAddressSingleAddressObject> address;

    protected ZAddressListingObject(Parcel in) {
        address = in.createTypedArrayList(ZAddressSingleAddressObject.CREATOR);
    }

    public ZAddressListingObject() {

    }

    public static final Creator<ZAddressListingObject> CREATOR = new Creator<ZAddressListingObject>() {
        @Override
        public ZAddressListingObject createFromParcel(Parcel in) {
            return new ZAddressListingObject(in);
        }

        @Override
        public ZAddressListingObject[] newArray(int size) {
            return new ZAddressListingObject[size];
        }
    };

    public List<ZAddressSingleAddressObject> getAddress() {
        return address;
    }

    public void setAddress(List<ZAddressSingleAddressObject> address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(address);
    }
}
