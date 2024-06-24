# Streams

## Pipeline

### Generate values
* `Stream.of`
  ```java
  Stream.of("a", "b", "c")
          .forEach(System.out::print);
  ```
* `IntStream.iterate`, `IntStream.range`, `IntStream.generate`
    * Same for `LongStream`, `DoubleStream` and `Stream` (except *range*)
  ```java
  IntStream.iterate(0, seed -> seed + 3)
          .limit(3)
          .forEach(System.out::print);
  ```
  ```java
  IntStream.range(0, 3)
          .forEach(System.out::print);
  ```

### Intermediate operations
#### Filtering
* `filter` - *(T) -> boolean*
  ```java
  songs.stream()
          .filter(x -> x.getName().toLowerCase().contains("song")) // evaluates to boolean
          .toList()
  ```

#### Mapping
* `map` - *(T) -> R*
* `mapToInt` - *(T) -> int*
  ```java
  songs.stream()
          .map(Song::getYear)
          .mapToInt(Integer::intValue) // Stream<Integer> -> IntStream
          .min()
  ```
* `flatMap` - *(T) -> Stream<R>*
    * Replaces element with stream (used for 'unpacking' lists or splitting string to chars)
  ```java
  Stream.of(List.of(1, 2), List.of(3, 4), List.of(5, 6)) // Stream<List<Integer>>
          .flatMap(Collection::stream) // Stream<Integer>
          .forEach(System.out::println);
  ```

#### Debugging
* `peek` - *(T) -> void*
  ```java
  IntStream.range(0, 3)
          .peek(System.out::println)
          .sum();
  ```

#### Sorting
* `sorted`
    * *IntStream*, *LongStream* and *DoubleStream* cannot be sorted in reverse order
  ```java
  new Random().ints()
          .limit(5)
          .sorted()
          .toArray();
  ```

* `sorted` - comparator: *(T, T) -> int*
  ```java
  new Random().ints()
          .limit(5)
          .boxed()
          .sorted(Collections.reverseOrder())
          .toArray();
  ```
  ```java
  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
  ```
* `Comparator.comparingInt` - accepts 'key extractor' *(T) -> int*
    * Also `comparingLong` and `comparingDouble` can be used
  ```java
  .sorted(Comparator.comparingInt(String::length))
  .sorted(Comparator.comparingInt(String::length).reversed())
  ```
* `Comparator.comparing` - general purpose comparator.
    * accepts key extractor *(T) -> R* and comparator *(T, T) -> int*
      ```java
      .sorted(Comparator.comparing(Integer::intValue, Integer::compareTo))
      .sorted(Comparator.comparing(Integer::intValue, Integer::compareTo).reversed())
      ```
    * accepts key extractor *(T) -> R* and compares extracted keys in natural way
      ```java
      .sorted(Comparator.comparing(Integer::intValue))
      .sorted(Comparator.comparing(Integer::intValue).reversed())
      ```

#### De-duplicating
* `distinct`
  ```java
  new Random().ints()
          .limit(5)
          .distinct()
          .toList()
  ```
#### Truncating
* `limit` - when working with infinite streams (generators) or selecting top elements after sorting
  ```java
  new Random().ints()
          .limit(5)
          .toList()
  ```
* `skip` - discards elements
  ```java
  IntStream.iterate(0, x -> x + 1)
          .skip(5)
          .limit(5)
          .forEach(System.out::print);
  ```
### Terminal operations
#### Searching
* `anyMatch`, `allMatch`, `noneMatch` - *(T) -> boolean*
  ```java
  new Random().ints()
          .limit(5)
          .allMatch(x -> x > 0)
  ```
* `findFirst`, `findAny`
    * *findAny* returns any element from the stream (when working with parallel streams)
  ```java
  songs.stream()
          .filter(x -> x.getName().toLowerCase().contains("song"))
          .map(Song::getAuthor)
          .findFirst());
  ```

#### Collecting
* `collect`
    * `Collectors.toSet`
      ```java
      .collect(Collectors.toSet())
      ```
    * `Collectors.toList`
      ```java
      .collect(Collectors.toList())
      ```
    * `Collectors.toMap`
      ```java
      Collectors.toMap(keyMapper, valueMapper)
      Collectors.toMap(keyMapper, valueMapper, mergeFunction)
      Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapFactory)
      ```
      ```java
      Stream.of("Hi")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .collect(Collectors.toMap(
                          Function.identity(),
                          Function.identity())) // Throws an exception on duplicate chars
      // {H=H, i=i}
  
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .collect(Collectors.toMap(
                          Function.identity(),
                          Function.identity(),
                          (Character ch1, Character ch2) -> ch1)) // Skips duplicate characters
      // { = , s=s, t=t, e=e, '=', H=H, )=), i=i, :=:, m=m}
      
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .collect(Collectors.toMap(
                          Function.identity(),
                          Function.identity(),
                          (Character ch1, Character ch2) -> ch1, // Skips duplicate characters 
                          LinkedHashMap::new))                   // Keeps chars in order
      // {H=H, i=i,  = , t=t, '=', s=s, m=m, e=e, :=:, )=)}
      ```
    * `Collectors.groupingBy`
      ```java
      Collectors.groupingBy(classifier)
      Collectors.groupingBy(classifier, downstream)
      Collectors.groupingBy(classifier, mapFactory, downstream)
      ```
      ```java
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .collect(Collectors.groupingBy(
                      Function.identity())
  
      // { =[ ,  ,  ], s=[s], t=[t], e=[e], '=['], H=[H], )=[)], i=[i, i], :=[:], m=[m]}
  
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .collect(Collectors.groupingBy(
                      Function.identity(),
                      Collectors.counting()))
     
      // { =3, s=1, t=1, e=1, '=1, H=1, )=1, i=2, :=1, m=1}
  
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .collect(Collectors.groupingBy(
                      Function.identity(),
                      LinkedHashMap::new,
                      Collectors.toList()))
  
      // {H=[H], i=[i, i],  =[ ,  ,  ], t=[t], '=['], s=[s], m=[m], e=[e], :=[:], )=[)]}
      ```
    * `Collectors.joining`
        * **Only strings can be joined together**
      ```java
      Collectors.joining()
      Collectors.joining(delimiter)
      Collectors.joining(delimiter, prefix, suffix)
      ```
      ```java
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .map(Object::toString)
              .collect(Collectors.joining("; "))
  
      // H; i;  ; i; t; '; s;  ; m; e;  ; :; )
  
      Stream.of("Hi it's me :)")
              .flatMap(x -> x.chars().boxed())
              .map(x -> (char) x.intValue())
              .map(Object::toString)
              .collect(Collectors.joining("; ", "Joining ", " using ';"))
  
      // Joining H; i;  ; i; t; '; s;  ; m; e;  ; :; ) using ';
      ```
    * General purpose collector:
      ```java
      Collector.of(
          Supplier<A> supplier,           // provides accumulator type
          BiConsumer<A, T> accumulator,   // combine value with accumulator
          BinaryOperator<A> combiner,     // combines two accumulators (for parallel streams)
          Function<A, R> finisher)        // accepts accumulator and returns final value
      
      Collector.of(
          Supplier<A> supplier,           // provides accumulator type
          BiConsumer<A, T> accumulator,   // combine value with accumulator
          BinaryOperator<A> combiner,     // combines two accumulators (for parallel streams)
      ```
      ```java
      new Random().ints()
              .limit(5).boxed()
              .collect(Collector.of(
                      StringBuilder::new,
                      StringBuilder::append,
                      StringBuilder::append,
                      StringBuilder::toString))
      ```
* `toList` - same as `Collectors.toList()`

#### Reducing
* `sum`, `min`, `max`, `average`, `count`, `summaryStatistics`
    * `average`, `sum`, `summaryStatistics` are not available for *Stream\<T\>*
  ```java
  IntStream.iterate(0, x -> x + 1)
          .limit(5)
          .summaryStatistics()

  // IntSummaryStatistics{count=5, sum=10, min=0, average=2,000000, max=4}

  IntStream.iterate(0, x -> x + 1)
          .limit(5)
          .boxed()
          .max(Integer::compareTo)
  
  // Optional[4]
  ```
* `reduce` - general purpose reduction
  ```java
  reduce(identity, accumulator)
  reduce(accumulator)
  reduce(identity, accumulator, combiner)
  ```
  ```java
  IntStream.iterate(0, x -> x + 1)
          .limit(5)
          .boxed()
          .reduce(0, Integer::sum);
  
  // 10
  
  IntStream.iterate(0, x -> x + 1)
          .limit(5)
          .boxed()
          .reduce(new HashSet<>(),
                  (Set<Integer> identity, Integer item) -> {  // accumulator
                      identity.add(item);
                      return identity;
                  },
                  (Set<Integer> s1, Set<Integer> s2) -> {     // combiner
                      s1.addAll(s2);
                      return s1;
                  }));

  // [0, 1, 2, 3, 4]
  ```
#### Side-effecting
* `forEach` - *(T) -> void*
  ```java
  List<Integer> numbers = new ArrayList<>();

  new Random().ints()
          .limit(5)
          .forEach(numbers::add);
  ```