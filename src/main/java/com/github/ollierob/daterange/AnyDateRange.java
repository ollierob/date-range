package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class AnyDateRange implements DateRange, Serializable {

    private static final long serialVersionUID = 7178272465031806916L;

    static final AnyDateRange INSTANCE = new AnyDateRange();

    protected AnyDateRange() {
    }

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
    public boolean contains(@Nonnull final LocalDate date) {
        return true;
    }

    @Override
    public boolean intersects(@Nonnull final DateRange that) {
        return true;
    }

    @Nonnull
    @Override
    public DateRange shift(@Nonnull final Period shift) {
        return this;
    }

    @Override
    public String toString() {
        return "(any)";
    }

}
