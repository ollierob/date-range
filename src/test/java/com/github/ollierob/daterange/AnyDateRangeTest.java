package com.github.ollierob.daterange;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AnyDateRangeTest {

    @Test
    void shouldContainAnyDate() {

        final AnyDateRange instance = new AnyDateRange();

        assertTrue(instance.contains(LocalDate.MIN));
        assertTrue(instance.contains(LocalDate.now()));
        assertTrue(instance.contains(LocalDate.MAX));

    }

}