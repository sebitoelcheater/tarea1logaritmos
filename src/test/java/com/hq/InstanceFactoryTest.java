package com.hq;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by jorge on 21-04-16.
 */
public class InstanceFactoryTest {

    long position20;
    long position50;
    long position80;
    long runSize20;
    long runSize50;
    long runSize80;

    @org.junit.Before
    public void setUp() throws Exception {
        InstanceFactory factory20 = new InstanceFactory((long) Math.pow(2, 30), 20);
        //InstanceFactory factory50 = new InstanceFactory((long) Math.pow(2, 30), 50);
        //InstanceFactory factory80 = new InstanceFactory((long) Math.pow(2, 30), 80);
        factory20.setBufferSize((long) Math.pow(2, 20));
        //factory50.setBufferSize((long) Math.pow(2, 20));
        //factory80.setBufferSize((long) Math.pow(2, 20));
        long[] info20 = factory20.createFile();
        //long[] info50 = factory50.createFile();
        //long[] info80 = factory80.createFile();
        position20 = info20[0];
        //position50 = info50[0];
        //position80 = info80[0];
        runSize20 = info20[1];
        //runSize50 = info50[1];
        //runSize80 = info80[1];
    }

    @org.junit.Test
    public void testCreateFile() throws Exception {
        File file = new File("input_20%.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));

            long i = 0;
            while (i < position20) {
                reader.readLine();
                i++;
            }

            long current = Long.valueOf(reader.readLine());
            long j = 0;
            while (j < runSize20) {
                long next = Long.valueOf(reader.readLine());
                if (next < current) {
                    assertFalse(next < current);
                    break;
                }
                current = next;
                j++;
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            try {
                if (reader != null)
                    reader.close();
            }
            catch (IOException e) {}
        }
    }
}