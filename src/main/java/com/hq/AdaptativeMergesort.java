package com.hq;

import javafx.util.Pair;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by jorgito on 22-04-16.
 */
public class AdaptativeMergesort extends Mergesort {

    private BufferedReader inputFile;
    public static String OUT_PATH = "out/AdaptativeMergesort/OUT_";
    public static String TEMP_PATH = "out/AdaptativeMergesort/temp/";
    private CustomSortedList list;
    private long numberOfRuns;

    private long k = (long) Math.pow(2, 20);
    private long n = (long) Math.pow(2, 30);

    public AdaptativeMergesort(String inputFileName) throws IOException {
        super(inputFileName);

        inputFile = new BufferedReader(new FileReader(inputFileName));
        list = new CustomSortedList();

        long i = 0;
        String line = inputFile.readLine();
        long prevLong = Long.parseLong(line);
        long currentLong;
        long currentRunSize = 1;


        createFile(TEMP_PATH+i+".txt");
        BufferedWriter currentWriter = new BufferedWriter(new FileWriter(TEMP_PATH+i+".txt"));
        currentWriter.write(prevLong+"");
        currentWriter.newLine();

        long startTime = System.currentTimeMillis();
        System.out.println("Split strarted: "+ this.inputFileName);

        while((line = inputFile.readLine()) != null) {
            currentLong = Long.parseLong(line);

            if (prevLong > currentLong){
                currentWriter.close();
                System.out.println("Run nº " + i + " generated. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");

                list.add(new CustomNode(i, TEMP_PATH+i+".txt", currentRunSize));
                currentRunSize = 0;
                i += 1;
                createFile(TEMP_PATH+i+".txt");
                currentWriter = new BufferedWriter(new FileWriter(TEMP_PATH+i+".txt"));
            }
            currentWriter.write(currentLong+"");
            currentWriter.newLine();
            currentRunSize += 1;
            prevLong = currentLong;
        }
        list.add(new CustomNode(i, TEMP_PATH+i+".txt", currentRunSize));
        currentWriter.close();
        System.out.println("Run nº " + i + " generated. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");
        System.out.println("Runs generated successfully");


    }

    public CustomSortedList getList(){
        return list;
    }


    @Override
    public void sort() throws IOException {
        System.out.println("Merging started.");
        long startTime = System.currentTimeMillis();
        long i = 0;

        while(list.getFirstElement().nextNode != null){
            list.mergeTwoFirsts();
            System.out.println("Merge nº "+i+" concluded. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");
            i += 1;
        }

        createFile(OUT_PATH+inputFileName);
        copyFile(new File(list.getFirstElement().getFileName()), new File(OUT_PATH+inputFileName));

    }
}
