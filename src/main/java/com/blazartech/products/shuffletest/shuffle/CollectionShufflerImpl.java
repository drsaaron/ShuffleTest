/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.shuffle;

import com.blazartech.products.shuffletest.random.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * emulate the C++ shuffle algorithm
 * @author AAR1069
 * @param <T>
 */
@Component
public class CollectionShufflerImpl<T extends Object> implements CollectionShuffler<T> {

    private static final Logger logger = LoggerFactory.getLogger(CollectionShufflerImpl.class);
    
    @Autowired
    private RandomNumberGenerator generator;
    
    /**
     * get a random integer between 0 and n-1
     * @param n
     * @return 
     */
    public int getRandomIndex(int n) {
        return generator.getRandomInt(n);
    }
    
    @Override
    public Collection<T> shuffle(final Collection<T> collection) {
        logger.debug("shuffling collection " + collection + " of size " + collection.size());
        
        ArrayList<T> output = new ArrayList<>();
        output.addAll(collection);
        
        for (int i = 0; i < collection.size(); i++) {
            /* I would think I would want to randomly select any index in the collection.
               However, that seems to give a bias, for reasons I don't understand, to moving
               an element down rather than up.  The Collections.shuffle source code
               (https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/Collections.java)
               shows the algorithm working backwards and only considering values
               in the portion of the array not yet looked at.  In other words, it
               only swaps backward.  My loop is going forward, so to do the same
               thing, we can only swap elements ahead.  That seems to give better
               results, i.e. the test gives a more uniform distribution of results.
               I still don't get why.
            
               It should be noted the C++ shuffle algorith (http://www.cplusplus.com/reference/algorithm/shuffle/)
               works the same as the Collections.shuffle implementation.
            */
        //    int k = getRandomIndex(collection.size());  // what I think it should be
            int k = getRandomIndex(collection.size() - i) + i; // modeled on the Collections.shuffle source
            output.set(i, output.set(k, output.get(i)));
        }
        
        return output;
    }
    

}
