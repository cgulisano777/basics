# Java Maps Summary

## HashMap
**Purpose**: General-purpose, fast key-value store (no ordering).

**Use case**: Frequent insertions/lookups; keys and values can be null (1 null key max).

**Performance:** O(1) average for get/put; can degrade to O(n) in worst hash collisions (Java 8+ uses tree bins to avoid this).

**Concerns:** Not thread-safe; iteration order is not predictable.

## LinkedHashMap
**Purpose** HashMap + predictable iteration order.

**Use case**: Maintain insertion order or access order (LRU caches).

**Performance:** Slightly slower than HashMap due to linked list overhead.

**Concerns:** Still not thread-safe; more memory usage.

## ConcurrentHashMap
**Purpose** Thread-safe, high-performance concurrent map.

**Use case**: Multi-threaded access without full synchronization.

**Performance:** Lock-striping → concurrent reads O(1), writes are lock-segmented.

**Concerns:** Doesn’t allow null keys or values.

## WeakHashMap
**Purpose** Automatically removes entries when keys are no longer referenced (GC-based).

**Use case**: Caching with keys that should not prevent GC.

**Performance:** Similar to HashMap, but with weak references.

**Concerns:** Keys must be weakly referenced; entries can disappear at any time.

## EnumMap
**Purpose** Map specialized for enum keys.

**Use case**: When keys are of a single enum type.

**Performance:** Internally an array → very fast lookups.

**Concerns:** Keys must be enum constants; cannot be null.

## IdentityHashMap
**Purpose** Compares keys by == instead of .equals().

**Use case**: Special cases where identity equality is required (e.g., serialization graphs).

**Performance:** Similar to HashMap.

**Concerns:** Not for general use — behavior can be surprising if .equals() differs from ==.

## TreeMap
**Purpose** Sorted map based on natural/comparator ordering.

**Use case**: Range queries, sorted iteration, ceiling/floor lookups.

**Performance:** O(log n) for get/put.

**Concerns:** Higher overhead than HashMap; ordering cost.

## SequencedMap (Java 21+)
**Purpose** Map that maintains and manipulates a defined sequence of entries.

**Use case**: Reversible iteration, first/last element operations.

**Performance:** Depends on implementation (e.g., LinkedHashMap implements it).

**Concerns:** Relatively new — use only if targeting Java 21+.

## ConcurrentSkipListMap
**Purpose** Concurrent, sorted map.

**Use case**: Thread-safe TreeMap with predictable order.

**Performance:** O(log n) for most ops; lock-free reads.

**Concerns:** Higher memory usage; slower than ConcurrentHashMap for unordered ops.