package com.softuni.sportify.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MonthStrings {

    private static final String JANUARY = "January";
    private static final String FEBRUARY = "February";
    private static final String MARCH = "March";
    private static final String APRIL = "April";
    private static final String MAY = "May";
    private static final String JUNE = "June";
    private static final String JULY = "July";
    private static final String AUGUST = "August";
    private static final String SEPTEMBER = "September";
    private static final String OCTOBER = "October";
    private static final String NOVEMBER = "November";
    private static final String DECEMBER = "December";

    public static final List<String> MONTH_STRINGS = new ArrayList<>(
            Arrays.asList(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY,
                    AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER));
}
