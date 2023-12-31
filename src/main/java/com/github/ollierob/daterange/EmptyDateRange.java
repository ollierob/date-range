package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class EmptyDateRange extends AbstractDateRange implements Serializable {

    private static final long serialVersionUID = 154001019754347063L;

    static final EmptyDateRange INSTANCE = new EmptyDateRange();

    protected EmptyDateRange() {
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
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(@Nonnull final LocalDate date) {
        return false;
    }

    /**
     * @return true only if given another empty range.
     */
    @Override
    public boolean intersects(@Nonnull final DateRange that) {
        return that.isEmpty();
    }

    @Override
    public DateRange intersection(final DateRange that) {
        return this;
    }

    @Nonnull
    @Override
    public DateRange shift(@Nonnull final Period shift) {
        return this;
    }

    @Nonnull
    @Override
    public Optional<Period> period() {
        return Optional.of(Period.ZERO);
    }

    @Override
    public boolean equals(final DateRange that) {
        return that.isEmpty();
    }

    @Override
    public String toString() {
        return "(none)";
    }

}
