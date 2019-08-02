/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.random;

/**
 *
 * @author AAR1069
 */
public interface RandomNumberGenerator {
    
    /**
     * get a uniform deviate in the range (0..1]
     * @return 
     */
    public double getRandomValue();
    
    /**
     * get a uniform deviate as an integer in the range (0..n]
     * @param n
     * @return 
     */
    public int getRandomInt(int n); 
}
