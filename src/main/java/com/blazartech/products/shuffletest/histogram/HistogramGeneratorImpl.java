/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class HistogramGeneratorImpl implements HistogramGenerator {
    
    private static final Logger logger = LoggerFactory.getLogger(HistogramGeneratorImpl.class);

    @Override
    public Map<Integer, Integer> generateHistogram(Collection<Integer> data) {
        Map<Integer, Integer> hist = new HashMap<>();
        data.forEach((i) -> {
            Integer count = hist.get(i);
            if (count == null) {
                count = 0;
            }
            hist.put(i, count + 1);
        });
        return hist;
    }
    
    
}
