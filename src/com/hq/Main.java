package com.hq;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        InstanceFactory factory = new InstanceFactory(100, 20);
        factory.setBufferSize(10);
        factory.createFile();
    }
}
