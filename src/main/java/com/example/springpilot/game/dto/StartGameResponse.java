package com.example.springpilot.game.dto;

import java.util.UUID;

public record StartGameResponse(UUID gameId, int min, int max) {
}
