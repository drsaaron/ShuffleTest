/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.random;

import java.util.Random;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class UniformDeviateRandomNumberGenerator implements RandomNumberGenerator {
    
    private static final Random random = new Random();
    
    @Override
    public double getRandomValue() {
        //return Math.random();
        return random.nextDouble();
    }

    @Override
    public int getRandomInt(int n) {
        //return (int) (getRandomValue() * n);
        return random.nextInt(n);
    }
    
}
