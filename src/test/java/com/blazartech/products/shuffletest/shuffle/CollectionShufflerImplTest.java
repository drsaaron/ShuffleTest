/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.shuffle;

import com.blazartech.products.shuffletest.random.RandomNumberGenerator;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author AAR1069
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    CollectionShufflerImplTest.CollectionShufflerImplTestConfiguration.class
})
public class CollectionShufflerImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CollectionShufflerImplTest.class);

    public static class TestRandomNumberGenerator implements RandomNumberGenerator {

        private int index = 0;
        private final double[] values;

        public TestRandomNumberGenerator(double[] values) {
            this.values = values;
        }

        @Override
        public double getRandomValue() {
            double v = values[index++];
            if (index == values.length) {
                index = 0;
            }
            return v;
        }

        public void reset() {
            logger.info("resetting generator, index = 0");
            index = 0;
        }

        @Override
        public int getRandomInt(int n) {
            return (int) (getRandomValue() * n);
        }
    }

    @Configuration
    static class CollectionShufflerImplTestConfiguration {

        @Bean
        public RandomNumberGenerator getRandomNumberGenerator() {
            return new TestRandomNumberGenerator(TEST_RANDOM_VALUES);
        }

        @Bean
        public CollectionShufflerImpl getCollectionShufflerImpl() {
            return new CollectionShufflerImpl();
        }
    }

    @Autowired
    private RandomNumberGenerator generator;

    @Autowired
    private CollectionShufflerImpl instance;

    public CollectionShufflerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        logger.info("resetting generator");
        ((TestRandomNumberGenerator) generator).reset();
    }

    @After
    public void tearDown() {
    }

    private static final double[] TEST_RANDOM_VALUES = new double[] { 0.2, 0.6, 0.4, 0, 0.8 };
    private static final int TEST_SIZE = TEST_RANDOM_VALUES.length;
    
    private int getExpectedIndex(int i) {
        return (int) (TEST_RANDOM_VALUES[i] * TEST_SIZE);
    }

    /**
     * Test of getRandomIndex method, of class CollectionShufflerImpl.
     */
    @Test
    public void testGetRandomIndex() {
        logger.info("getRandomIndex");
        int n = TEST_SIZE;

        int expResult = getExpectedIndex(0);
        int result = instance.getRandomIndex(n);

        assertEquals(expResult, result);
        
        expResult = getExpectedIndex(1);
        result = instance.getRandomIndex(n);
        assertEquals(expResult, result);

    }

    public List<Integer> convertArray(int[] array) {
        return IntStream.of(array).boxed().collect(Collectors.toList());
    }
    
    /**
     * Test of shuffle method, of class CollectionShufflerImpl.
     */
    @Test
    public void testShuffle() {
        logger.info("shuffle");
        
        int[] testArray = new int[] { 0, 1, 2, 3, 4 };
        //int[] shuffledArray = new int[] { 0, 3, 2, 1, 4 };
        int[] shuffledArray = new int[] { 1, 3, 0, 2, 4 };
        
        List<Integer> expResult = convertArray(shuffledArray);
        Collection<Integer> result = instance.shuffle(convertArray(testArray));
        assertEquals(expResult, result);
    }

}
