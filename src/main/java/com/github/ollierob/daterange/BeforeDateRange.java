package com.github.ollierob.daterange;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

public class BeforeDateRange implements DateRange, Serializable {

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
    public String toString() {
        return "(..," + latest + "]";
    }

}
