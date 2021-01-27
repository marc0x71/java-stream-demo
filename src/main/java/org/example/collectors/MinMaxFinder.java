package org.example.collectors;

import lombok.Getter;

import java.util.function.BiFunction;

public class MinMaxFinder<T> {
    private final BiFunction<T, T, T> minFunction;
    private final BiFunction<T, T, T> maxFunction;
    @Getter
    private T min;
    @Getter
    private T max;

    public MinMaxFinder(BiFunction<T, T, T> minFunction, BiFunction<T, T, T> maxFunction) {
        this.minFunction = minFunction;
        this.maxFunction = maxFunction;
    }

    public void accumulate(T value) {
        if (min == null && max == null) {
            min = value;
            max = value;
        }
        min = minFunction.apply(min, value);
        max = maxFunction.apply(max, value);
    }

    public void combine(MinMaxFinder<T> r) {
        if (min == null && max == null) {
            min = r.min;
            max = r.max;
        }
        min = minFunction.apply(min, r.min);
        max = maxFunction.apply(max, r.max);
    }
}
