package com.example.newMock.controller;

import java.util.Random;

public class RandomGenerator {
    public static int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(min, max);
    }
}
