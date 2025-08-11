# Interfaces And Generics

## Interfaces

An interface in Java is a contract: it specifies what a class must do, but not how.

### Core Concepts
All methods in an interface are public and abstract by default (unless declared default or static).

Fields in interfaces are public static final (constants).

A class can implement multiple interfaces → enables multiple inheritance of type (unlike classes, which only have single inheritance).

Interface methods must be implemented by the concrete class, unless the class is abstract.

### Java 8+ Enhancements
Default methods → provide a method body inside the interface (backward compatibility & convenience).

```
default void log(String message) {
System.out.println("Log: " + message);
}
```
Static methods → belong to the interface itself, not the instance.

Private methods (Java 9) → helper methods used inside default methods.

### Functional Interfaces
An interface with exactly one abstract method.

Can be annotated with @FunctionalInterface.

Used in lambda expressions and method references.

```
@FunctionalInterface
interface Calculator {
int add(int a, int b);
}
Calculator c = (x, y) -> x + y;
```

### Key Interview Traps
A class can implement multiple interfaces but cannot extend multiple classes.

If two interfaces have the same default method, the implementing class must override it to resolve the conflict.

Interfaces cannot have constructors.

## Generics
   Generics allow type parameters for classes, interfaces, and methods, enabling type safety without casting.

### Benefits
Compile-time type checking → fewer runtime errors.

No explicit casting when retrieving objects from collections.

Reusable code that works with any object type.

### Generic Class

```
class Box<T> {
private T value;
public void set(T value) { this.value = value; }
public T get() { return value; }
}

Box<String> b = new Box<>();
b.set("Hello");
// b.set(123); // Compile-time error
```

### Generic Method

```
public static <T> void printArray(T[] array) {
for (T item : array) {
System.out.println(item);
}
}
```

### Bounded Type Parameters
- Upper bound: <T extends Number> → T must be Number or subclass.
- Lower bound: <? super Integer> → T must be Integer or a superclass.

### Wildcards
- ```<?>``` → unknown type.

- ```<? extends T>``` → read-only, safe to get, not safe to put (covariant).

- ```<? super T>``` → safe to put T, but reading returns Object (contravariant).

Example:
```
List<? extends Number> nums = List.of(1, 2.5); // can read Numbers
Number n = nums.get(0);
// nums.add(3); // ERROR

List<? super Integer> ints = new ArrayList<Number>();
ints.add(3); // OK
Object obj = ints.get(0);
```


### Interfaces + Generics Together
Interfaces themselves can be generic:

```
interface Repository<T> {
    void save(T entity);
    T findById(int id);
}

class UserRepository implements Repository<User> {
    public void save(User entity) { /* ... */ }
    public User findById(int id) { return null; }
}
```

- Allows you to build type-safe, reusable APIs (e.g., Spring Data repositories).

### Common Pitfalls
Type Erasure — Generics exist only at compile time; type parameters are replaced with their bounds (or Object) at runtime.

Cannot use primitives as type parameters (List<int> ❌, must use List<Integer>).

Cannot create generic arrays directly (new T[] ❌ — use List<T> instead).

Overusing wildcards can make code unreadable — prefer named type parameters.

