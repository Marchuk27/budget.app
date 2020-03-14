package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


import static java.util.GregorianCalendar.YEAR;
import static java.util.GregorianCalendar.DAY_OF_MONTH;
import static java.util.GregorianCalendar.MONTH;
import static java.util.GregorianCalendar.HOUR_OF_DAY;
import static java.util.GregorianCalendar.MINUTE;

public class DateUtils {
    public static String getCurrentDateInCorrectFormat() {
        Calendar calendar = GregorianCalendar.getInstance(Locale.ROOT);
        int monthNumber = calendar.get(MONTH) + 1;
        String monthValues = (monthNumber <10) ? "0" + monthNumber : String.valueOf(monthNumber);
        return calendar.get(DAY_OF_MONTH) + "." + monthValues + "." + calendar.get(YEAR);
    }

    public static String getCurrentTime() {
        Calendar calendar = GregorianCalendar.getInstance(Locale.ROOT);
        int min = calendar.get(MINUTE);
        String minuteValues = (min < 10) ? "0" + min : String.valueOf(min);
        return calendar.get(HOUR_OF_DAY) + ":" + minuteValues;
    }
}
