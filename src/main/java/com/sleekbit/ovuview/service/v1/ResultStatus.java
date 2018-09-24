package com.sleekbit.ovuview.service.v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.sleekbit.ovuview.service.Constants;

/**
 * Information about the result of the operation - whether it is valid or not.
 */
public class ResultStatus implements Parcelable {
    // constants
    public static final int STATUS_OK = 0;
    // other error codes follow
    public static final int STATUS_FAIL = 100;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // members
    // status
    public int status;
    // further human readable info
    public String explanation;


    public ResultStatus() {
    }

    protected ResultStatus(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        in.readInt();   // throw away the version, we cannot do anything about it anyway
        status = in.readInt();
        explanation = in.readString();
    }

    public static final Creator<ResultStatus> CREATOR = new Creator<ResultStatus>() {
        @Override
        public ResultStatus createFromParcel(Parcel in) {
            return new ResultStatus(in);
        }

        @Override
        public ResultStatus[] newArray(int size) {
            return new ResultStatus[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Constants.PARCEL_VERSION);
        parcel.writeInt(status);
        parcel.writeString(explanation);
    }

}
