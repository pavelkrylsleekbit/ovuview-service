package com.sleekbit.ovuview.service.v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.sleekbit.ovuview.service.Constants;
import com.sleekbit.ovuview.service.OvuUtil;

import java.util.UUID;

/**
 * Blood pressure value - composed value in a single object.
 *
 * -1 value means not set
 *
 * If systolic pressure is set, then diastolic pressure might be also set.
 * In such case, pressureOriginAppId is common for both systolic and diastolic pressure.
 *
 * Pulse might be set, in which case pulse origin app must be also set.
 */
@SuppressWarnings("unused")
public class BloodPressureSymptomValue implements Parcelable {
    public static final int VALUE_NOT_SET = -1;
    //
    public int systolic = -1;
    //
    public int diastolic = -1;
    // which app set the blood pressure
    public UUID pressureOriginAppId;
    //
    public int pulse = -1;
    // which app set the pulse
    public UUID pulseOriginAppId;


    public BloodPressureSymptomValue() {
    }

    protected BloodPressureSymptomValue(Parcel in) {
        readFromParcel(in);
    }

    @SuppressWarnings("WeakerAccess")
    public void readFromParcel(Parcel in) {
        in.readInt();   // throw away the version, we cannot do anything about it anyway
        systolic = in.readInt();
        diastolic = in.readInt();
        pulse = in.readInt();
        pressureOriginAppId = OvuUtil.readUuid(in);
        pulseOriginAppId = OvuUtil.readUuid(in);
    }

    public static final Creator<BloodPressureSymptomValue> CREATOR = new Creator<BloodPressureSymptomValue>() {
        @Override
        public BloodPressureSymptomValue createFromParcel(Parcel in) {
            return new BloodPressureSymptomValue(in);
        }

        @Override
        public BloodPressureSymptomValue[] newArray(int size) {
            return new BloodPressureSymptomValue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Constants.PARCEL_VERSION);
        parcel.writeInt(systolic);
        parcel.writeInt(diastolic);
        parcel.writeInt(pulse);
        parcel.writeString(pressureOriginAppId == null ? null : pressureOriginAppId.toString());
        parcel.writeString(pulseOriginAppId == null ? null : pulseOriginAppId.toString());
    }

    @Override
    public String toString() {
        return "BloodPressureSymptomValue{" +
                "systolic=" + systolic +
                ", diastolic=" + diastolic +
                ", pressureOriginAppId=" + pressureOriginAppId +
                ", pulse=" + pulse +
                ", pulseOriginAppId=" + pulseOriginAppId +
                '}';
    }
}
