package com.sleekbit.ovuview.service.v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.sleekbit.ovuview.service.Constants;
import com.sleekbit.ovuview.service.OvuUtil;

import java.util.UUID;

/**
 * Temperature symptom value - composed value in a single object.
 */
@SuppressWarnings("unused")
public class TemperatureSymptomValue implements Parcelable {
    // value in celsius degrees
    public double value;
    // true bbt
    public boolean trueBbt;
    // when was the temperature measured (-1 if time is not specified)
    public int time = -1;
    // which app set the value
    public UUID originAppId;


    public TemperatureSymptomValue() {
    }

    protected TemperatureSymptomValue(Parcel in) {
        readFromParcel(in);
    }

    public void setTime(int hrs, int mins) {
        if (hrs < 0 || hrs >= 24) {
            throw new IllegalStateException("hrs must be between 0..23");
        }
        if (mins < 0 || mins >= 60) {
            throw new IllegalStateException("minutes must be between 0..59");
        }
        time = OvuUtil.ovuTimeFromHoursMinutes(hrs, mins);
    }

    @SuppressWarnings("WeakerAccess")
    public void readFromParcel(Parcel in) {
        in.readInt();   // throw away the version, we cannot do anything about it anyway
        value = in.readDouble();
        trueBbt = in.readInt() == 1;
        time = in.readInt();
        originAppId = OvuUtil.readUuid(in);
    }

    public static final Creator<TemperatureSymptomValue> CREATOR = new Creator<TemperatureSymptomValue>() {
        @Override
        public TemperatureSymptomValue createFromParcel(Parcel in) {
            return new TemperatureSymptomValue(in);
        }

        @Override
        public TemperatureSymptomValue[] newArray(int size) {
            return new TemperatureSymptomValue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Constants.PARCEL_VERSION);
        parcel.writeDouble(value);
        parcel.writeInt(trueBbt ? 1 : 0);
        parcel.writeInt(time);
        parcel.writeString(originAppId == null ? null : originAppId.toString());
    }

    @Override
    public String toString() {
        return "TemperatureSymptomValue{" +
                "value=" + value +
                ", trueBbt=" + trueBbt +
                ", time=" + time +
                ", originAppId='" + originAppId + '\'' +
                '}';
    }
}
