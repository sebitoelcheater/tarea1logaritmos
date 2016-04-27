package com.hq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Created by sebito on 26-04-16.
 */
public class AdaptativeMergesortTest {
    public AdaptativeMergesort adaptativeMergesort;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructor() throws Exception {
        String input = "input_small.txt";
        adaptativeMergesort = new AdaptativeMergesort(input);

        CustomSortedList customSortedList = adaptativeMergesort.getList();
        assertEquals(2, customSortedList.getFirstElement().getSize());
        assertEquals(3, customSortedList.getFirstElement().nextNode.getSize());
        assertEquals(3, customSortedList.getFirstElement().nextNode.nextNode.getSize());
        assertEquals(4, customSortedList.getFirstElement().nextNode.nextNode.nextNode.getSize());
        assertEquals(5, customSortedList.getFirstElement().nextNode.nextNode.nextNode.nextNode.getSize());
        assertEquals(6, customSortedList.getFirstElement().nextNode.nextNode.nextNode.nextNode.nextNode.getSize());

        assertEquals(AdaptativeMergesort.TEMP_PATH+"0.txt", customSortedList.getFirstElement().getFileName());
        assertEquals(AdaptativeMergesort.TEMP_PATH+"5.txt", customSortedList.getFirstElement().nextNode.getFileName());
        assertEquals(AdaptativeMergesort.TEMP_PATH+"1.txt", customSortedList.getFirstElement().nextNode.nextNode.getFileName());
        assertEquals(AdaptativeMergesort.TEMP_PATH+"2.txt", customSortedList.getFirstElement().nextNode.nextNode.nextNode.getFileName());
        assertEquals(AdaptativeMergesort.TEMP_PATH+"3.txt", customSortedList.getFirstElement().nextNode.nextNode.nextNode.nextNode.getFileName());
        assertEquals(AdaptativeMergesort.TEMP_PATH+"4.txt", customSortedList.getFirstElement().nextNode.nextNode.nextNode.nextNode.nextNode.getFileName());
    }

    @Test
    public void testSortSmall() throws Exception {
        String input = "input_small.txt";
        adaptativeMergesort = new AdaptativeMergesort(input);

        CustomSortedList customSortedList = adaptativeMergesort.getList();
        adaptativeMergesort.sort();

        BufferedReader reader = new BufferedReader(new FileReader(AdaptativeMergesort.OUT_PATH+input));
        long[] expected = new long[]{1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6};
        String line;
        int i = 0;
        while((line = reader.readLine()) != null){
            assertEquals(expected[i], Long.parseLong(line));
            i += 1;
        }

    }

    @Test
    public void testSort9020() throws Exception {
        String input = "input_90_20%.txt";
        adaptativeMergesort = new AdaptativeMergesort(input);

        CustomSortedList customSortedList = adaptativeMergesort.getList();
        adaptativeMergesort.sort();

        BufferedReader reader = new BufferedReader(new FileReader(AdaptativeMergesort.OUT_PATH+input));
        long[] expected = new long[]{-2144526255, -2129377369, -2117316884, -2086274546, -1987471236, -1967267371, -1925971295, -1867854235, -1838043051, -1801441133, -1776810967, -1736340770, -1642087729, -1600347399, -1582719449, -1568453412, -1567237774, -1531832680, -1526079716, -1516904999, -1462292468, -1436025969, -1384496959, -1365297519, -1315612971, -1290568010, -1262592852, -1227270702, -1175011748, -1119140491, -1029312154, -1003280265, -980562592, -934797870, -853409543, -841006329, -840939200, -830296979, -815910126, -761709636, -651066546, -643135838, -608199792, -441194404, -406487417, -395117198, -384496761, -289076499, -147784453, -101629132, 63068500, 103690769, 109475225, 143681833, 145587043, 151894675, 189722792, 431364535, 433057985, 474598847, 715998078, 770918071, 786105236, 789726076, 795875064, 892183921, 948845443, 950679826, 986481288, 995680822, 996930261, 1061331353, 1108221147, 1212464024, 1270811688, 1271555545, 1273500464, 1466495992, 1470802088, 1492134289, 1509411786, 1533349823, 1560405704, 1697538652, 1775303057, 1876065683, 1905556182, 1974136012, 2018624677, 2100054021};
        String line;
        int i = 0;
        while((line = reader.readLine()) != null){
            assertEquals(expected[i], Long.parseLong(line));
            i += 1;
        }

    }

}