package com.hq;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by jorge on 21-04-16.
 */
public class InstanceFactoryTest {

    InstanceFactory[] factories = new InstanceFactory[3];
    int[] percentages = new int[3];

    @org.junit.Before
    public void setUp() throws Exception {
        this.percentages[0] = 20;
        this.percentages[1] = 50;
        this.percentages[2] = 80;
        for (int i = 0; i < percentages.length; i++) {
            factories[i] = new InstanceFactory((long) Math.pow(2, 10), percentages[i]);
            factories[i].setBufferSize((long) Math.pow(2, 5));
        }
    }

    @org.junit.Test
    public void testCreateFileAllInstances() throws Exception {

        for (int k = 0; k < percentages.length; k++) {
            long[] info = factories[k].createFile();
            long position = info[0];
            long runSize = info[1];

            String p = String.valueOf(percentages[k]);
            File file = new File("Instances/2^10/run_" + p + "%.txt");
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(file));

                long i = 0;
                while (i < position) {
                    reader.readLine();
                    i++;
                }

                long current = Long.valueOf(reader.readLine());
                long j = 0;
                while (j < runSize) {
                    long next = Long.valueOf(reader.readLine());
                    if (next < current) {
                        assertFalse(next < current);
                        break;
                    }
                    current = next;
                    j++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                }
            }
        }
    }
}