/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.histogram;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    HistogramGeneratorImplTest.HistogramGeneratorImplTestConfiguration.class
})
public class HistogramGeneratorImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(HistogramGeneratorImplTest.class);
    
    @Configuration
    static class HistogramGeneratorImplTestConfiguration {
        
        @Bean
        public HistogramGeneratorImpl instance() {
            return new HistogramGeneratorImpl();
        }
    }
    
    @Autowired 
    private HistogramGeneratorImpl instance;
    
    public HistogramGeneratorImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    public List<Integer> convertArray(int[] array) {
        return IntStream.of(array).boxed().collect(Collectors.toList());
    }
    
    /**
     * Test of generateHistogram method, of class HistogramGeneratorImpl.
     */
    @Test
    public void testGenerateHistogram() {
        logger.info("generateHistogram");
        
        Collection<Integer> data = convertArray(new int[] { 1, 2, 3, 1, 1, 4, 3, 2 });
        Map<Integer, Integer> result = instance.generateHistogram(data);
        
        assertEquals(3, (int) result.get(1));
        assertEquals(2, (int) result.get(2));
        assertNull(result.get(0));
    }
    
}
