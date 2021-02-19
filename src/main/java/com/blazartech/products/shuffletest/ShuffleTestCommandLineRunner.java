/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest;

import com.blazartech.products.shuffletest.histogram.HistogramGenerator;
import com.blazartech.products.shuffletest.test.ShuffleTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
@Order(1)
public class ShuffleTestCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ShuffleTestCommandLineRunner.class);

    private static final int ARRAY_SIZE = 100;
    private static final int SHUFFLE_COUNT = 10000;
    private static final int CHECK_VALUE = 25;

    @Autowired
    private ShuffleTest tester;
    
    @Autowired
    private HistogramGenerator histogramGenerator;

    @Override
    public void run(String... args) throws Exception {

        logger.info("checking shuffler");
        
        // build the collection
        Collection<Integer> initialCollection = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            initialCollection.add(i);
        }

        // shuffle the thing SHUFFLE_COUNT times, each time find where CHECK_VALUE is
        List<Integer> locations = new ArrayList<>();
        for (int j = 0; j < SHUFFLE_COUNT; j++) {
            locations.add(tester.doTest(initialCollection, CHECK_VALUE));
        }

        // get a histogram
        Map<Integer, Integer> histogram = histogramGenerator.generateHistogram(locations);

        // dump out the counts.
        histogram.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> logger.info("position {} --> {}", e.getKey(), e.getValue()));
/*        for (int m = 0; m < ARRAY_SIZE; m++) {
            logger.info("position " + m + " --> " + histogram.get(m));
        }*/
    }

}
