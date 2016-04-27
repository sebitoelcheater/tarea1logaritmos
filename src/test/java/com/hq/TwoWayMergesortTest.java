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

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSort9020() throws Exception {
        twoWayMergesort = new TwoWayMergesort("input_90_20%.txt");
        twoWayMergesort.sort();
        BufferedReader outputReader = new BufferedReader(new FileReader(TwoWayMergesort.OUT_PATH +"input_90_20%.txt"));
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

    @Test
    public void testSort20() throws Exception {
        twoWayMergesort = new TwoWayMergesort("input_20%.txt");
        twoWayMergesort.sort();
        BufferedReader outputReader = new BufferedReader(new FileReader(TwoWayMergesort.OUT_PATH +"input_20%.txt"));
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

    @Test
    public void testSort50() throws Exception {
        twoWayMergesort = new TwoWayMergesort("input_50%.txt");
        twoWayMergesort.sort();
        BufferedReader outputReader = new BufferedReader(new FileReader(TwoWayMergesort.OUT_PATH +"input_50%.txt"));
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

    @Test
    public void testSort80() throws Exception {
        twoWayMergesort = new TwoWayMergesort("input_80%.txt");
        twoWayMergesort.sort();
        BufferedReader outputReader = new BufferedReader(new FileReader(TwoWayMergesort.OUT_PATH +"input_80%.txt"));
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