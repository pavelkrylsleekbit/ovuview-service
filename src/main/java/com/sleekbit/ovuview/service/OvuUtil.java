package com.sleekbit.ovuview.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * General utility methods usable by client apps.
 */
@SuppressWarnings("WeakerAccess")
public class OvuUtil {

    private static final long stmEpochInMilli;
    
    static {
        final Calendar stmEpoch = Calendar.getInstance();
        stmEpoch.set(Calendar.MILLISECOND, 0);
        stmEpoch.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        // Get the epoch time in our time zone
        stmEpochInMilli = stmEpoch.getTimeInMillis();
    }

    public static UUID readUuid(Parcel in) {
        String uidAsString = in.readString();
        return uidAsString != null ? UUID.fromString(uidAsString) : null;
    }

    public static int calendarToOvuDate(Calendar cal) {
        return dateToOvuDate(cal.getTime());
    }

    public static int dateToOvuDate(Date date) {
        Calendar c = getWorkCalendar();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 3);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return millisToOvuDate(c.getTimeInMillis());
    }

    private static ThreadLocal<Calendar> workCalendar = new ThreadLocal<>();

    private static Calendar getWorkCalendar() {
        Calendar c = workCalendar.get();
        if (c == null) {
            c = Calendar.getInstance();
            workCalendar.set(c);
        }
        return c;
    }

    public static int millisToOvuDate(long time) {
        final long delta = time - stmEpochInMilli;
        int retVal = (int) (delta / 1000 / 86400);
        if (delta < 0) {
            retVal--;
        }
        return retVal;
    }

    public static int ovuTimeFromHoursMinutes(int hours, int minutes) {
        return hours * 60 + minutes;
    }

    /**
     * Converts time in stm representation into hours and minutes.
     * @param ovuTime time to be converted (stm representation)
     * @param hoursMinutes array where first element will be filled with hours
     * and second with minutes
     */
    public static void ovuTimeToHoursMinutes(int ovuTime, int[] hoursMinutes) {
        if (hoursMinutes == null || hoursMinutes.length != 2) {
            throw new IllegalStateException("unexpected hoursMinutes");
        }
        int hours = ovuTime / 60;
        hoursMinutes[1] = ovuTime - hours * 60;
        hoursMinutes[0] = hours;
    }

}
