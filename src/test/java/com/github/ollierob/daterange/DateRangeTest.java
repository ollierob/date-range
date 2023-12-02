package com.github.ollierob.daterange;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateRangeTest {

    @Test
    void shouldCreateOnOrBefore() {

        final LocalDate date = LocalDate.now();

        final DateRange range = DateRange.onOrBefore(date);

        assertTrue(range.contains(date));
        assertFalse(range.contains(date.plusDays(1)));
        assertTrue(range.contains(date.minusDays(1)));

    }

}