package com.robbie.mvc.converters;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class DateConverter implements Converter<String, Date>{
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    public Date convert(String source) {
        System.out.println("=====Converting input date======");
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_PATTERN);
        return dateTimeFormatter.parseDateTime(source).toDate();
    }
}
