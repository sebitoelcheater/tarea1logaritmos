package com.hq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Created by sebito on 23-04-16.
 */
public class TwoWayMergesortTest {
    TwoWayMergesort twoWayMergesort;

    @Before
    public void setUp() throws Exception {
        twoWayMergesort = new TwoWayMergesort("input_90_20%.txt");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSort() throws Exception {
        twoWayMergesort.sort();
        BufferedReader outputReader = new BufferedReader(new FileReader(TwoWayMergesort.OUT_SUFIX+"input_90_20%.txt"));
        long prevNumber = Long.parseLong(outputReader.readLine());
        // counter starts in 1 because we have already readed one line to set prevNumber
        long counter = 1;
        for(String line; (line = outputReader.readLine()) != null; ) {
            assertTrue(prevNumber <= Long.parseLong(line));
            counter += 1;
            if (Long.parseLong(line) < prevNumber){
                break;
            }
        }
        assertEquals(counter, twoWayMergesort.getN());

    }

//    @Test
//    public void testSwipe() throws Exception {
//
//    }
//
//    @Test
//    public void testMerge() throws Exception {
//
//    }
}