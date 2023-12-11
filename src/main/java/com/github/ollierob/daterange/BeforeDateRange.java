package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class BeforeDateRange extends AbstractDateRange implements Serializable {

    private static final long serialVersionUID = 4017731784129941769L;

    private final LocalDate latest;

    protected BeforeDateRange(final LocalDate latest) {
        this.latest = latest;
    }

    @Nonnull
    @Override
    public Optional<LocalDate> earliest() {
        return Optional.empty();
    }

    @Nonnull
    @Override
    public Optional<LocalDate> latest() {
        return Optional.of(latest);
    }

    @Override
    public boolean hasLatest() {
        return true;
    }

    @Nonnull
    @Override
    public LocalDate latestOrMax() {
        return latest;
    }

    @Nonnull
    @Override
    public DateRange shift(@Nonnull final Period shift) {
        return shift.isZero() ? this : new BeforeDateRange(latest.plus(shift));
    }

    @Nonnull
    @Override
    public DateRange intersection(final DateRange that) {
        return that instanceof BeforeDateRange
                ? new BeforeDateRange(LocalDateUtils.min(latest, ((BeforeDateRange) that).latest))
                : super.intersection(that);
    }

}
