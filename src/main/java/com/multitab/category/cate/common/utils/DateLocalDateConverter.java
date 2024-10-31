package com.multitab.category.cate.common.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateLocalDateConverter {
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}