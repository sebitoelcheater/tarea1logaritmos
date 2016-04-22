package com.hq;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jorgito on 22-04-16.
 */
public class TwoPhaseMultiwayMergesort extends Mergesort {

    private long k = (long) Math.pow(2, 20);

    int nOfFiles = (int) (n/k);
    BufferedWriter[] pieceFiles = new BufferedWriter[nOfFiles];
    String[] pieceFileNames = null;

    public TwoPhaseMultiwayMergesort(String inputFileName) throws IOException {
        super(inputFileName);

        createFiles(nOfFiles);

        inputFile = new BufferedReader(new FileReader(this.inputFileName));

        long[] elements = new long[(int) k];
        for (int i = 0; i < nOfFiles; i++) {
            pieceFiles[i] = new BufferedWriter(new FileWriter(pieceFileNames[i]));

            for (int j = 0; j < k; j++) {
                long number = Long.parseLong(inputFile.readLine());
                elements[j] = number;
            }
            Arrays.sort(elements);

            for (int j = 0; j < k; j++) {
                this.pieceFiles[i].write(Long.toString(elements[j]));
            }

            this.pieceFiles[i].close();
        }
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

    public void merge(){

    }

    public void createFiles(int nOfFiles) throws IOException {
        String prefix = "out/piece_";
        for (int i = 0; i < nOfFiles; i++) {
            pieceFileNames[i] = prefix + i + ".txt";
            this.createFile(pieceFileNames[i]);
        }
    }
}
