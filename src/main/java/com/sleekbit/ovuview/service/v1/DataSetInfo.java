package com.sleekbit.ovuview.service.v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.sleekbit.ovuview.service.Constants;

/**
 * Owned data set information.
 */
public class DataSetInfo implements Parcelable {
    // unique dataset id
    public String id;
    // dataset label
    public String label;


    protected DataSetInfo(Parcel in) {
        in.readInt();   // throw away the version, we cannot do anything about it anyway
        id = in.readString();
        label = in.readString();

    }

    public DataSetInfo(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public static final Creator<DataSetInfo> CREATOR = new Creator<DataSetInfo>() {
        @Override
        public DataSetInfo createFromParcel(Parcel in) {
            return new DataSetInfo(in);
        }

        @Override
        public DataSetInfo[] newArray(int size) {
            return new DataSetInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Constants.PARCEL_VERSION);
        parcel.writeString(id);
        parcel.writeString(label);
    }

}
