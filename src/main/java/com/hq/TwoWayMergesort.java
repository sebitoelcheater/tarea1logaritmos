package com.hq;

import java.io.*;
import java.util.List;

/**
 * Created by sebito on 21-04-16.
 */
public class TwoWayMergesort extends Mergesort{

    public String inputFileName;

    private static String f1 = "out/f1.txt";
    private static String f2 = "out/f2.txt";
    private static String g1 = "out/g1.txt";
    private static String g2 = "out/g2.txt";

    private BufferedReader[] originFiles;
    private BufferedWriter[] destinationFiles;
    private String[] originFileNames;
    BufferedReader inputFile;
    private String[] destinationFileNames;
    private int n;

    public TwoWayMergesort(String inputFileName) throws IOException {
        super(inputFileName);
        this.originFiles = new BufferedReader[2];
        this.destinationFiles = new BufferedWriter[2];

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
            i += 1;
        }

        this.destinationFiles[0].close();
        this.destinationFiles[1].close();

        this.originFiles[0] = new BufferedReader(new FileReader(f1));
        this.originFiles[1] = new BufferedReader(new FileReader(f2));

        this.destinationFiles[0] = new BufferedWriter(new FileWriter(g1));
        this.destinationFiles[1] = new BufferedWriter(new FileWriter(g2));
    }


    @Override
    public void sort() throws IOException {
        long i = 0;
        long runSize = (long) Math.pow(2, i);;
        long size;

        while(runSize < this.n){
            do{
                size = merge(runSize, destinationFiles[(int)i % 2]);
            }while (false);

        }
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
    }


    public long merge(long size, BufferedWriter writer) throws IOException {
        String currentLine0 = originFiles[0].readLine();
        String currentLine1 = originFiles[1].readLine();

        long i = 0;
        while(i < size){
            if(currentLine0 == null){
                while((currentLine1 = originFiles[1].readLine()) != null && i<size){
                    i += 1;
                    writer.write(currentLine1);
                }
            } else if (currentLine1 == null){
                while((currentLine0 = originFiles[0].readLine()) != null && i<size){
                    i += 1;
                    writer.write(currentLine1);
                }
            } else if (currentLine0 == null && currentLine1 == null){
                break;
            } else {
                if(Long.parseLong(currentLine0) < Long.parseLong(currentLine1)){
                    writer.write(currentLine0);
                    currentLine0 = originFiles[0].readLine();
                } else {
                    writer.write(currentLine1);
                    currentLine1 = originFiles[1].readLine();
                }
            }
        }
        return i;
    }
}
