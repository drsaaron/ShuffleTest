/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest;

import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author aar1069
 */
abstract public class BaseCommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BaseCommandLineRunner.class);

    void dumpHistogram(Map<Integer, Integer> histogram, String formatString) {

        histogram.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> logger.info(formatString, e.getKey(), e.getValue()));

        // calculate the mean
        double mean = histogram.values()
                .stream()
                .collect(Collectors.averagingInt(Integer::intValue));
        logger.info("mean = {}", mean);

        // calculate the standard deviation        
        double averageTotalVariance = histogram.values()
                .stream()
                .collect(Collectors.averagingDouble(e -> Math.pow(e - mean, 2)));
        double standardDeviation = Math.sqrt(averageTotalVariance);
        logger.info("standardDeviation = {}", standardDeviation);
    }
}
