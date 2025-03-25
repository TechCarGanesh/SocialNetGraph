/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.finalproj;


import java.util.Iterator;
/**
   An interface for the ADT list that has an iterator.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T>
{
   public Iterator<T> getIterator();
} // end ListWithIteratorInterface
