package com.hq;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by jorgito on 22-04-16.
 */
public class AdaptativeMergesort extends Mergesort {

    private BufferedReader inputFile;
    public static long MIN_RUN_SIZE = 5;//(long) Math.pow(2, 30);
    public static String OUT_PATH = "out/AdaptativeMergesort/OUT_";
    public static String TEMP_PATH = "out/AdaptativeMergesort/temp/";
    private CustomSortedList list;
    private long numberOfRuns;

    private long n;



    public AdaptativeMergesort(String inputFileName) throws IOException {
        super(inputFileName);

        inputFile = new BufferedReader(new FileReader(inputFileName));
        list = new CustomSortedList();

        String line;
        long prevLong;
        long currentLong;
        long i = -1;
        long currentRunSize = 0;
        boolean ascendant;
        n = 0;

        ArrayList<Long> longs;

        long startTime = System.currentTimeMillis();
        System.out.println("Split strarted: "+ this.inputFileName);
        longs = new ArrayList<Long>();

        while((line = inputFile.readLine()) != null) {
            ascendant = true;
            n += 1;
            i += 1;
            // crear archivo
            createFile(TEMP_PATH + i + ".txt");
            BufferedWriter currentWriter = new BufferedWriter(new FileWriter(TEMP_PATH + i + ".txt"));
            // inicializar run en RAM
            longs.add(Long.parseLong(line));

            prevLong = Long.parseLong(line);
            while (longs.size() < MIN_RUN_SIZE) {
                line = inputFile.readLine();
                if (line == null) {
                    break;
                } else {
                    n += 1;
                    currentLong = Long.parseLong(line);
                    longs.add(currentLong);
                    ascendant = ascendant && prevLong <= currentLong;

                    prevLong = currentLong;
                }
            }

            currentRunSize = longs.size();

            Collections.sort(longs);
            for (long l : longs) {
                currentWriter.write(l + "");
                currentWriter.newLine();
            }

            if(!ascendant || line == null){
                currentWriter.close();
                list.add(new CustomNode(i, TEMP_PATH+i+".txt", currentRunSize));
                longs = new ArrayList<Long>();

//                if(i % 10000 == 0)
                System.out.println("Run nº " + i + " generated. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");
            } else {
                while((line = inputFile.readLine()) != null) {
                    this.n += 1;
                    currentLong = Long.parseLong(line);
                    if (prevLong > currentLong) {
                        currentWriter.close();
                        list.add(new CustomNode(i, TEMP_PATH + i + ".txt", currentRunSize));
                        longs = new ArrayList<Long>();
                        longs.add(currentLong);
//                if(i % 10000 == 0)
                        System.out.println("Run nº " + i + " generated. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");
                        break;
                    } else {
                        currentWriter.write(currentLong + "");
                        currentWriter.newLine();
                        currentRunSize += 1;
                        prevLong = currentLong;
                    }
                }
                if(line == null){
                    currentWriter.close();
                    list.add(new CustomNode(i, TEMP_PATH + i + ".txt", currentRunSize));
//                if(i % 10000 == 0)
                    System.out.println("Run nº " + i + " generated. (Elapsed: "+(System.currentTimeMillis() - startTime)+" ms)");
                }
            }
        }

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
