package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleStreamTest {
    @Test
    public void integerSupplier() {
        long count = Stream.generate(Generator.integerSupplier)
                .filter(x -> x > 500)
                .limit(10)
                .peek(System.out::println)
                .count();

        assertEquals(10, count);
    }

    @Test
    void stringSupplier() {
        long count = Stream.generate(Generator.stringSupplier)
                .filter(s -> s.length() > 4)
                .limit(10)
                .peek(System.out::println)
                .count();
        assertEquals(10, count);
    }

    @Test
    void CollectorsPartitioningBy() {

        Map<Boolean, List<Integer>> result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                .collect(Collectors.partitioningBy(e -> e % 2 == 0));

        System.out.println("result = " + result); // result = {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}

        assertEquals(2, result.size());
        assertEquals(List.of(1, 3, 5, 7, 9), result.get(false));
        assertEquals(List.of(2, 4, 6, 8, 10), result.get(true));
    }

    @Test
    void CollectorsJoining() {
        String result = List.of("Pippo", "Pluto", "Paperino", "Qui", "Quo", "Qua").stream()
                .map(String::toLowerCase)
                .collect(Collectors.joining(", "));

        System.out.println("result = " + result); // result = pippo, pluto, paperino, qui, quo, qua

        assertEquals("pippo, pluto, paperino, qui, quo, qua", result);
    }

    @Test
    void CollectorsGroupingBy() {
        Map<String, List<String>> result = List.of("Pippo", "Pluto", "Qui", "Paperino", "Quo", "Qua").stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1)));

        System.out.println("result = " + result); // result = {P=[Pippo, Pluto, Paperino], Q=[Qui, Quo, Qua]}

        assertEquals(2, result.size());
        assertEquals(List.of("Pippo", "Pluto", "Paperino"), result.get("P"));
        assertEquals(List.of("Qui", "Quo", "Qua"), result.get("Q"));
    }

    @Test
    void CollectorsGroupingByAndMapping() {
        Map<String, List<Integer>> result = List.of("Pippo", "Pluto", "Qui", "Paperino", "Quo", "Qua").stream()
                .collect(
                        Collectors.groupingBy(s -> s.substring(0, 1), Collectors.mapping(String::length, Collectors.toList()))
                );

        System.out.println("result = " + result); // result = {P=[5, 5, 8], Q=[3, 3, 3]}

        assertEquals(2, result.size());
        assertEquals(List.of(5, 5, 8), result.get("P"));
        assertEquals(List.of(3, 3, 3), result.get("Q"));
    }

    @Test
    void CollectorsGroupingByCollectingAndThen() {
        Map<String, Integer> result = List.of("Pippo", "Pluto", "Qui", "Paperino", "Quo", "Qua").stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));

        System.out.println("result = " + result); // result = {P=3, Q=3}

        assertEquals(2, result.size());
        assertEquals(3, result.get("P"));
        assertEquals(3, result.get("Q"));
    }

    @Test
    void CollectorsToMap() {
        Map<String, Integer> result = List.of("Pippo", "Pluto", "Paperino", "Qui", "Quo", "Qua").stream()
                .map(String::toLowerCase)
                .collect(Collectors.toMap(s -> s, String::length));

        System.out.println("result = " + result); // result = {qui=3, pluto=5, quo=3, qua=3, pippo=5, paperino=8}

        assertEquals(6, result.size());
        assertEquals(5, result.get("pippo"));
        assertEquals(5, result.get("pluto"));
        assertEquals(8, result.get("paperino"));
        assertEquals(3, result.get("qui"));
        assertEquals(3, result.get("quo"));
        assertEquals(3, result.get("qua"));
    }

    @Test
    void reduce() {
        int result = IntStream.range(1, 10)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .reduce(0, Integer::sum);

        System.out.println("result = " + result); // result = 40

        assertEquals(40, result);
    }

    @Test
    void flatMap() {
        List<Integer> result = List.of(1, 2, 3).stream()
                .flatMap(n -> List.of(n - 1, n, n + 1).stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("result = " + result); // result = [0, 1, 2, 3, 4]

        assertEquals(List.of(0, 1, 2, 3, 4), result);
    }
}
