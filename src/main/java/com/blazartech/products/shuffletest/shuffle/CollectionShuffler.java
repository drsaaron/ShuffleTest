/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.shuffletest.shuffle;

import java.util.Collection;

/**
 *
 * @author AAR1069
 * @param <T>
 */
public interface CollectionShuffler<T extends Object> {
    
    public Collection<T> shuffle(final Collection<T> collection);
}
