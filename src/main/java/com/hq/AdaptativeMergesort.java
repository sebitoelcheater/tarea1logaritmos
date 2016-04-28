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

    private long n;

    private long k = (long) Math.pow(2, 20);


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
        this.n += 1;

        long startTime = System.currentTimeMillis();
        System.out.println("Split strarted: "+ this.inputFileName);

        while((line = inputFile.readLine()) != null) {
            this.n += 1;
            currentLong = Long.parseLong(line);

            if (prevLong > currentLong){
                currentWriter.close();
                if(i % 10000 == 0)
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
            if(i % 10000 == 0)
            System.out.println("Merge nº "+i+" concluded. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");
            i += 1;
        }

        createFile(OUT_PATH+inputFileName);
        copyFile(new File(list.getFirstElement().getFileName()), new File(OUT_PATH+inputFileName));

    }

    public long getN(){
        return n;
    }
}
