package com.adityaraj.numbergame.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private int secretNumber;
    private int attempts;

    public String startNewGame() {
        secretNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        return "New game started! Guess a number between 1 and 100.";
    }

    public String checkGuess(int guess) {
        attempts++;

        if (guess == secretNumber) {
            return "🎉 Correct! You guessed it in " + attempts + " attempts.";
        } else if (guess < secretNumber) {
            return "Too low! Try again.";
        } else {
            return "Too high! Try again.";
        }
    }
}
