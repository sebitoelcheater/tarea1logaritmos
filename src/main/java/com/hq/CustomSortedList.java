package com.hq;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by sebito on 24-04-16.
 */
public class CustomSortedList {
    private CustomNode firstElement;
    private long length;

    public CustomSortedList(){
        firstElement = null;

    }

    public void add(CustomNode node){
        if (firstElement == null){
            firstElement = node;
        }else if (node.getSize() <= firstElement.getSize()){
            node.nextNode = firstElement;
            firstElement = node;
        } else {
            CustomNode prevNode = firstElement;
            CustomNode nextNode = prevNode.nextNode;
            while (nextNode != null) {
                if (node.getSize() <= nextNode.getSize()) {
                    prevNode.nextNode = node;
                    node.nextNode = nextNode;
                    return;
                } else {
                    prevNode = nextNode;
                    nextNode = nextNode.nextNode;
                }
            }
            prevNode.nextNode = node;
        }

    }

    public CustomNode getFirstElement(){
        return firstElement;
    }

    public CustomNode get(long position){
        long i = 0;
        CustomNode currentNode = firstElement;
        while (currentNode != null) {
            if(position == i)
                return currentNode;
            i += 1;
        }
        return null;
    }

    public boolean mergeTwoFirsts() throws IOException {
        CustomNode nodeA = firstElement;
        if (nodeA == null)
            return false;
        CustomNode nodeB = firstElement.nextNode;
        if (nodeB == null)
            return false;
        BufferedReader bufferedReaderA = new BufferedReader(new FileReader(nodeA.getFileName()));
        BufferedReader bufferedReaderB = new BufferedReader(new FileReader(nodeB.getFileName()));

        String lineA = bufferedReaderA.readLine();
        String lineB = bufferedReaderB.readLine();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(AdaptativeMergesort.TEMP_PATH+nodeA.getId()+"-"+nodeB.getId()+".txt"));

        while(true){
            if(lineA == null && lineB == null)
                break;
            else if (lineA == null){
                while (lineB != null){
                    bufferedWriter.write(lineB);
                    bufferedWriter.newLine();
                    lineB = bufferedReaderB.readLine();
                }
                break;
            } else if (lineB == null){
                while (lineA != null){
                    bufferedWriter.write(lineA);
                    bufferedWriter.newLine();
                    lineA = bufferedReaderA.readLine();
                }
                break;
            } else {
                if(Long.parseLong(lineA) < Long.parseLong(lineB)){
                    bufferedWriter.write(lineA);
                    bufferedWriter.newLine();
                    lineA = bufferedReaderA.readLine();
                } else {
                    bufferedWriter.write(lineB);
                    bufferedWriter.newLine();
                    lineB = bufferedReaderB.readLine();
                }
            }
        }

        bufferedWriter.close();
        Mergesort.copyFile(new File(AdaptativeMergesort.TEMP_PATH+nodeA.getId()+"-"+nodeB.getId()+".txt"), new File(nodeA.getFileName()));

        File f1 = new File(AdaptativeMergesort.TEMP_PATH+nodeA.getId()+"-"+nodeB.getId()+".txt");
        f1.delete();
        File f2 = new File(nodeB.getFileName());
        f2.delete();

        firstElement = nodeB.nextNode;

        CustomNode newNode = new CustomNode(nodeA.getId(), nodeA.getFileName(), nodeA.getSize() + nodeB.getSize());
        this.add(newNode);

        return true;

    }

    public void print(){
        CustomNode current = firstElement;
        while (current != null){
            System.out.println("filename: " + current.getFileName() + ", size: " + current.getSize());
            current = current.nextNode;
        }
    }
}
