package com.hq;

import java.io.PrintWriter;
import java.util.Random;

public class InstanceFactory {

    private int size = (int) Math.pow(2, 30);
    private int runPercentage = 20;
    private int runSize = (int) 0.2*size;
    private int bufferSize = (int) Math.pow(2, 20);

    public InstanceFactory(int sizeExp, float runRatio) {
        this.size = (int) Math.pow(2, sizeExp);
        this.runSize = (int) runRatio*this.size;
        this.runPercentage = (int) runRatio*100;
    }

    private int setPosition() {
        Random random = new Random();
        int position = random.nextInt(this.size - this.runSize);
        return position;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    private void createFile() {
        String filename = "input_" + String.valueOf(this.runPercentage) + "%.txt";
        PrintWriter writer = new PrintWriter(filename, "UTF-8");

        Random random = new Random();
        int position = setPosition();

        int[] buffer = new int[this.bufferSize];
        int i = 0;
        while (i < this.size) {
            int j = 0;
            if (i < )
            while (j < this.bufferSize) {
                buffer[j] = random.nextInt();
                j++;

            }
        }
    }

}