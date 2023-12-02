# Date Range

Java library for ranges of `LocalDate` objects.

Factory methods:

* `none()` creates a range that represents no dates, i.e. the empty set.
* `any()` creates a range that represents any date, i.e. the infinite set.
* `of(date)` creates a range that represents a single date.
* `onOrBefore(date)` creates a range that contains the given date and any preceding date.
