package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

/**
 * A range containing a single date.
 */
public class ExactDate implements DateRange, Serializable {

    private static final long serialVersionUID = -7822691754379610144L;

    private final LocalDate date;

    protected ExactDate(@Nonnull final LocalDate date) {
        Objects.requireNonNull(date);
        this.date = date;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> earliest() {
        return Optional.of(date);
    }

    @Override
    public boolean hasEarliest() {
        return true;
    }

    @Nonnull
    @Override
    public LocalDate earliestOrMin() {
        return date;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.of(date);
    }

    @Override
    public boolean hasLatest() {
        return true;
    }

    @Nonnull
    @Override
    public LocalDate latestOrMax() {
        return date;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> exact() {
        return Optional.of(date);
    }

    @Override
    public boolean contains(@Nonnull final LocalDate date) {
        return this.date.isEqual(date);
    }

    @Override
    public boolean intersects(@Nonnull final DateRange that) {
        return that.contains(date);
    }

    @Nonnull
    @Override
    public DateRange intersection(final DateRange that) {
        return this.intersects(that) ? this : DateRange.none();
    }

    @Nonnull
    @Override
    public DateRange shift(final Period shift) {
        return shift.isZero() ? this : new ExactDate(date.plus(shift));
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof DateRange && this.equals((DateRange) obj);
    }

    @Override
    public String toString() {
        return "[" + date + "]";
    }

}
