package com.hq;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jorgito on 22-04-16.
 */
public class AdaptativeMergesort extends Mergesort {

    public String inputFileName;

    private String[] originFileNames;
    private String[] destinationFileNames;

    private BufferedReader inputFile;

    private BufferedReader[] originFiles;
    private BufferedWriter[] destinationFiles;

    private long k = (long) Math.pow(2, 20);

    public AdaptativeMergesort(String inputFileName) throws IOException {
        super(inputFileName);
        this.inputFileName = inputFileName;

        int nOfFiles = (int) (n/k);
        this.originFileNames = new String[nOfFiles];
        this.destinationFileNames = new String[nOfFiles];

        this.originFiles = new BufferedReader[nOfFiles];
        this.destinationFiles = new BufferedWriter[nOfFiles];

        for (String fileName : this.originFileNames)
            createFile(fileName);
        for (String fileName : this.destinationFileNames)
            createFile(fileName);

        inputFile = new BufferedReader(new FileReader(this.inputFileName));

        long[] elements = new long[(int) k];
        for (int i = 0; i < nOfFiles; i++) {
            destinationFiles[i] = new BufferedWriter(new FileWriter(originFileNames[i]));

            for (int j = 0; j < k; j++) {
                long number = Long.parseLong(inputFile.readLine());
                elements[j] = number;
                j++;
            }
            Arrays.sort(elements);
            for (int j = 0; j < k; j++) {
                this.destinationFiles[i].write(Long.toString(elements[j]));
            }

            this.destinationFiles[i].close();
            i++;
        }

//        this.originFiles[0] = new BufferedReader(new FileReader(f1));
//        this.originFiles[1] = new BufferedReader(new FileReader(f2));
//
//        this.destinationFiles[0] = new BufferedWriter(new FileWriter(g1));
//        this.destinationFiles[1] = new BufferedWriter(new FileWriter(g2));
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


    public void createFile(String path) throws IOException {
        File f = new File(path);

        f.getParentFile().mkdirs();
        f.createNewFile();
    }
}
