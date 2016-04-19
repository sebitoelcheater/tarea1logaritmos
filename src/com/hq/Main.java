package com.hq;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] test = new int[(int) Math.pow(2, 20)];
        int i = 0;
        Random random = new Random();
        while (i < test.length) {
            test[i] = random.nextInt(1000000);
        }
    }
}
