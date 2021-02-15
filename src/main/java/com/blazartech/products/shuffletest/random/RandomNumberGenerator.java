/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.random;

/**
 * interface for a generic random number generator
 * @author AAR1069
 */
public interface RandomNumberGenerator {
    
    /**
     * get a random number defined by the distribution
     * @return 
     */
    public double getRandomValue();
    
    /**
     * get a random number as an integer in the range (0..n] per the distribution
     * @param n
     * @return 
     */
    public int getRandomInt(int n); 
}
