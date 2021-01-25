package org.example;

import java.util.Random;
import java.util.function.Supplier;

public class Generator {
    private static Random random = new Random();
    public static Supplier<Integer> integerSupplier = () -> random.nextInt(1000);
    public static Supplier<String> stringSupplier = () -> random.ints('a', 'z'+ 1)
            .limit(random.nextInt(10))
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
}
