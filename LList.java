/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproj;

/**
 *
 * @author pk914828
 * @param <T>
 */
public class LList<T> implements ListInterface<T> 

{

    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;

    // constructor
    public void LList()
    {
        initializeDataFields();
    }

    
    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    }
    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    // end getNodeAt
    // end initializeDataFields

    /**
     *
     * @param newEntry
     */
    @Override
    public void add(T newEntry) {
        //throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Node currentNode = firstNode;
        Node newNode = new Node(newEntry);
        if (newNode == null) {
            throw new OutOfMemoryError("out of memory");
        }
        if (currentNode == null) {
            firstNode = newNode;
        }
        else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        numberOfEntries++;
    }

    /**
     *
     * @param newPosition
     * @param newEntry
     */
    @Override
    public void add(int newPosition, T newEntry) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int curPosition = 1;
        Node newNode = new Node(newEntry);
        Node currentNode = firstNode;
        if (newNode == null) {
            throw new OutOfMemoryError("Out of memory");
        }
        if (newPosition == 1) {
            newNode.next = firstNode;
            firstNode = newNode;
            numberOfEntries++;
            return;
        }
        if (newPosition < 1 || (newPosition > getLength() + 1)) {
            throw new ArrayIndexOutOfBoundsException("newPosition out of bounds");
        }
        while ((curPosition != (newPosition - 1)) && (currentNode != null)) {
            currentNode = currentNode.next;
            curPosition++;
        }
        if (currentNode != null) {
            newNode.next = currentNode.next;
            currentNode.next= newNode;
            numberOfEntries++;
        }
    }

        /** Removes the entry at a given position from this list.
    * Entries originally at positions higher than the given
    * position are at the next lower position within the list,
    * and the list's size is decreased by 1.
    * @param givenPosition An integer that indicates the position of
    * the entry to be removed.
    * @return A reference to the removed entry.
    * @throws IndexOutOfBoundsException if either
    * givenPosition less than 1, or
    * givenPosition greater than getLength()+1.
    */
    @Override
    public T remove(int givenPosition) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int curPosition = 1;
        T curEntry = null;
        Node currentNode = firstNode;
        Node nodeN = null;
        if (givenPosition < 1 || givenPosition > getLength()) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        if ((givenPosition == 1) && (currentNode != null)) {
            curEntry = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            return curEntry;
        }
        while ((curPosition != (givenPosition - 1)) && (currentNode != null)) {
            currentNode = currentNode.next;
            curPosition++;
        }
        nodeN = currentNode.next;
        curEntry = nodeN.data;
        currentNode.next = nodeN.next;
        numberOfEntries--;
        return curEntry;
    }
    
    @Override
    public void clear() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Nothing to clear");
        }
        while(firstNode != null) {
            firstNode = firstNode.next;
            numberOfEntries--;
        }
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        T origEntry = null;
        int nodePosition = 1;
        Node currentNode = firstNode;
        
        if (givenPosition < 1 || givenPosition > getLength() + 1) {
            throw new ArrayIndexOutOfBoundsException("Position out of bounds");
        }
        if (givenPosition == 1) {
            origEntry = firstNode.data;
            firstNode.data = newEntry;
            return origEntry;
        }
        while (currentNode != null && nodePosition != givenPosition) {
            currentNode = currentNode.next;
            nodePosition++;
        }
        if (currentNode != null) {
            origEntry = currentNode.data;
            currentNode.data = newEntry;
        }
        
        return origEntry;
    }

    /**
     *
     * @param givenPosition
     * @return
     */
    @Override
    public T getEntry(int givenPosition) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Node currentNode = firstNode;
        T curEntry = null;
        int nodePosition = 1;
        if (givenPosition < 1 || givenPosition > getLength()) {
            throw new ArrayIndexOutOfBoundsException("Position out of bounds");
        }
        while (currentNode != null && nodePosition != givenPosition) {
            currentNode = currentNode.next;
            nodePosition++;
        }
        if (currentNode != null) {
            curEntry = currentNode.data;
        }
        return curEntry;
    }

    /**
     *
     * @param anEntry
     * @return
     */
    @Override
    public boolean contains(T anEntry) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        for (int index = 1; index <= getLength(); index++) {
            if (getEntry(index) == anEntry) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLength() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return numberOfEntries;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        System.out.println("com.mycompany.ex3_22c.LList.isEmpty() " + numberOfEntries);
        if (numberOfEntries == 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public T[] toArray() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return result;
    }
 


    private class Node {
        private T data;
        private Node next;
    
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }
    
        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }
    }

 

} // end LList

