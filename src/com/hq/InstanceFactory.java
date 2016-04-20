package com.hq;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class InstanceFactory {

    private int size = (int) Math.pow(2, 30);
    private int runPercentage = 20;
    private int runSize = (int) (0.2 * (float) size);
    private int bufferSize = (int) Math.pow(2, 20);

    public InstanceFactory(int size, int runPercentage) {
        this.size = size;
        this.runPercentage = runPercentage;
        this.runSize = (int) (((float) size) * (((float) runPercentage) / 100.0f));
    }

    private int setPosition() {
        Random random = new Random();
        return random.nextInt(this.size - this.runSize);
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void createFile() {
        String filename = "input_" + String.valueOf(this.runPercentage) + "%.txt";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
        }
        catch (FileNotFoundException | UnsupportedEncodingException fnf) {}

        Random rnd = new Random();
        int position = setPosition();

        int[] buffer = new int[this.bufferSize];
        int i = 0;
        int j = 0;
        int min = Integer.MIN_VALUE;
        while (i < this.size) {

            int val;
            if (i >= position - 1 && i <= position + runSize) {
                val = min + rnd.nextInt(Integer.MAX_VALUE);
                min = val;
            }
            else {
                val = rnd.nextInt();
            }

            if (j == this.bufferSize) {
                for (int number : buffer)
                    writer.println(number);
                j = 0;
            }

            buffer[j] = val;
            i++;
            j++;

            System.out.println("i = " + Integer.toString(i));
            System.out.println("j = " + Integer.toString(j));
            if (i == position) {
                System.out.println(val);
            }
        }
        writer.close();
    }
}