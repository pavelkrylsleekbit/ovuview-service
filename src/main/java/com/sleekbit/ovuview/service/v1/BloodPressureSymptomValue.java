package com.sleekbit.ovuview.service.v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.sleekbit.ovuview.service.Constants;
import com.sleekbit.ovuview.service.OvuUtil;

import java.util.UUID;

/**
 * Blood pressure value - composed value in a single object.
 *
 * Systolic pressure is present iff diastolic is present as well.
 * In such case, pressureOriginAppId is common for both systolic and diastolic pressure.
 *
 * Pulse might be set, in which case pulse origin app must be also set (on input).
 */
@SuppressWarnings("unused")
public class BloodPressureSymptomValue implements Parcelable {
    public static final int VALUE_NOT_SET = -1;
    // optional (mandatory if diastolic is present)
    public Integer systolic;
    // optional (mandatory if systolic is present)
    public Integer diastolic;
    // which app set the blood pressure
    public UUID pressureOriginAppId;
    // optional
    public Integer pulse;
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
        if (in.readInt() == 1) {
            // there is present systolic + diastolic
            systolic = in.readInt();
            diastolic = in.readInt();
            pressureOriginAppId = OvuUtil.readUuid(in);
        } else {
            systolic = null;
            diastolic = null;
            pressureOriginAppId = null;
        }
        // pulse
        if (in.readInt() == 1) {
            // there is pulse
            pulse = in.readInt();
            pulseOriginAppId = OvuUtil.readUuid(in);
        } else {
            pulse = null;
            pulseOriginAppId = null;
        }
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
        if ((systolic != null && diastolic == null) || (systolic == null && diastolic != null)) {
            // inconsistency
            throw new IllegalStateException("cannot serialize inconsistent blood pressure: " + this);
        }
        parcel.writeInt(systolic != null ? 1 : 0);
        if (systolic != null) {
            parcel.writeInt(systolic);
            parcel.writeInt(diastolic);
            parcel.writeString(pressureOriginAppId == null ? null : pressureOriginAppId.toString());
        }
        // pulse
        parcel.writeInt(pulse != null ? 1 : 0);
        if (pulse != null) {
            parcel.writeInt(pulse);
            parcel.writeString(pulseOriginAppId == null ? null : pulseOriginAppId.toString());
        }
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
