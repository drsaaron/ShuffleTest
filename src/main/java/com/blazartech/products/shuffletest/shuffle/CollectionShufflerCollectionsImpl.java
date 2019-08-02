/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.shuffle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * use Collections.shuffle to shuffle.
 * 
 * @author AAR1069
 * @param <T>
 */
//@Component
public class CollectionShufflerCollectionsImpl<T extends Object> implements CollectionShuffler<T> {

    @Override
    public Collection<T> shuffle(Collection<T> collection) {
        List<T> copy = new ArrayList<>();
        copy.addAll(collection);
        Collections.shuffle(copy);
        return copy;
    }
    
}
