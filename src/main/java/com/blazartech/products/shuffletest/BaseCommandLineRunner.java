/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest;

import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author aar1069
 */
abstract public class BaseCommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BaseCommandLineRunner.class);

    static class DeviationIntFunction implements ToIntFunction<Integer> {

        private final int mean;

        public DeviationIntFunction(int mean) {
            this.mean = mean;
        }

        @Override
        public int applyAsInt(Integer value) {
            return (value - mean) * (value - mean);
        }

    }

    void dumpHistogram(Map<Integer, Integer> histogram, String formatString) {

        histogram.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> logger.info(formatString, e.getKey(), e.getValue()));

        // calculate the mean
        int total = histogram.values()
                .stream()
                .collect(Collectors.summingInt(Integer::intValue));
        int count = histogram.keySet().size();
        int average = total / count;
        logger.info("average = " + average);

        // calculate the standard deviation
        DeviationIntFunction toInt = new DeviationIntFunction(average);
        int totalDeviation = histogram.values()
                .stream()
                .collect(Collectors.summingInt(toInt));
        double standardDeviation = Math.sqrt(totalDeviation / count);
        logger.info("standardDeviation = {}", standardDeviation);
    }
}
