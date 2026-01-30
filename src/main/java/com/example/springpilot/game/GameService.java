package com.example.springpilot.game;

import com.example.springpilot.game.dto.GuessResponse;
import com.example.springpilot.game.dto.StartGameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GameService {
    private static final int DEFAULT_MIN = 1;
    private static final int DEFAULT_MAX = 100;

    private final Map<UUID, GameState> games = new ConcurrentHashMap<>();

    public StartGameResponse startGame() {
        int target = ThreadLocalRandom.current().nextInt(DEFAULT_MIN, DEFAULT_MAX + 1);
        UUID gameId = UUID.randomUUID();
        games.put(gameId, new GameState(target, DEFAULT_MIN, DEFAULT_MAX));
        return new StartGameResponse(gameId, DEFAULT_MIN, DEFAULT_MAX);
    }

    public GuessResponse guess(UUID gameId, int guess) {
        GameState game = games.get(gameId);
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }

        if (guess < game.getMin() || guess > game.getMax()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guess out of range");
        }

        int attempts = game.incrementAttempts();
        if (guess < game.getTarget()) {
            return new GuessResponse("higher", attempts);
        }
        if (guess > game.getTarget()) {
            return new GuessResponse("lower", attempts);
        }
        return new GuessResponse("correct", attempts);
    }
}
