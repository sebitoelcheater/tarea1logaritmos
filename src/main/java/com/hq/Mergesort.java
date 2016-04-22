package com.hq;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Created by sebito on 21-04-16.
 */
public abstract class Mergesort {

    String inputFileName;
    BufferedReader inputFile;

    long n = (long) Math.pow(2, 30);

    public Mergesort (String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void createFile(String path) throws IOException {
        File f = new File(path);

        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    public abstract void sort() throws IOException;
}
