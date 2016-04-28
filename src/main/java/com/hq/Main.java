package com.hq;

/**
 * Created by jorgito on 19-04-16.
 */
public class Main {

    public static void main(String[] args) {
        InstanceFactory factory20 = new InstanceFactory((long) Math.pow(2, 25), 20);
        InstanceFactory factory50 = new InstanceFactory((long) Math.pow(2, 25), 50);
        InstanceFactory factory80 = new InstanceFactory((long) Math.pow(2, 25), 80);
        factory20.setBufferSize((long) Math.pow(2, 15));
        factory50.setBufferSize((long) Math.pow(2, 15));
        factory80.setBufferSize((long) Math.pow(2, 15));
        factory20.createFile();
        factory50.createFile();
        factory80.createFile();
    }
}
