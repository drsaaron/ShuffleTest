/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.test;

import com.blazartech.products.shuffletest.shuffle.CollectionShuffler;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class ShuffleTestImpl implements ShuffleTest {

    @Autowired
    private CollectionShuffler<Integer> shuffler;

    @Override
    public int doTest(Collection<Integer> initialCollection, int checkPosition) {
        List<Integer> shuffled = (List<Integer>) shuffler.shuffle(initialCollection);
        for (int k = 0; k < shuffled.size(); k++) {
            if (shuffled.get(k) == checkPosition) {
                return k;
            }
        }
        
        return -1;
    }

}
