package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class AfterDateRange extends AbstractDateRange implements Serializable {

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

    @Override
    public boolean hasEarliest() {
        return true;
    }

    @Nonnull
    @Override
    public LocalDate earliestOrMin() {
        return start;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.empty();
    }

    @Nonnull
    @Override
    public DateRange shift(@Nonnull final Period shift) {
        return shift.isZero() ? this : new AfterDateRange(start.plus(shift));
    }

}
