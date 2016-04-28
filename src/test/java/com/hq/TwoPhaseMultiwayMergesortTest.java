package com.hq;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static junit.framework.TestCase.assertFalse;

public class TwoPhaseMultiwayMergesortTest {

    @Test
    public void testSort20() throws Exception {
        long startTime20 = System.currentTimeMillis();
        Mergesort tpmmsort20 = new TwoPhaseMultiwayMergesort("Instances/2^10/run_20%.txt", (long) Math.pow(2, 10), (long) Math.pow(2, 5));
        long setupTime20 = System.currentTimeMillis();
        System.out.println("File splitting completed. Total time for run_20%.txt = " + (setupTime20 - startTime20) + "ms.");
        tpmmsort20.sort();
        long finishTime20 = System.currentTimeMillis();
        System.out.println("File successfully sorted! Total time for run_20%.txt = " + (finishTime20 - startTime20)  + "ms.");

        BufferedReader reader = new BufferedReader(new FileReader("out/TwoPhaseMultiwayMergesort/OUT_Instances/2^10/run_20%.txt"));
        long prev = -Long.MIN_VALUE;
        for (int i = 0; i < (long) Math.pow(2, 10); i++) {
            long val = Long.parseLong(reader.readLine());
            if (val < prev)
                assertFalse(val < prev);
            prev = val;
        }
    }


    @Test
    public void testSort50() throws Exception {
        long startTime50 = System.currentTimeMillis();
        Mergesort tpmmsort50 = new TwoPhaseMultiwayMergesort("Instances/2^10/run_50%.txt", (long) Math.pow(2, 10), (long) Math.pow(2, 5));
        long setupTime50 = System.currentTimeMillis();
        System.out.println("File splitting completed. Total time for run_50%.txt = " + (setupTime50 - startTime50) + "ms.");
        tpmmsort50.sort();
        long finishTime50 = System.currentTimeMillis();
        System.out.println("File successfully sorted! Total time for run_50%.txt = " + (finishTime50 - startTime50)  + "ms.");

        BufferedReader reader = new BufferedReader(new FileReader("out/TwoPhaseMultiwayMergesort/OUT_Instances/2^10/run_50%.txt"));
        long prev = -Long.MIN_VALUE;
        for (int i = 0; i < (long) Math.pow(2, 10); i++) {
            long val = Long.parseLong(reader.readLine());
            if (val < prev)
                assertFalse(val < prev);
            prev = val;
        }
    }

    @Test
    public void testSort80() throws Exception {
        long startTime80 = System.currentTimeMillis();
        Mergesort tpmmsort80 = new TwoPhaseMultiwayMergesort("Instances/2^10/run_80%.txt", (long) Math.pow(2, 10), (long) Math.pow(2, 5));
        long setupTime80 = System.currentTimeMillis();
        System.out.println("File splitting completed. Total time for run_80%.txt = " + (setupTime80 - startTime80) + "ms.");
        tpmmsort80.sort();
        long finishTime80 = System.currentTimeMillis();
        System.out.println("File successfully sorted! Total time for run_80%.txt = " + (finishTime80 - startTime80)  + "ms.");

        BufferedReader reader = new BufferedReader(new FileReader("out/TwoPhaseMultiwayMergesort/OUT_Instances/2^10/run_80%.txt"));
        long prev = -Long.MIN_VALUE;
        for (int i = 0; i < (long) Math.pow(2, 10); i++) {
            long val = Long.parseLong(reader.readLine());
            if (val < prev)
                assertFalse(val < prev);
            prev = val;
        }
    }
}

