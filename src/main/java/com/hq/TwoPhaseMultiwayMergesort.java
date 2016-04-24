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
    String[] pieceFileNames = new String[nOfFiles];

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
                this.pieceFiles[i].write(Long.toString(elements[j]) + "\n");
            }

            this.pieceFiles[i].close();
        }
    }

    @Override
    public void sort() throws IOException {
        createFile("out/TwoPhaseMultiwayMergesort/OUT_" + inputFileName);
        PrintWriter outputFile = new PrintWriter("out/TwoPhaseMultiwayMergesort/OUT_" + inputFileName, "UTF-8");

        boolean open = false;
        long last_min = Long.MIN_VALUE;
        BufferedReader[] pieces = new BufferedReader[nOfFiles];

        long[] readCounter = new long[nOfFiles];
        Arrays.fill(readCounter, 0);

        for (int i = 0; i < n; i++) {

            long min = Long.MAX_VALUE;
            for (int j = 0; j < this.nOfFiles; j++) {

                if (!open)
                    pieces[j] = new BufferedReader(new FileReader("out/TwoPhaseMultiwayMergesort_" + inputFileName + "/piece_" + j + ".txt"));

                if (readCounter[j] < k) {

                    pieces[j].mark(100);
                    long val = Long.parseLong(pieces[j].readLine());

                    if (val == last_min) {
                        readCounter[j]++;
                        if (readCounter[j] < k) {
                            pieces[j].mark(100);
                            val = Long.parseLong(pieces[j].readLine()); }
                        else {
                            continue; }
                        last_min = Long.MIN_VALUE;
                    }

                    pieces[j].reset();
                    min = val < min ? val : min;
                }
            }

            last_min = min;
            open = true;

            outputFile.println(Long.toString(min));
        }

        outputFile.close();

        for (int j = 0; j < this.nOfFiles; j++)
            pieces[j].close();
    }

    public void createFiles(int nOfFiles) throws IOException {
        String prefix = "out/TwoPhaseMultiwayMergesort_" + inputFileName + "/piece_";
        for (int i = 0; i < nOfFiles; i++) {
            pieceFileNames[i] = prefix + i + ".txt";
            this.createFile(pieceFileNames[i]);
        }
    }
}
