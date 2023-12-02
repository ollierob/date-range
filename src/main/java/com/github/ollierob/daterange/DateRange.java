package com.github.ollierob.daterange;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public interface DateRange {

    @Nonnull
    Optional<LocalDate> earliest();

    @Nonnull
    default LocalDate earliestOrMin() {
        return this.earliest().orElse(LocalDate.MIN);
    }

    @Nonnull
    Optional<LocalDate> latest();

    @Nonnull
    default LocalDate latestOrMax() {
        return this.latest().orElse(LocalDate.MAX);
    }

    default boolean isEmpty() {
        return false;
    }

    /**
     * @return true if this range contains the given date.
     */
    default boolean contains(@Nonnull final LocalDate date) {
        return this.earliestOrMin().compareTo(date) <= 0 && date.compareTo(this.latestOrMax()) <= 0;
    }

    default boolean intersects(@Nonnull final DateRange that) {
        return this.earliestOrMin().compareTo(that.latestOrMax()) <= 0 && this.latestOrMax().compareTo(that.earliestOrMin()) >= 0;
    }

    @Nonnull
    default Optional<LocalDate> exact() {
        final Optional<LocalDate> earliest = this.earliest();
        if (!earliest.isPresent()) return Optional.empty();
        final Optional<LocalDate> latest = this.latest();
        if (!latest.isPresent()) return Optional.empty();
        return earliest.get().isEqual(latest.get()) ? earliest : Optional.empty();
    }

    @Nonnull
    @CheckReturnValue
    default DateRange shift(@Nonnull final Period period) {
        return period.isZero() ? this : new ShiftedDateRange(this, period, period);
    }

    static DateRange any() {
        return AnyDateRange.INSTANCE;
    }

    static DateRange none() {
        return EmptyDateRange.INSTANCE;
    }

    static DateRange of(final LocalDate date) {
        return new ExactDate(date);
    }

    static DateRange onOrBefore(final LocalDate date) {
        return new BeforeDateRange(date);
    }

    static DateRange strictlyBefore(final LocalDate date) {
        return new BeforeDateRange(date.minusDays(1));
    }

    static DateRange onOrAfter(final LocalDate date) {
        return new AfterDateRange(date);
    }

    static DateRange strictlyAfter(final LocalDate date) {
        return new AfterDateRange(date.plusDays(1));
    }

    static DateRange closed(final LocalDate start, final LocalDate end) {
        if (start.isEqual(end)) return of(start);
        if (start.isAfter(end)) return closed(end, start);
        return new ClosedDateRange(start, end);
    }

}
