package com.maemresen.infsec.keylogapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Utility class for dateTime operations...
 *
 * @author mehmetkarakoc
 */
public class DateTimeHelper {

    /**
     * DateTime format for conversion... "'T'HH:mm:ss" may be added for the time
     * part...
     * <p>
     * dateFormat definition for the calendar...
     * <p>
     * Date date = formatter.parse(dateInString);
     * <p>
     * "EEE dd/MM/yyyy" --- "dd/MM/yyyy"
     * <p>
     * throws ParseException {
     */
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * calendar definition...
     */
    private final static Calendar calendar = Calendar.getInstance(new Locale("tr"));

    /**
     * retrieve today
     *
     * @return current day
     */
    public static Date getCurrentDay() {

        Date date = new Date();
        simpleDateFormat.format(date);
        return date;
    }

    /**
     * Get the date as a string value...
     *
     * @param baseDate given date
     * @return date in string format
     */
    public static String getTheDateInString(Date baseDate) {

        if (baseDate == null) {
            return "";
        }

        String dateString = simpleDateFormat.format(baseDate);

        return dateString == null ? "" : dateString;
    }

    public static Date getTheDateInDate(String baseDate) {

        if (baseDate == null) {
            return new Date();
        }

        Date date = null;

        try {
            date = simpleDateFormat.parse(baseDate);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date == null ? new Date() : date;
    }

    /**
     * get the date for Monday in current week...
     *
     * @param date base date
     * @return first day of the week
     */
    public static Date getMondayOfTheWeek(Date date) {
        return getDayOfTheWeek(date, 1);
    }

    public static String getFirstDayOfWeek(Date date) {
        return getTheDateInString(getMondayOfTheWeek(date));
    }

    /**
     * get the date for Sunday in current week...
     *
     * @param date base date
     * @return last day of the week
     */
    public static Date getSundayOfTheWeek(Date date) {
        return getDayOfTheWeek(date, 7);
    }

    public static String getLastDayOfWeek(Date date) {
        return getTheDateInString(getSundayOfTheWeek(date));
    }

    /**
     * get Monday or Sunday of the current week using today...
     *
     * @param date
     * @param daySort
     * @return
     */
    private static Date getDayOfTheWeek(Date date, int daySort) {

        calendar.setTime(date);

        if (daySort == 1) {

            // Set the calendar to MONDAY of the current week
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        } else if (daySort == 7) {

            // Set the calendar to SUNDAY of the current week
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        return calendar.getTime();
    }

    /**
     * get all the dates within this week starting on Monday...
     *
     * @return the days inside of the current week that we are in
     */
    public static List<String> getAllDaysOfTheWeek() {

        List<String> dateList = new ArrayList<String>();

        calendar.setTime(new Date()); // What day is today?

        /**
         * from what day...
         */
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        dateList.add(getTheDateInString(calendar.getTime()));

        /**
         * until the day Calendar.SUNDAY...
         */
        for (int i = 1; i <= 6; i++) {

            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(getTheDateInString(calendar.getTime()));
        }

        return dateList;
    }

    /* Akif - FormattedDateBuilder */

    public static String getFirstDayOfMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date dddd = calendar.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf1.format(dddd);
    }

    public static String getLastDayOfMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date dddd = calendar.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf1.format(dddd);
    }

    public static int getNumericLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentWeekNumber() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}