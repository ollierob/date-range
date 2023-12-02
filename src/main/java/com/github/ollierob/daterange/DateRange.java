package com.github.ollierob.daterange;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Optional;

public interface DateRange {

    /**
     * @return the earliest date in this range, if specified. Empty means no earliest date.
     */
    @Nonnull
    Optional<LocalDate> earliest();

    default boolean hasEarliest() {
        return this.earliest().isPresent();
    }

    /**
     * @return the earliest date in this range, if specified, else {@link LocalDate#MIN}.
     */
    @Nonnull
    default LocalDate earliestOrMin() {
        return this.earliest().orElse(LocalDate.MIN);
    }

    /**
     * @return the latest date in this range, if specified. Empty means no latest date.
     */
    @Nonnull
    Optional<LocalDate> latest();

    default boolean hasLatest() {
        return this.latest().isPresent();
    }

    /**
     * @return the latest date in this range, if specified, else {@link LocalDate#MAX}.
     */
    @Nonnull
    default LocalDate latestOrMax() {
        return this.latest().orElse(LocalDate.MAX);
    }

    /**
     * @return the period of this date range, if non-infinite.
     */
    @Nonnull
    default Optional<Period> period() {
        final LocalDate earliest = this.earliest().orElse(null);
        if (earliest == null) return Optional.empty();
        final LocalDate latest = this.latest().orElse(null);
        if (latest == null) return Optional.empty();
        return Optional.of(Period.between(earliest, latest));
    }

    /**
     * @return the single year represented by this range, if any,
     */
    @Nonnull
    default Optional<Year> year() {
        final LocalDate earliest = this.earliest().orElse(null);
        if (earliest == null) return Optional.empty();
        final LocalDate latest = this.latest().orElse(null);
        if (latest == null) return Optional.empty();
        return earliest.getYear() == latest.getYear() ? Optional.of(Year.of(earliest.getYear())) : Optional.empty();
    }

    /**
     * @return true if this range is the empty set.
     */
    default boolean isEmpty() {
        return false;
    }

    /**
     * @return true if this range contains the given date.
     */
    @SuppressWarnings("RedundantCompareToJavaTime")
    default boolean contains(@Nonnull final LocalDate date) {
        return this.earliestOrMin().compareTo(date) <= 0 && date.compareTo(this.latestOrMax()) <= 0;
    }

    /**
     * @return true if this range intersects with the given range.
     */
    @SuppressWarnings("RedundantCompareToJavaTime")
    default boolean intersects(@Nonnull final DateRange that) {
        return this.earliestOrMin().compareTo(that.latestOrMax()) <= 0 && this.latestOrMax().compareTo(that.earliestOrMin()) >= 0;
    }

    @Nonnull
    default DateRange intersection(final DateRange that) {
        if (that.isEmpty()) return none();
        final LocalDate start = LocalDateUtils.max(this.earliestOrMin(), that.earliestOrMin());
        final LocalDate end = LocalDateUtils.min(this.latestOrMax(), that.latestOrMax());
        final int c = start.compareTo(end);
        if (c == 0) return of(start);
        else if (c > 0) return none();
        //Check for open intervals
        final boolean startOpen = !this.hasEarliest() || !that.hasEarliest();
        final boolean endOpen = !this.hasLatest() || !that.hasLatest();
        if (startOpen && endOpen) return any();
        else if (startOpen) return onOrBefore(end);
        else if (endOpen) return onOrAfter(start);
        else return closed(start, end);
    }

    /**
     * @return a date if this range represents a single date.
     */
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

    default boolean equals(@Nonnull final DateRange that) {
        return this == that || this.earliest().equals(that.earliest()) && this.latest().equals(that.latest());
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
        if (!date.isAfter(LocalDate.MIN)) return none();
        return new BeforeDateRange(date.minusDays(1));
    }

    @Nonnull
    static DateRange onOrAfter(final LocalDate date) {
        return new AfterDateRange(date);
    }

    @Nonnull
    static DateRange strictlyAfter(final LocalDate date) {
        if (!date.isBefore(LocalDate.MAX)) return none();
        return new AfterDateRange(date.plusDays(1));
    }

    /**
     * @return a range of dates between the given start and end dates, inclusive.
     * If the given start date is after the given end date then return an empty date range.
     */
    @Nonnull
    static DateRange closed(final LocalDate start, final LocalDate end) {
        final int c = start.compareTo(end);
        if (c == 0) return of(start);
        else if (c < 0) return new ClosedDateRange(start, end);
        else return none();
    }

    @Nonnull
    static DateRange closedOpen(final LocalDate start, final LocalDate end) {
        return closed(start, end.minusDays(1));
    }

}
