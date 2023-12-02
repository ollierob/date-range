# Date Range

Java library for ranges of `LocalDate` objects.

Factory methods:

* `none()` creates a range that represents no dates, i.e. the empty set.
* `any()` creates a range that represents any date, i.e. the infinite set.
* `of(date)` creates a range that represents a single date.
* `onOrBefore(date)` creates a range that contains the given date and any preceding date.
* `strictlyBefore(date)` creates a range that contains all dates before the given date.
* `closed(start,end)` creates a closed range that contains all dates between and equal to the given dates.
* `closedOpen(start,end)` creates a range that contains all dates equal to after the given start date and before the given end date.