package com.example.springpilot.game;

import com.example.springpilot.game.dto.GuessRequest;
import com.example.springpilot.game.dto.GuessResponse;
import com.example.springpilot.game.dto.StartGameResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public StartGameResponse startGame() {
        return gameService.startGame();
    }

    @PostMapping(path = "/{gameId}/guess", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GuessResponse makeGuess(@PathVariable UUID gameId, @RequestBody GuessRequest request) {
        return gameService.guess(gameId, request.guess());
    }
}
