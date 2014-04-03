package de.seliger.jtube.util;

import org.joda.time.*;
import org.joda.time.base.AbstractDateTime;
import org.joda.time.format.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatters {

    public static final DateTimeFormatter STANDARD = DateTimeFormat.forPattern("dd.MM.yyyy");
    public static final DateTimeFormatter MINUTES = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
    public static final DateTimeFormatter SECONDS = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
    public static final DateTimeFormatter GERMAN_DATETIME_MILLISECONDS = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss.SSS").withLocale(Locale.GERMAN);
    public static final DateTimeFormatter CONFIRMATION_DATE = DateTimeFormat.forPattern("yyyyMMddHHmmss").withLocale(Locale.GERMAN);


    public static String formatDateTimeToString(DateTime date) {
        return getDateFormatter().print(date);
    }

    public static String formatDateTimeToString(LocalDate date) {
        return formatDateTimeToString(date.toDateTimeAtStartOfDay());
    }

    public static String formatDateToString(AbstractDateTime date) {
        return getDateFormatter().print(date);
    }

    public static String formatDateToString(Date date) {
        return formatDateTimeToString(new DateTime(date));
    }

    public static String formatDateToString(LocalDate day) {
        return getDateFormatter().print(day);
    }

    public static DateTimeFormatter getDateFormatter() {
        return new DateTimeFormatterBuilder().appendDayOfMonth(2).appendLiteral(".").appendMonthOfYear(2)
                .appendLiteral(".").appendFixedDecimal(DateTimeFieldType.year(), 4).toFormatter();
    }

}
