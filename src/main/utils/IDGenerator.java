package main.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
    private static final AtomicInteger counter = new AtomicInteger(1000);

    // Generates an ID with a prefix and a number, e.g., "B1001"
    public static String generateId(String prefix) {
        return prefix + counter.getAndIncrement();
    }
}
