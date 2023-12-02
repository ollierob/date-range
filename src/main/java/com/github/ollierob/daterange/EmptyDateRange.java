package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class EmptyDateRange implements DateRange, Serializable {

    private static final long serialVersionUID = 154001019754347063L;

    static final EmptyDateRange INSTANCE = new EmptyDateRange();

    @Nonnull
    @Override
    public Optional<LocalDate> earliest() {
        return Optional.empty();
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.empty();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(@Nonnull final LocalDate date) {
        return false;
    }

    @Override
    public boolean intersects(@Nonnull final DateRange that) {
        return that.isEmpty();
    }

    @Nonnull
    @Override
    public DateRange shift(@Nonnull final Period period) {
        return this;
    }

    @Override
    public String toString() {
        return "(empty)";
    }

}
