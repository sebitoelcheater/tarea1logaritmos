package com.hq;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        InstanceFactory factory = new InstanceFactory((long) Math.pow(2, 30), 20);
        factory.setBufferSize((long) Math.pow(2, 20));
        factory.createFile();
    }
}
