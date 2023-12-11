package com.github.ollierob.daterange;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class BeforeDateRangeTest {

    @Test
    void shouldIntersect() {

        final LocalDate date = LocalDate.now();
        final BeforeDateRange before = new BeforeDateRange(date);

        {
            final DateRange empty = DateRange.none();
            assertSame(empty, before.intersection(empty));
        }

        {
            final DateRange after = DateRange.onOrAfter(date);
            assertEquals(DateRange.of(date), before.intersection(after));
        }

        {
            final DateRange before2 = DateRange.onOrBefore(date.plusDays(1));
            assertSame(before, before.intersection(before2));
        }

        {
            final DateRange on = DateRange.of(date);
            assertEquals(on, before.intersection(on));
        }

        {
            final DateRange all = DateRange.all();
            assertEquals(before, before.intersection(all));
        }

    }

}