/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest;

import com.blazartech.products.shuffletest.histogram.HistogramGenerator;
import com.blazartech.products.shuffletest.random.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
@Order(2)
public class RandomNumberGeneratorCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RandomNumberGeneratorCommandLineRunner.class);
    
    @Autowired
    private RandomNumberGenerator generator;
    
    @Autowired 
    private HistogramGenerator histogramGenerator;
    
    private static final int DATA_COUNT = 10000;
    private static final int RANGE = 10;
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("checking generator");
        
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < DATA_COUNT; i++) {
            data.add((int) (generator.getRandomValue() * RANGE));
        }
        
        Map<Integer, Integer> histogram = histogramGenerator.generateHistogram(data);
        
        for (int i = 0; i < RANGE; i++) {
            logger.info("count[" + i + "] = " + histogram.get(i));
        }
    }
    
}
