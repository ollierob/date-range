package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

public class ShiftedDateRange implements DateRange, Serializable {

    private static final long serialVersionUID = -6980833782804824602L;

    private final DateRange delegate;
    private final Period shiftStart, shiftEnd;

    protected ShiftedDateRange(final DateRange delegate, final Period shiftStart, final Period shiftEnd) {
        Objects.requireNonNull(delegate, "delegate");
        Objects.requireNonNull(shiftStart, "shift");
        this.delegate = delegate;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> earliest() {
        return delegate.earliest().map(d -> d.plus(shiftStart));
    }

    @Override
    public boolean hasEarliest() {
        return delegate.hasEarliest();
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return delegate.latest().map(d -> d.plus(shiftEnd));
    }

    @Override
    public boolean hasLatest() {
        return delegate.hasLatest();
    }

    @Nonnull
    @Override
    public DateRange shift(final Period shift) {
        return shift.isZero() ? this : new ShiftedDateRange(delegate, this.shiftStart.plus(shift), this.shiftEnd.plus(shift));
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof DateRange && this.equals((DateRange) obj);
    }

    @Override
    public String toString() {
        final String start = this.earliest().map(d -> d + "+" + shiftStart).orElse(null);
        throw new UnsupportedOperationException(); //TODO
    }

}
