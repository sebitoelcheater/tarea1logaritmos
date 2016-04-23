package com.hq;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by jorge on 21-04-16.
 */
public class InstanceFactoryTest {

    long position;
    long runSize;

    @org.junit.Before
    public void setUp() throws Exception {
        InstanceFactory factory = new InstanceFactory(50, 50);
        factory.setBufferSize(10);
        long[] info = factory.createFile();
        position = info[0];
        runSize = info[1];
    }

    @org.junit.Test
    public void testCreateFile() throws Exception {
        File file = new File("input_50%.txt");
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
                    System.out.println("next: "+next+", current: "+current+", line: "+i+j+1);
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