package com.hq;

import java.io.*;
import java.util.Arrays;

public class TwoPhaseMultiwayMergesort extends Mergesort {

    private long k;
    private long n;

    private int nOfFiles;
    private BufferedWriter[] pieceFiles;
    private String[] pieceFileNames;

    public TwoPhaseMultiwayMergesort(String inputFileName, long n, long k) throws IOException {
        super(inputFileName);
        this.n = n;
        this.k = k;
        this.nOfFiles = (int) (n/k);
        this.pieceFiles = new BufferedWriter[nOfFiles];
        this.pieceFileNames = new String[nOfFiles];

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

        BufferedReader[] pieces = new BufferedReader[nOfFiles];
        for (int k = 0; k < nOfFiles; k++)
            pieces[k] = new BufferedReader(new FileReader(pieceFileNames[k]));

        long[] readCounter = new long[nOfFiles];
        long last_min = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            long min = Long.MAX_VALUE;
            for (int j = 0; j < this.nOfFiles; j++) {

                if (readCounter[j] < k) {
                    pieces[j].mark(100);
                    long val = Long.parseLong(pieces[j].readLine());

                    if (val == last_min) {
                        readCounter[j]++;
                        if (readCounter[j] < k) {
                            pieces[j].mark(100);
                            val = Long.parseLong(pieces[j].readLine());
                        }
                        else {
                            pieces[j].close();
                            continue;
                        }
                        last_min = Long.MIN_VALUE;
                    }

                    pieces[j].reset();
                    min = val < min ? val : min;
                }
            }
            last_min = min;
            outputFile.println(Long.toString(last_min));
        }
        outputFile.close();
    }

    public void createFiles(int nOfFiles) throws IOException {
        String prefix = "out/TwoPhaseMultiwayMergesort_" + inputFileName + "/piece_";
        for (int i = 0; i < nOfFiles; i++) {
            pieceFileNames[i] = prefix + i + ".txt";
            this.createFile(pieceFileNames[i]);
        }
    }
}
