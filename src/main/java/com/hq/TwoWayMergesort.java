package com.hq;

import java.io.*;

/**
 * Created by sebito on 21-04-16.
 */
public class TwoWayMergesort extends Mergesort{

    public static String OUT_PATH = "out/TwoWayMergesort/OUT_";

    private static String f1 = "out/TwoWayMergesort/temp/f1.txt";
    private static String f2 = "out/TwoWayMergesort/temp/f2.txt";
    private static String g1 = "out/TwoWayMergesort/temp/g1.txt";
    private static String g2 = "out/TwoWayMergesort/temp/g2.txt";

    private BufferedReader[] originFiles;
    private BufferedWriter[] destinationFiles;
    private String[] originFileNames;
    BufferedReader inputFile;
    private String[] destinationFileNames;
    private int n;
    private String[] currentLines;

    public TwoWayMergesort(String inputFileName) throws IOException {
        super(inputFileName);
        this.originFiles = new BufferedReader[2];
        this.destinationFiles = new BufferedWriter[2];
        this.originFileNames = new String[2];
        this.destinationFileNames = new String[2];
        this.currentLines = new String[2];

        createFile(f1);
        createFile(f2);
        createFile(g1);
        createFile(g2);

        this.destinationFiles[0] = new BufferedWriter(new FileWriter(f1));
        this.destinationFiles[1] = new BufferedWriter(new FileWriter(f2));

        inputFile = new BufferedReader(new FileReader(this.inputFileName));
        int i = 0;
        for(String line; (line = inputFile.readLine()) != null; ) {
            this.destinationFiles[i%2].write(line);
            this.destinationFiles[i%2].newLine();
            i += 1;
        }

        this.n = i;

        this.destinationFiles[0].close();
        this.destinationFiles[1].close();

        this.originFileNames[0] = f1;
        this.originFileNames[1] = f2;

        this.destinationFileNames[0] = g1;
        this.destinationFileNames[1] = g2;

        this.originFiles[0] = new BufferedReader(new FileReader(f1));
        this.originFiles[1] = new BufferedReader(new FileReader(f2));

        this.destinationFiles[0] = new BufferedWriter(new FileWriter(g1));
        this.destinationFiles[1] = new BufferedWriter(new FileWriter(g2));

        currentLines[0] = originFiles[0].readLine();
        currentLines[1] = originFiles[1].readLine();
    }


    @Override
    public void sort() throws IOException {
        // i represents the turn of passing from f1, f2 to g1 g2 (or viceversa)
        long i = 0;
        // j represents the turn of writing the numbers either to g1 or g2 (or viceversa)
        long j = 0;
        long runSize = (long) Math.pow(2, i);
        long size;

        long startTime = System.currentTimeMillis();

        System.out.println("Strart sorting "+ this.inputFileName);

        while(i <= Math.ceil(Math.log(this.n) / Math.log(2))){
            j = 0;
            do{
                size = merge(runSize, destinationFiles[(int)j % 2]);
                j += 1;
            }while (size/2 == runSize);

            System.out.println("iteration: " + i + ", Run Size:" + runSize + ", Total time elapsed: " + (System.currentTimeMillis() - startTime));
            i += 1;
            runSize = (long) Math.pow(2, i);
            swipe();
        }
        System.out.println("The file has been sorted! Total time elapsed: "+(System.currentTimeMillis() - startTime));

        destinationFiles[0].close();
        destinationFiles[1].close();
        originFiles[0].close();
        originFiles[1].close();

        createFile(OUT_PATH +inputFileName);
        copyFile(new File(originFileNames[0]), new File("out/TwoWayMergesort/OUT_"+inputFileName));

    }

    public void swipe() throws IOException {

        originFiles[0].close();
        originFiles[1].close();
        destinationFiles[0].close();
        destinationFiles[1].close();

        String originFileNames0 = this.originFileNames[0];
        String originFileNames1 = this.originFileNames[1];

        this.originFileNames[0] = this.destinationFileNames[0];
        this.originFileNames[1] = this.destinationFileNames[1];

        this.destinationFileNames[0] = originFileNames0;
        this.destinationFileNames[1] = originFileNames1;

        this.originFiles[0] = new BufferedReader(new FileReader(originFileNames[0]));
        this.originFiles[1] = new BufferedReader(new FileReader(originFileNames[1]));

        this.destinationFiles[0] = new BufferedWriter(new FileWriter(destinationFileNames[0]));
        this.destinationFiles[1] = new BufferedWriter(new FileWriter(destinationFileNames[1]));

        currentLines[0] = originFiles[0].readLine();
        currentLines[1] = originFiles[1].readLine();
    }


    public long merge(long size, BufferedWriter writer) throws IOException {
        long[] runCounter = {0, 0};

        long i = 0;
        while(i < 2*size){
            if (currentLines[0] == null && currentLines[1] == null){
                break;
            } else if (currentLines[0] == null){
                while(currentLines[1] != null ){
                    i += 1;
                    writer.write(currentLines[1]);
                    writer.newLine();
                    runCounter[1] += 1;
                    if (runCounter[1] < size)
                        currentLines[1] = originFiles[1].readLine();
                    else
                        currentLines[1] = null;
                }
            } else if (currentLines[1] == null){
                while(currentLines[0] != null){
                    i += 1;
                    writer.write(currentLines[0]);
                    writer.newLine();
                    runCounter[0] += 1;
                    if (runCounter[0] < size)
                        currentLines[0] = originFiles[0].readLine();
                    else
                        currentLines[0] = null;
                }
            } else {
                if(Long.parseLong(currentLines[0]) < Long.parseLong(currentLines[1])){
                    writer.write(currentLines[0]);
                    writer.newLine();
                    i += 1;
                    runCounter[0] += 1;
                    if (runCounter[0] < size)
                        currentLines[0] = originFiles[0].readLine();
                    else
                        currentLines[0] = null;
                } else {
                    writer.write(currentLines[1]);
                    writer.newLine();
                    i += 1;
                    runCounter[1] += 1;
                    if (runCounter[1] < size)
                        currentLines[1] = originFiles[1].readLine();
                    else
                        currentLines[1] = null;
                }
            }
        }
        currentLines[0] = originFiles[0].readLine();
        currentLines[1] = originFiles[1].readLine();
        return i;
    }

    public long getN(){
        return n;
    }
}
