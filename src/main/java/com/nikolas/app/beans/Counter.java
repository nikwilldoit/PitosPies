package com.nikolas.app.beans;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private String name;
    private AtomicInteger counter = new AtomicInteger();

    public Counter(String name) {
        this.name = name;
    }

    public int getValue() {
        return counter.get();
    }

    public void increase() {
        counter.incrementAndGet();
    }

    @Override
    public String toString() {
        return counter.toString();
    }
}