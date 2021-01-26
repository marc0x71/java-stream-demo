package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class OrderLoader {

    public static final String FILENAME = "orders.csv";

    public Stream<Order> load() {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(FILENAME).toURI());
            return Files.lines(path)
                    .map(OrderBuilder::build);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

}
