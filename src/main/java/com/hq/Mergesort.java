package com.hq;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by sebito on 21-04-16.
 */
public abstract class Mergesort {

    String inputFileName;
    BufferedReader inputFile;

    public Mergesort (String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void createFile(String path) throws IOException {
        File f = new File(path);

        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    public static void copyFile( File from, File to ) throws IOException {
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public abstract void sort() throws IOException;
}
