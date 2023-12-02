package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class ClosedDateRange implements DateRange, Serializable {

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

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.of(end);
    }

    @Nonnull
    @Override
    public LocalDate latestOrMax() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }

}
