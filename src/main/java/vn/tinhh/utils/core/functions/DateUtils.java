package vn.tinhh.utils.core.functions;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public final String TYPE_DATE_HOUR_FULL = "dd/MM/yyyy HH:mm:ss";
    public final String TYPE_DATE_HOUR_FULL_2 = "yyyy-MM-dd HH:mm:ss";
    public final String TYPE_DATE_HOUR_FULL_3 = "HH:mm:ss dd/MM/yy";
    public final String TYPE_DATE_HOUR_MINUTE = "dd/MM/yyyy HH:mm";
    public final String TYPE_DATE_HOUR_MINUTE_2 = "HH:mm dd/MM/yyyy";
    public final String TYPE_DATE_HOUR_MINUTE_3 = "MM/dd/yyyy HH:mm";
    public final String TYPE_HOUR_MINUTE_DATE = "HH:mm dd/MM/yy";
    public final String TYPE_HOUR_MINUTE = "HH:mm";
    public final String TYPE_DATE_HOUR = "dd/MM/yyyy HH";
    public final String TYPE_MONTH_YEAR = "MM/yy";
    public final String TYPE_ONLY_DATE = "dd/MM/yyyy";
    public final String TYPE_ONLY_DATE_2 = "dd-MM-yyyy";
    public final String TYPE_ONLY_DATE_3 = "yyyy-MM-dd";
    public final String TYPE_ONLY_DATE_4 = "MM/yy";
    public final String TYPE_ONLY_DATE_5 = "MM/dd/yyyy HH:mm";

    public final String TYPE_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final String TYPE_UTC_2 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public final String MINUTE = "minute";
    public final String HOURLY = "hourly";
    public final String DAILY = "daily";
    public final String MONTHLY = "monthly";
    public final String YEARLY = "yearly";

    public String convertDateToStringUTC(String strDate, String type) {
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = isoFormat.parse(strDate);
            if (null != date) {
                DateFormat dateFormat = new SimpleDateFormat(type);
                return dateFormat.format(date);
            }
        } catch (Exception ex) {

        }
        return strDate;
    }

    public String convertDateToString(Date date, String type) {
        if (null != date) {
            DateFormat dateFormat = new SimpleDateFormat(type);
            return dateFormat.format(date);
        }
        return "";
    }

    public String convertDateToStringUTC(Date date, String type) {
        if (null != date) {
            DateFormat dateFormat = new SimpleDateFormat(type);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat.format(date);
        }
        return "";
    }

    public String convertCalendarToString(Calendar calendar, String type) {
        if (null != calendar) {
            Date date = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat(type);
            return dateFormat.format(date);
        }
        return "";
    }

    public String convertStringByType(String strDate, String type, String typeConvert) {
        if (null == strDate || strDate.length() == 0) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Date date = null;
        String dateStr = null;
        try {
            date = formatter.parse(strDate);
            formatter = new SimpleDateFormat(typeConvert);
            dateStr = formatter.format(date);
        } catch (Exception e) {
            date = null;
        }
        return dateStr;
    }

    public Date convertStringToDate(String strDate, String type) {
        if (null == strDate || strDate.length() == 0) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public Date convertStringUTCToDate(String strDate) {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat(TYPE_UTC);
        sourceFormat.setTimeZone(utc);
        Date date = null;
        try {
            date = sourceFormat.parse(strDate);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public Date customToDate(Date toDate) {
        if (null != toDate) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            toDate = cal.getTime();
        }
        return toDate;
    }

    public Date customFromDate(Date fromDate) {
        if (null != fromDate) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fromDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            fromDate = cal.getTime();
        }
        return fromDate;
    }

    public long customDateToLong(Date fromDate) {
        if (null != fromDate) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fromDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            return cal.getTime().getTime();
        }
        return 0;
    }

    public int getNumberDays(Timestamp start, Timestamp end) {
        if (null != start && null != end) {
            Date dStart = customFromDate(start);
            Date dEnd = customFromDate(end);
            long h = dEnd.getTime() - dStart.getTime();
            if (h > 0) {
                h = (((h / 1000) / 60) / 60) / 24;
                return (int) h;
            }
        }
        return 0;
    }

    public int getNumberDaysToExpired(Timestamp expireDate) {
        if (null != expireDate) {
            Date dStart = customFromDate(new Date());
            Date dEnd = customFromDate(expireDate);
            long h = dEnd.getTime() - dStart.getTime();
            if (h > 0) {
                h = (((h / 1000) / 60) / 60) / 24;
                return (int) h;
            }
        }
        return 0;
    }

    public int getNumberYear(Date start, Date end) {
        if (null != start && null != end) {
            Date dStart = customFromDate(start);
            Date dEnd = customFromDate(end);
            long h = dEnd.getTime() - dStart.getTime();
            if (h > 0) {
                h = (((h / 1000) / 60) / 60) / 24 / 365;
                return (int) h;
            }
        }
        return 0;
    }

    public int getNumberMonth(Date start, Date end) {
        if (null != start && null != end) {
            Date dStart = customFromDate(start);
            Date dEnd = customFromDate(end);
            long h = dEnd.getTime() - dStart.getTime();
            if (h > 0) {
                h = (((h / 1000) / 60) / 60) / 24 / 30;
                return (int) h;
            }
        }
        return 0;
    }

    public int getNumberDays(Date start, Date end) {
        if (null != start && null != end) {
            start = customFromDate(start);
            end = customFromDate(end);
            Timestamp timeStart = new Timestamp(start.getTime());
            Timestamp timeEnd = new Timestamp(end.getTime());
            return getNumberDays(timeStart, timeEnd);
        }
        return 0;
    }

    public int getNumberMinutes(Timestamp start, Timestamp end) {
        if (null != start && null != end) {
            long h = end.getTime() - start.getTime();
            if (h > 0) {
                h = (h / 1000) / 60;
                return (int) h;
            }
        }
        return 0;
    }

    public int getNumberHours(Timestamp start, Timestamp end) {
        if (null != start && null != end) {
            long h = end.getTime() - start.getTime();
            if (h > 0) {
                h = ((h / 1000) / 60) / 60;
                return (int) h;
            }
        }
        return 0;
    }

    public int getNumberMinutes(Date start, Date end) {
        if (null != start && null != end) {
            Timestamp timeStart = new Timestamp(start.getTime());
            Timestamp timeEnd = new Timestamp(end.getTime());
            return getNumberMinutes(timeStart, timeEnd);
        }
        return 0;
    }

    public int getNumberHours(Date start, Date end) {
        if (null != start && null != end) {
            Timestamp timeStart = new Timestamp(start.getTime());
            Timestamp timeEnd = new Timestamp(end.getTime());
            return getNumberHours(timeStart, timeEnd);
        }
        return 0;
    }

    public int timeOwe(Timestamp on, Timestamp off) {
        if (null != on && null != off) {
            long h = off.getTime() - on.getTime();
            if (h > 0) {
                h = (h / 1000) / 60;
                return (int) h;
            }
        }
        return 0;
    }

    public int timeOweSeconds(Timestamp on, Timestamp off) {
        if (null != on && null != off) {
            long h = off.getTime() - on.getTime();
            if (h > 0) {
                h = h / 1000;
                return (int) h;
            }
        }
        return 0;
    }

    public Date getDateAfterNumberTimes(Timestamp timestamp, int number, String type) {
        Calendar cal = Calendar.getInstance();
        if (null != timestamp) {
            cal.setTimeInMillis(timestamp.getTime());
        }
        if (type.equalsIgnoreCase(HOURLY)) {
            cal.add(Calendar.HOUR, +number);
        } else if (type.equalsIgnoreCase(DAILY)) {
            cal.add(Calendar.DAY_OF_YEAR, +number);
        } else if (type.equalsIgnoreCase(MONTHLY)) {
            cal.add(Calendar.MONTH, +number);
        } else if (type.equalsIgnoreCase(YEARLY)) {
            cal.add(Calendar.YEAR, +number);
        }
        return cal.getTime();
    }

    public Date getDateAfterNumberTimes(Date date, int number, String type) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {
            cal.setTimeInMillis(date.getTime());
        }
        if (type.equalsIgnoreCase(MINUTE)) {
            cal.add(Calendar.MINUTE, +number);
        } else if (type.equalsIgnoreCase(HOURLY)) {
            cal.add(Calendar.HOUR, +number);
        } else if (type.equalsIgnoreCase(DAILY)) {
            cal.add(Calendar.DAY_OF_YEAR, +number);
        } else if (type.equalsIgnoreCase(MONTHLY)) {
            cal.add(Calendar.MONTH, +number);
        } else if (type.equalsIgnoreCase(YEARLY)) {
            cal.add(Calendar.YEAR, +number);
        }
        return cal.getTime();
    }

    public Date getDateBeforeNumberTimes(Timestamp timestamp, int number, String type) {
        if (null != timestamp) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp.getTime());
            if (type.equalsIgnoreCase(HOURLY)) {
                cal.add(Calendar.HOUR, -number);
            } else if (type.equalsIgnoreCase(DAILY)) {
                cal.add(Calendar.DAY_OF_YEAR, -number);
            } else if (type.equalsIgnoreCase(MONTHLY)) {
                cal.add(Calendar.MONTH, -number);
            } else if (type.equalsIgnoreCase(YEARLY)) {
                cal.add(Calendar.YEAR, -number);
            }
            return cal.getTime();
        }
        return null;
    }

    public Date getDateBeforeNumberTimes(Date date, int number, String type) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {
            cal.setTimeInMillis(date.getTime());
        }
        if (type.equalsIgnoreCase(HOURLY)) {
            cal.add(Calendar.HOUR, -number);
        } else if (type.equalsIgnoreCase(DAILY)) {
            cal.add(Calendar.DAY_OF_YEAR, -number);
        } else if (type.equalsIgnoreCase(MONTHLY)) {
            cal.add(Calendar.MONTH, -number);
        } else if (type.equalsIgnoreCase(YEARLY)) {
            cal.add(Calendar.YEAR, -number);
        }

        return cal.getTime();
    }

    public int getNumberDaysBetween2Dates(Date start, Date end) throws Exception {
        if (null == start || null == end) return 0;
        start = customDate(start);
        end = customDate(end);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(start);
        c2.setTime(end);
        double noDay = 1.0 * (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        noDay = Math.round(noDay);
        return (int) noDay;
    }

    public int getNumberMinutesBetween2Dates(Date start, Date end) throws Exception {
        if (null == start || null == end) return 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(start);
        c2.setTime(end);
        double value = 1.0 * (c2.getTime().getTime() - c1.getTime().getTime()) / (60 * 1000);
        value = Math.round(value);
        return (int) value;
    }

    public Date customDate(Date fromDate) {
        if (null != fromDate) {
            SimpleDateFormat sdfd = new SimpleDateFormat("dd");
            int day = Integer.parseInt(sdfd.format(fromDate));
            SimpleDateFormat sdfm = new SimpleDateFormat("MM");
            int month = Integer.parseInt(sdfm.format(fromDate));
            SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
            int year = Integer.parseInt(sdfy.format(fromDate));

            Calendar cal = Calendar.getInstance();
            cal.setTime(fromDate);
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            fromDate = cal.getTime();
        }
        return fromDate;
    }

    public String getNameTimeType(String type) {
        if (null != type) {
            if (type.equalsIgnoreCase(HOURLY)) {
                return "Giờ";
            }
            if (type.equalsIgnoreCase(DAILY)) {
                return "Ngày";
            }
            if (type.equalsIgnoreCase(MONTHLY)) {
                return "Tháng";
            }
            if (type.equalsIgnoreCase(YEARLY)) {
                return "Năm";
            }
        }
        return "";
    }

    public Date getDayStartInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDayEndInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public int getDayStartOfMonth(int year, int month) {
        Date date = getDayStartInMonth(year, month);
        return getDay(date);
    }

    public int getDayEndOfMonth(int year, int month) {
        Date date = getDayEndInMonth(year, month);
        return getDay(date);
    }

    public Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDayStartInYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDayEndInYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.YEAR, year);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public boolean checkCompareHour(Timestamp lastUpdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(lastUpdate);
        Calendar now = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int hourNow = now.get(Calendar.HOUR_OF_DAY);
        if (hour == hourNow) {
            return true;
        }
        return false;
    }

    public int compareDate(Date from, Date to) {
        if (null != from && null != to) {
            if (from.before(to)) {
                return 1;
            }
            if (from.after(to)) {
                return -1;
            }
            return 0;
        }
        return -1;
    }

    public String convertTimeTToTimeLocation(String time) {
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = utcFormat.parse(time);

            DateFormat pstFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yy");

            return pstFormat.format(date);
        } catch (Exception ex) {

        }
        return "";
    }

    public String convertTimeTToTimeLocation(String time, String type) {
        try {
            DateFormat utcFormat = new SimpleDateFormat(type);
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = utcFormat.parse(time);

            DateFormat pstFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yy");

            return pstFormat.format(date);
        } catch (Exception ex) {

        }
        return "";
    }

    /**
     * 1 = CN; 2 = T2 ...; 7 = T7
     *
     * @return
     */
    public int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public int getDayOfWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getHourOfDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MINUTE);
    }

    public int getHour(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.MINUTE);
    }

    public int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    public int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {
            cal.setTimeInMillis(date.getTime());
        }
        return cal.get(Calendar.YEAR);
    }

    public int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {
            cal.setTimeInMillis(date.getTime());
        }
        return cal.get(Calendar.MONTH) + 1;
    }

    public int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {
            cal.setTimeInMillis(date.getTime());
        }
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    //lấy danh sách ngày theo thứ trong tháng
    //dayOfWeek: 1 = CN; 2 = T2 ...; 7 = T7
    //vd lấy các ngày thứ 3 trong tháng 4/2020
    public List<Integer> getDaysOfMonth(int year, int month, int dayOfWeek) {
        Date startMonth = getDayStartInMonth(year, month);
        Date endMonth = getDayEndInMonth(year, month);
        int dayStart = getDay(startMonth);
        int dayEnd = getDay(endMonth);
        List<Integer> days = new ArrayList<>();
        for (int d = dayStart; d <= dayEnd; d++) {
            int dayOfWeekItem = getDayOfWeek(year, month, d);
            if (dayOfWeekItem == dayOfWeek) {
                days.add(d);
            }
        }
        return days;
    }

    //lấy ngày trong tháng theo thứ và vị trí
    //vd lấy ngày thứ 3 đầu tiên trong tháng 4/2020
    //index = 1 = đầu tiên
    //index = 2 = second
    //index = 3 = third...
    public Integer getDaysOfMonth(int year, int month, int dayOfWeek, int index) {
        if (index > 5) return -1;
        Date startMonth = getDayStartInMonth(year, month);
        Date endMonth = getDayEndInMonth(year, month);
        int dayStart = getDay(startMonth);
        int dayEnd = getDay(endMonth);
        List<Integer> days = new ArrayList<>();
        for (int d = dayStart; d <= dayEnd; d++) {
            int dayOfWeekItem = getDayOfWeek(year, month, d);
            if (dayOfWeekItem == dayOfWeek) {
                if (days.size() == (index - 1)) return d;
                days.add(d);
            }
        }
        return -1;
    }

    public String getTimeName(long durationInMillis) {
        if (durationInMillis <= 0) return "";
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;
        if (hour > 0) {
            return String.format("%2d giờ %2d phút %2d giây", hour, minute, second);
        } else {
            return String.format("%2d phút %2d giây", minute, second);
        }
    }

    public List<String> getMonthList(int countMonth) {
        Date now = new Date();
        List<String> moths = new ArrayList<>();
        moths.add(convertDateToString(now, TYPE_ONLY_DATE_4));
        for (int i = 1; i < countMonth; i++) {
            Date date = getDateBeforeNumberTimes(now, i, MONTHLY);
            moths.add(convertDateToString(date, TYPE_ONLY_DATE_4));
        }
        return moths;
    }

    public List<String> getMonthsToPresent(Date date) {
        List<String> moths = new ArrayList<>();
        Date now = new Date();
        String temp = convertDateToString(date, TYPE_ONLY_DATE_4);
        moths.add(convertDateToString(now, TYPE_ONLY_DATE_4));
        while (!temp.equals(convertDateToString(now, TYPE_ONLY_DATE_4))) {
            now = getDateBeforeNumberTimes(now, 1, MONTHLY);
            moths.add(convertDateToString(now, TYPE_ONLY_DATE_4));
        }
        return moths;

    }

    public List<String> getMonthList(int countMonth, String dateFrom) {
        Date from = convertStringToDate(dateFrom, TYPE_ONLY_DATE_4);
        List<String> moths = new ArrayList<>();
        moths.add(convertDateToString(from, TYPE_ONLY_DATE_4));
        for (int i = 1; i < countMonth; i++) {
            Date date = getDateBeforeNumberTimes(from, i, MONTHLY);
            moths.add(convertDateToString(date, TYPE_ONLY_DATE_4));
        }
        return moths;
    }


    public List<String> getYearList(int count) {
        List<String> years = new ArrayList<>();
        int year = getYear(new Date());
        years.add(String.valueOf(year));
        for (int i = 0; i < count; i++) {
            year -= 1;
            years.add(String.valueOf(year));
        }
        return years;
    }

    public Date convertSecondsToDate(long time) {
        return new Date(TimeUnit.MILLISECONDS.convert(time, TimeUnit.SECONDS));
    }
}
