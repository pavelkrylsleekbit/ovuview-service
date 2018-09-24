package com.sleekbit.ovuview.service.v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.sleekbit.ovuview.service.Constants;
import com.sleekbit.ovuview.service.OvuUtil;

import java.util.UUID;

/**
 * Weight symptom value along with origin app id.
 */
@SuppressWarnings("unused")
public class WeightSymptomValue implements Parcelable {
    // weight in kilograms
    public float weight;
    // which app set the value
    public UUID originAppId;


    public WeightSymptomValue() {
    }

    protected WeightSymptomValue(Parcel in) {
        readFromParcel(in);
    }

    @SuppressWarnings("WeakerAccess")
    public void readFromParcel(Parcel in) {
        in.readInt();   // throw away the version, we cannot do anything about it anyway
        weight = in.readFloat();
        originAppId = OvuUtil.readUuid(in);
    }

    public static final Creator<WeightSymptomValue> CREATOR = new Creator<WeightSymptomValue>() {
        @Override
        public WeightSymptomValue createFromParcel(Parcel in) {
            return new WeightSymptomValue(in);
        }

        @Override
        public WeightSymptomValue[] newArray(int size) {
            return new WeightSymptomValue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Constants.PARCEL_VERSION);
        parcel.writeFloat(weight);
        parcel.writeString(originAppId == null ? null : originAppId.toString());
    }

    @Override
    public String toString() {
        return "WeightSymptomValue{" +
                "weight=" + weight +
                ", originAppId=" + originAppId +
                '}';
    }
}
