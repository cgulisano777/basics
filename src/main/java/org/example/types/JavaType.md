# Java Primitive Types — The Essentials
Java has 8 primitive types, stored directly in memory (not as objects), each with a fixed size and range.

## byte
**Size**: 8-bit signed integer

**Range:** −128 to 127

**Default:** 0

**Use case:** Memory-saving when working with large arrays where the values fit in this range (e.g., image data, file streams).

**Performance note:** Avoid unless you truly need the small range — arithmetic auto-promotes to int.

## short
**Size**: 16-bit signed integer

**Range:** −32,768 to 32,767

**Default:** 0

**Use case:** Compact storage for numbers where byte is too small. Rare in modern code except for specialized data formats.

**Performance note:** Same caution as byte — arithmetic still promotes to int.

## int
**Size**: 32-bit signed integer

**Range:** −2,147,483,648 to 2,147,483,647

**Default:** 0

**Use case:** The default integer type in Java. Used for counters, indexes, general numeric work.

**Performance note:** Fastest integer type on most architectures.

## long
**Size**: 64-bit signed integer

**Range:** −9,223,372,036,854,775,808 to 9,223,372,036,854,775,807

**Default:** 0L

**Use case:** Large counters, timestamps, unique IDs.

**Performance note:** Slightly slower than int, but necessary for large values. Use L suffix for literals (123L).

## float
**Size**: 32-bit IEEE 754 floating-point

**Range:** ~±3.4 × 10³⁸, 7 decimal digits precision

**Default:** 0.0f

**Use case:** Large arrays of decimal numbers where memory matters more than precision (e.g., 3D graphics, scientific data).

**Performance note:** Precision loss likely. Always use f suffix (3.14f).

## double
**Size**: 64-bit IEEE 754 floating-point

**Range:** ~±1.7 × 10³⁰⁸, 15–16 decimal digits precision

**Default:** 0.0d

**Use case:** Default type for decimal values. Used in financial, scientific, statistical computations (but beware floating-point rounding).

**Performance note:** Usually hardware-accelerated; preferred for accuracy.

## char
**Size**: 16-bit unsigned integer (UTF-16 code unit)

**Range:** 0 to 65,535 (can hold any Unicode character)

**Default:** '\u0000'

**Use case:** Single characters, Unicode processing.

**Performance note:** Can also be used for small integer optimizations.

## boolean
**Size**: JVM-dependent (typically 1 byte in arrays)

Values: true / false

**Default:** false

**Use case:** Flags, conditions.

**Performance note:** Can’t be reliably used for bit-level optimizations; JVM may pad booleans in arrays.

# Extra Key Points
**Default values** apply only to instance/static variables, not local variables (locals must be initialized before use).

**Type promotion:** Smaller integer types (byte, short, char) auto-promote to int in expressions.

**Wrapper classes:** Each primitive has an object counterpart (int → Integer, etc.) for use in generics/collections.

**Autoboxing/unboxing:** Conversion between primitives and wrappers happens automatically, but can cause performance hits if overused.

**Overflow/underflow:** Primitives do not throw exceptions on overflow — they wrap around (e.g., int overflow). Use Math.addExact in Java 8+ to detect overflow.

