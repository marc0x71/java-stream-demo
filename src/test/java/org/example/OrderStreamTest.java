package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStreamTest {

    Stream<Order> orders;

    @BeforeEach
    void init() {
        OrderLoader loader = new OrderLoader();
        orders = loader.load();
    }

    @Test
    void ordersPerYear() {
        Map<Integer, Long> ordersPerYear = orders.
                collect(Collectors.groupingBy(o -> o.getOrderDate().get(Calendar.YEAR), Collectors.counting()));

        System.out.println("ordersPerYear = " + ordersPerYear);

        assertEquals(13076, ordersPerYear.get(2010));
        assertEquals(13224, ordersPerYear.get(2011));
        assertEquals(13204, ordersPerYear.get(2012));
        assertEquals(13357, ordersPerYear.get(2013));
        assertEquals(13209, ordersPerYear.get(2014));
        assertEquals(13180, ordersPerYear.get(2015));
        assertEquals(13192, ordersPerYear.get(2016));
        assertEquals(7558, ordersPerYear.get(2017));
    }
}
