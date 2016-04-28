package com.hq;

import java.io.BufferedReader;

/**
 * Created by sebito on 24-04-16.
 */
public class CustomNode {
    private String fileName;
    private long size;
    private long id;
    public CustomNode nextNode;

    public CustomNode(long id, String fileName, long size){
        this.fileName = fileName;
        this.size = size;
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    public long getId() {
        return id;
    }
}
