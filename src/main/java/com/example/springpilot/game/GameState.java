package com.example.springpilot.game;

import java.util.concurrent.atomic.AtomicInteger;

public class GameState {
    private final int target;
    private final int min;
    private final int max;
    private final AtomicInteger attempts = new AtomicInteger(0);

    public GameState(int target, int min, int max) {
        this.target = target;
        this.min = min;
        this.max = max;
    }

    public int getTarget() {
        return target;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int incrementAttempts() {
        return attempts.incrementAndGet();
    }

    public int getAttempts() {
        return attempts.get();
    }
}
