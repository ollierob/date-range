package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class ClosedDateRange extends AbstractDateRange implements Serializable, Iterable<LocalDate> {

    private static final long serialVersionUID = 8540911115103058696L;

    private final LocalDate start, end;

    protected ClosedDateRange(final LocalDate start, final LocalDate end) {
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        this.start = start;
        this.end = end;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> earliest() {
        return Optional.of(start);
    }

    @Nonnull
    @Override
    public LocalDate earliestOrMin() {
        return start;
    }

    @Override
    public boolean hasEarliest() {
        return true;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.of(end);
    }

    @Override
    public boolean hasLatest() {
        return true;
    }

    @Nonnull
    @Override
    public LocalDate latestOrMax() {
        return end;
    }

    @Nonnull
    @Override
    public DateRange shift(@Nonnull final Period shift) {
        return shift.isZero() ? this : new ClosedDateRange(start.plus(shift), end.plus(shift));
    }

    @Nonnull
    @Override
    public Optional<LocalDate> exact() {
        return start.equals(end) ? Optional.of(start) : Optional.empty();
    }

    @Override
    public Optional<ClosedDateRange> closed() {
        return Optional.of(this);
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new Iterator<LocalDate>() {

            LocalDate date = start;

            @Override
            public boolean hasNext() {
                return !date.isAfter(end);
            }

            @Override
            public LocalDate next() {
                if (!this.hasNext()) throw new NoSuchElementException();
                final LocalDate date = this.date;
                this.date = date.plusDays(1);
                return date;
            }

        };
    }

}
