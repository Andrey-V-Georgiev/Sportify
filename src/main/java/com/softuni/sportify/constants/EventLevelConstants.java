package com.softuni.sportify.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EventLevelConstants {

    public static final String BEGINNER = "Beginner";
    public static final String MEDIUM = "Medium";
    public static final String ADVANCED = "Advanced";
    public static final String PROFESSIONAL = "Professional";

    public static List<String> EVENT_LEVELS = new ArrayList<>(
            Arrays.asList(BEGINNER, MEDIUM, ADVANCED, PROFESSIONAL));
}

