package com.github.ollierob.daterange;

import java.time.LocalDate;

public class LocalDateUtils {

    private LocalDateUtils() {

    }

    public static LocalDate min(final LocalDate d1, final LocalDate d2) {
        return d1.isBefore(d2) ? d1 : d2;
    }

    public static LocalDate max(final LocalDate d1, final LocalDate d2) {
        return d1.isAfter(d2) ? d1 : d2;
    }

}
