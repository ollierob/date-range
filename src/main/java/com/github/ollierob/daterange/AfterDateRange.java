package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

public class AfterDateRange implements DateRange, Serializable {

    private static final long serialVersionUID = -6455215487148810950L;

    private final LocalDate start;

    protected AfterDateRange(final LocalDate start) {
        this.start = start;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> earliest() {
        return Optional.of(start);
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "[" + start + ",..)";
    }

}
