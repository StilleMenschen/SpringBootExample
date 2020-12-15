package tech.tystnad.works.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static Date get(Date date, int h, int m, int s, int ms) {
        Calendar calendar = new Calendar.Builder().setInstant(date).build();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        calendar.set(Calendar.SECOND, s);
        calendar.set(Calendar.MILLISECOND, ms);
        return calendar.getTime();
    }

    public static Timestamp get(Timestamp date, int h, int m, int s, int ms) {
        Calendar calendar = new Calendar.Builder().setInstant(date).build();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        calendar.set(Calendar.SECOND, s);
        calendar.set(Calendar.MILLISECOND, ms);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
