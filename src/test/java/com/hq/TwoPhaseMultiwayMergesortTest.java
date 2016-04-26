package com.hq;

import com.sun.scenario.effect.Merge;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jorge on 23-04-16.
 */
public class TwoPhaseMultiwayMergesortTest {

    private Mergesort tpmmsort20 = null;
    private Mergesort tpmmsort50 = null;
    private Mergesort tpmmsort80 = null;

    @Before
    public void setUp() throws Exception {
        long startTime20 = System.currentTimeMillis();
        Mergesort tpmmsort20 = new TwoPhaseMultiwayMergesort("input_20%.txt", (long) Math.pow(2, 30), (long) Math.pow(2, 20));
        long setupTime20 = System.currentTimeMillis();
        System.out.println("File splitting completed. Total time for input_20%.txt = " + (setupTime20 - startTime20) + "ms.");
        tpmmsort20.sort();
        long finishTime20 = System.currentTimeMillis();
        System.out.println("File successfully sorted! Total time for input_20%.txt = " + (finishTime20 - startTime20)  + "ms.");
    }

    @Test
    public void testSort() throws Exception {

    }
}