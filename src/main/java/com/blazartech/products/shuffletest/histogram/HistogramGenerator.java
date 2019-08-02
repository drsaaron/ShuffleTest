/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.histogram;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author AAR1069
 */
public interface HistogramGenerator {
    
    public Map<Integer, Integer> generateHistogram(Collection<Integer> data);
}
