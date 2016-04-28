package com.hq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sebito on 24-04-16.
 */
public class CustomSortedListTest {
    public CustomSortedList list;

    @Before
    public void setUp() throws Exception {
        list = new CustomSortedList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        CustomNode node1 = new CustomNode(1, "1", 4);
        CustomNode node2 = new CustomNode(2, "2", 7);
        CustomNode node3 = new CustomNode(3, "3", 5);
        CustomNode node4 = new CustomNode(4, "4", 1);
        CustomNode node5 = new CustomNode(5, "5", 10);
        CustomNode node6 = new CustomNode(4, "4", 1);

        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);

        assertEquals(list.getFirstElement().getFileName(), node4.getFileName());
        assertEquals(list.getFirstElement().nextNode.getFileName(), node4.getFileName());
        assertEquals(list.getFirstElement().nextNode.nextNode.getFileName(), node1.getFileName());
        assertEquals(list.getFirstElement().nextNode.nextNode.nextNode.getFileName(), node3.getFileName());
        assertEquals(list.getFirstElement().nextNode.nextNode.nextNode.nextNode.getFileName(), node2.getFileName());
        assertEquals(list.getFirstElement().nextNode.nextNode.nextNode.nextNode.nextNode.getFileName(), node5.getFileName());

        list.print();


    }
}