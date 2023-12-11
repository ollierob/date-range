package com.github.ollierob.daterange;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractDateRange implements DateRange, Serializable {

    private static final long serialVersionUID = 7087752370390444943L;

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof DateRange && this.equals((DateRange) obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.earliest(), this.latest());
    }

    @Override
    public String toString() {
        final Optional<LocalDate> earliest = this.earliest();
        final Optional<LocalDate> latest = this.latest();
        return earliest.map(d -> "[" + d).orElse("(..")
                + ','
                + latest.map(d -> d.toString() + ']').orElse("..)");
    }

}
