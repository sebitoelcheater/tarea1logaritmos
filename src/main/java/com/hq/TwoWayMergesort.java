package com.hq;

import java.io.*;
import java.util.List;

/**
 * Created by sebito on 21-04-16.
 */
public class TwoWayMergesort implements Mergesort{
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
        this.inputFileName = inputFileName;
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
        int runSize = 1;
        int i0 = 0;
        int i1 = 0;

        while(runSize < this.n){
            double current0 = Double.parseDouble(originFiles[0].readLine());
            double current1 = Double.parseDouble(originFiles[1].readLine());


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

    public List<Double> merge(int size) throws IOException {
        double current0 = Double.parseDouble(originFiles[0].readLine());
        double current1 = Double.parseDouble(originFiles[1].readLine());
        
    }

    public void createFile(String path) throws IOException {
        File f = new File(path);

        f.getParentFile().mkdirs();
        f.createNewFile();
    }
}
