package com.hq;

import static org.junit.Assert.*;

public class InstanceFactoryTest {

    @org.junit.Before
    public void setUp() throws Exception {
        InstanceFactory instanceFactory = new InstanceFactory((long) Math.pow(2, 30), 20);
        instanceFactory.setBufferSize((long) Math.pow(2, 20));
        instanceFactory.createFile();


    }
}