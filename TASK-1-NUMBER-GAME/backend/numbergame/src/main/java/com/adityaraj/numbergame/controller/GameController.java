package com.adityaraj.numbergame.controller;

import com.adityaraj.numbergame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*") // allow frontend to connect
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/new")
    public String startNewGame() {
        return gameService.startNewGame();
    }

    @PostMapping("/guess")
    public String guessNumber(@RequestBody int userGuess) {
        return gameService.checkGuess(userGuess);
    }
}
