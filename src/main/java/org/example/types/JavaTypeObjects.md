# Java Types — Objects

## Text Objects
   These are immutable or mutable representations of sequences of characters.

### String
Immutable sequence of characters.

Stored in the String Pool when created with literals ("Hello").

**Use case:** fixed text, keys in maps, safe for multi-threading without extra synchronization.

Performance concern: modifying a String creates a new object — use StringBuilder/StringBuffer for heavy mutations.

### StringBuilder
Mutable, not thread-safe.

**Use case:** frequent modifications (append, insert, delete) in single-threaded contexts.

**Performance:** faster than StringBuffer due to no synchronization.

### StringBuffer
Mutable, thread-safe (synchronized).

**Use case:** frequent modifications in multi-threaded contexts.

### CharSequence (interface)
Parent type for String, StringBuilder, StringBuffer.

Useful for methods that can accept any character sequence.

## Date/Time Objects
   Since Java 8, java.time (JSR-310) is the standard — immutable, thread-safe.

### LocalDate
Represents a date without time (e.g., 2025-08-11).

**Use case:** birthdays, due dates, schedules.

### LocalTime
Represents a time without date (e.g., 15:45:30).

**Use case:** store opening hours, alarms.

### LocalDateTime
Combines LocalDate + LocalTime, no timezone.

**Use case:** timestamps without timezone tracking.

### ZonedDateTime
Date + time + timezone (e.g., 2025-08-11T10:15:30+03:00[Europe/Paris]).

**Use case:** international applications.

### Instant
Point on the UTC timeline (nanosecond precision).

**Use case:** event logs, epoch-based calculations.

### Period
Amount of time in years, months, days (date-based).

**Use case:** age calculation, subscription durations.

### Duration
Amount of time in seconds/nanos (time-based).

**Use case:** stopwatch, timeouts.

Performance note: Prefer java.time over legacy Date/Calendar — it’s safer and cleaner.

## Numeric Objects
   Immutable wrappers for primitives + classes for big numbers.

### Integer, Long, Short, Byte, Double, Float
Wrapper classes for primitives.

**Use case:** needed in collections (can’t store primitives directly), or when null is allowed.

Autoboxing/unboxing converts between primitive and wrapper automatically.

Performance concern: unnecessary boxing can slow down loops.

### BigInteger
Arbitrary-precision integer.

**Use case:** cryptography, very large numbers.

**Performance:** slower than primitives, but exact for huge numbers.

### BigDecimal
Arbitrary-precision decimal, exact for financial calculations.

**Use case:** money, currency, scientific precision.

**Performance:** slower than double but avoids rounding errors.

# Key reminders:

- Use immutable objects (String, LocalDate) for safety and predictability.

- Use mutable ones (StringBuilder) when you need repeated changes.

- Use java.time for all new date/time code.

- Use BigDecimal and BigInteger only when precision matters more than performance.