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

    @Nonnull
    default Optional<Period> period() {
        final LocalDate earliest = this.earliest().orElse(null);
        if (earliest == null) return Optional.empty();
        final LocalDate latest = this.latest().orElse(null);
        if (latest == null) return Optional.empty();
        return Optional.of(Period.between(earliest, latest));
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

    /**
     * @return true if this range intersects with the given range.
     */
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
    default DateRange shift(@Nonnull final Period shift) {
        return shift.isZero() ? this : new ShiftedDateRange(this, shift, shift);
    }

    default boolean equals(final DateRange that) {
        return this.earliest().equals(that.earliest())
                && this.latest().equals(that.latest());
    }

    @Nonnull
    static DateRange any() {
        return AnyDateRange.INSTANCE;
    }

    @Nonnull
    static DateRange none() {
        return EmptyDateRange.INSTANCE;
    }

    @Nonnull
    static DateRange of(final LocalDate date) {
        return new ExactDate(date);
    }

    @Nonnull
    static DateRange onOrBefore(final LocalDate date) {
        return new BeforeDateRange(date);
    }

    @Nonnull
    static DateRange strictlyBefore(final LocalDate date) {
        return new BeforeDateRange(date.minusDays(1));
    }

    @Nonnull
    static DateRange onOrAfter(final LocalDate date) {
        return new AfterDateRange(date);
    }

    @Nonnull
    static DateRange strictlyAfter(final LocalDate date) {
        return new AfterDateRange(date.plusDays(1));
    }

    @Nonnull
    static DateRange closed(final LocalDate start, final LocalDate end) {
        final int c = start.compareTo(end);
        if (c == 0) return of(start);
        else if (c < 0) return of(start);
        else return closed(end, start);
    }

}
