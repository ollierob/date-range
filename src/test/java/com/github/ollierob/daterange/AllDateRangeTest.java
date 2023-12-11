package com.github.ollierob.daterange;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AllDateRangeTest {

    @Test
    void shouldContainAnyDate() {

        final AllDateRange instance = new AllDateRange();

        assertTrue(instance.contains(LocalDate.MIN));
        assertTrue(instance.contains(LocalDate.now()));
        assertTrue(instance.contains(LocalDate.MAX));

    }

}