package com.hq;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class InstanceFactory {

    private long size = (long) Math.pow(2, 30);
    private long runPercentage = 20;
    private long runSize = (long) (0.2 * (float) size);
    private long bufferSize = (long) Math.pow(2, 20);

    public InstanceFactory(long size, long runPercentage) {
        this.size = size;
        this.runPercentage = runPercentage;
        this.runSize = (long) (((float) size) * (((float) runPercentage) / 100.0f));
    }

    private int setPosition() {
        Random random = new Random();
        return random.nextInt((int) (this.size - this.runSize - 1));
    }

    public void setBufferSize(long bufferSize) {
        this.bufferSize = bufferSize;
    }

    public long[] createFile() {
        String filename = "input_" + String.valueOf(this.runPercentage) + "%.txt";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
        }
        catch (FileNotFoundException | UnsupportedEncodingException e) {}

        Random rnd = new Random();
        int position = setPosition();

        long[] buffer = new long[(int) this.bufferSize];
        int i = 0;
        int j = 0;
        long val = 0;

        long MAX_VAL = 20000000000L;
        while (i < this.size) {
            if (i > position && i < position + runSize) {
                long increment = (long) rnd.nextInt(10);
                val = val + increment;
            }
            else {
                val = -MAX_VAL + (long)(rnd.nextDouble()*(2*MAX_VAL));
            }

            if (j == this.bufferSize) {
                for (long number : buffer)
                    writer.println(number);
                j = 0;
            }

            buffer[j] = val;
            i++;
            j++;
        }
        writer.close();
    }
}