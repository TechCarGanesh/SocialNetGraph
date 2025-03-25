/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproj;

import java.util.Iterator;
import java.util.Arrays;

/** 
   A driver that demonstrates the class LinkedDictionary.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class Driver
{
        // Create a graph
    private static SocialNetworkGraph network = new SocialNetworkGraph();
    private static StackInterface<UserProfile> path = new LinkedStack<>();
            
    
    public static void main(String[] args) {
        testSocialNetwork();
        System.out.println("\n\nDone.");

    }
        
        public static void displayStringArray(String[] array) {
            for (int index = 0; index < array.length; index++) {
                System.out.print(array[index]);
                if (index < array.length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.print("\n");
                }
            }
        }
        
        public static void testBFS(UserProfile v)
	{
		System.out.println("\n\nBreadth-First Traversal beginning at vertex " + v + ": ");
		QueueInterface<UserProfile> bfs = network.getBreadthFirstTraversal(v);
		
                printNames(bfs);

	} // end testBFS
        
        public static void testDFS(UserProfile v)
	{
		System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ": ");
		QueueInterface<UserProfile> dfs = network.getDepthFirstTraversal(v);
		
		printNames(dfs);
	} // end testDFS
        
        public static void printQueue(QueueInterface<UserProfile> q)
	{
		while (!q.isEmpty())
			System.out.print(q.dequeue().getEmail() + " ");
		System.out.println(" Actual");
	} // end printQueue

        public static void printNames(QueueInterface<UserProfile> q)
	{
		while (!q.isEmpty())
			System.out.print(q.dequeue().getName() + " ");
		System.out.println(" Actual");
	} // end printQueue
        
       
	public static void testSocialNetwork()
	{
            // Create a graph
            // Users
            String johnEmail = "john@example.com";
            String janeEmail = "jane@example.com";
            String tomEmail = "tom@example.com";
            String dirkEmail = "dirk@example.com";
            String matEmail = "mat@example.com";
            String bobEmail = "bob@example.com";
            String charlieEmail = "charlie@example.com";
            String jimEmail = "jim@example.com";
            String maryEmail = "mary@example.com";
            String martinEmail = "martin@example.com";
                
            // Create user profiles
            System.out.println("\nTesting create profiles:\n");
            UserProfile p1 = new UserProfile("John", "Doe", johnEmail, "1234567890");
            UserProfile p2 = new UserProfile("Jane", "Smith", janeEmail, "0987654321");
            UserProfile p3 = new UserProfile("Thomas", "Jefferson", tomEmail, "135790246");
            UserProfile p4 = new UserProfile("Dirk", "Mayer", dirkEmail, "5556781212");
            UserProfile p5 = new UserProfile("Matthew", "Jhonson", matEmail, "8007001234");
            UserProfile p6 = new UserProfile("Bob", "Barker", bobEmail, "717123555");
            UserProfile p7 = new UserProfile("Charlie", "Jones", charlieEmail, "6157771223");
            UserProfile p8 = new UserProfile("Jim", "Jhonson", jimEmail, "9809991675");
            UserProfile p9 = new UserProfile("Mary", "Hart", maryEmail, "9199268877");
            UserProfile p10 = new UserProfile("Martin", "Hunter", martinEmail, "9867781243");


            // Add to the network
            System.out.println("\nTesting add graph vertices:\n");
            network.addVertex(p1);
            network.addVertex(p2);
            network.addVertex(p3);
            network.addVertex(p4);
            network.addVertex(p5);
            network.addVertex(p6);
            network.addVertex(p7);
            network.addVertex(p8);
            network.addVertex(p9);
            network.addVertex(p10);
            System.out.println("\nThere should be 10 vertices:" + "Actual: " +
                    network.getNumberOfVertices());

            network.displayVertices();
            
            System.out.println("\nNo friends added");

            System.out.println("\nAdding connections adds friends");
            // Add edges
            network.addEdge(p1, p2);
            network.addEdge(p1, p3);
            network.addEdge(p1, p4);
            network.addEdge(p2, p3);
            network.addEdge(p2, p4);
            network.addEdge(p3, p4);
            network.addEdge(p3, p5);
            network.addEdge(p4, p6);
            network.addEdge(p4, p7);
            network.addEdge(p5, p1);
            network.addEdge(p5, p8);
            network.addEdge(p6, p1);
            network.addEdge(p7, p9);
            network.addEdge(p8, p1);
            network.addEdge(p8, p10);
            network.addEdge(p9, p10);
            network.addEdge(p10, p7);
            network.addEdge(p10, p5);

            System.out.println("Testing friends List: ");
            System.out.println("John Doe " + p1.getNumFriends() + " Friends");
            network.displayFriends(p1);
            System.out.println("Jane Smith " + p2.getNumFriends() + " Friends");
            network.displayFriends(p2);
            System.out.println("Thomas Jefferson " + p3.getNumFriends() + " Friends");
            network.displayFriends(p3);
            System.out.println("Dirk Mayer " + p4.getNumFriends() + " Friends");
            network.displayFriends(p4);
            System.out.println("Matthew Johnson " + p5.getNumFriends() + " Friends");
            network.displayFriends(p5);
            System.out.println("Bob Barker " + p6.getNumFriends() + " Friends");
            network.displayFriends(p6);
            System.out.println("Charlie Jones " + p7.getNumFriends() + " Friends");
            network.displayFriends(p7);
            System.out.println("Jim Johnson " + p8.getNumFriends() + " Friends");
            network.displayFriends(p8);
            System.out.println("Mary Hart " + p9.getNumFriends() + " Friends");
            network.displayFriends(p9);
            System.out.println("Martin Hunter " + p10.getNumFriends() + " Friends");
            network.displayFriends(p10);

            System.out.println("\nTesting BFS from node John");
            testBFS(p1);
            System.out.println("John Doe Jane Smith Thomas Jefferson Dirk Mayer"
                    + "Matthew Jhonson Bob Barker Jim Jhonson Charlie Jones Martin Hunter Mary Hart");
            System.out.println("Testing DFS from Node John");
            testDFS(p1);
            System.out.println("John Doe Jim Jhonson Martin Hunter Charlie Jones Mary Hart Bob Barker "
                    + "Matthew Jhonson Dirk Mayer Thomas Jefferson Jane Smith");

            System.out.println("\n Testing Remove edges will remove friends: ");
            network.removeEdge(p1, p2);
            network.removeEdge(p10, p8);
            network.removeEdge(p6, p4);

            System.out.println(p1.getName() + " has " + p1.getNumFriends() + " Friends");
            network.displayFriends(p1);
            System.out.println(p2.getName() + " has " + p2.getNumFriends() + " Friends");
            network.displayFriends(p2);
            System.out.println(p4.getName() + " has " + p4.getNumFriends() + " Friends");
            network.displayFriends(p4);
            System.out.println(p6.getName() + " has " + p6.getNumFriends() + " Friends");
            network.displayFriends(p6);

            // Test modify profile
            System.out.println("\n\nTesting modifyProfile:\n");
            p1.modifyProfile("John", "Doe", johnEmail, "1112223333");
            p2.modifyProfile("Jane", "Smith", janeEmail, "5551231234");
            p5.modifyProfile("Matthew", "Johnson", johnEmail, "9998761234");

            // Test add new users
            System.out.println("\n\nTesting add():\n");
            UserProfile p11 = new UserProfile("Judy", "Janice", "judy@example.com", "2123335467");
            UserProfile p12 = new UserProfile("Janet", "Huang", "janet@example.com", "7876541212");
            UserProfile p13 = new UserProfile("Jackie", "Chan", "tom@example.com", "9334441256");
            UserProfile p14 = new UserProfile("Jimmy", "George", "tom@example.com", "8772331212");
            UserProfile p15 = new UserProfile("Thomas", "Murphy", "tom@example.com", "9881223654");

            network.addVertex(p11);
            network.addVertex(p12);
            network.addVertex(p13);
            network.addVertex(p14);
            network.addVertex(p15);
            network.addVertex(p15);

            System.out.println("\n There should be 15 vertices:" + "Actual: " +
                    network.getNumberOfVertices() + "No friends for any new users\n");
            network.displayVertices();

            // Judy Janice
            network.addEdge(p11, p5); // Matthew Johnson
            network.addEdge(p11, p12); // Janet Huang 
            network.addEdge(p11, p4); // Dirk Mayer
            network.addEdge(p11, p3); // Thomas Jefferson

            // Janet Huang
            network.addEdge(p12, p13); // Jackie Chan
            network.addEdge(p12, p15); // Thomas Murphy
            network.addEdge(p12, p2); // Jane Smith

            // Jackie Chan
            network.addEdge(p13, p12); // Janet Huang
            network.addEdge(p13, p10); // Martin Hunter

            // Jimmy George
            network.addEdge(p14, p12); // Janet Huang
            network.addEdge(p14, p11); // Judy Janice
            network.addEdge(p14, p15); // Thomas Murphy

            // Thomas Murphy
            network.addEdge(p15, p14); // Jimmy George
            network.addEdge(p15, p12); // Janet Huang
            network.addEdge(p15, p9); // Mary Hart
            System.out.println("Testing friends List: ");
            System.out.println(p1.getName() + " has " + p1.getNumFriends() + " Friends");
            network.displayFriends(p1);
            System.out.println(p2.getName() + " has " + p2.getNumFriends() + " Friends");
            network.displayFriends(p2);
            System.out.println(p3.getName() + " has " + p3.getNumFriends() + " Friends");
            network.displayFriends(p3);
            System.out.println(p4.getName() + " has " + p4.getNumFriends() + " Friends");
            network.displayFriends(p4);
            System.out.println(p5.getName() + " has " + p5.getNumFriends() + " Friends");
            network.displayFriends(p5);
            System.out.println(p6.getName() + " has " + p6.getNumFriends() + " Friends");
            network.displayFriends(p6);
            System.out.println(p7.getName() + " has " + p7.getNumFriends() + " Friends");
            network.displayFriends(p7);
            System.out.println(p8.getName() + " has " + p8.getNumFriends() + " Friends");
            network.displayFriends(p8);
            System.out.println(p9.getName() + " has " + p9.getNumFriends() + " Friends");
            network.displayFriends(p9);
            System.out.println(p10.getName() + " has " +  p10.getNumFriends() + " Friends");
            network.displayFriends(p10);
            System.out.println(p11.getName() + " has " + p11.getNumFriends() + " Friends");
            network.displayFriends(p11);
            System.out.println(p12.getName() + " has " + p12.getNumFriends() + " Friends");
            network.displayFriends(p12);
            System.out.println(p13.getName() + " has " + p13.getNumFriends() + " Friends");
            network.displayFriends(p13);
            System.out.println(p14.getName() + " has " + p14.getNumFriends() + " Friends");
            network.displayFriends(p14);
            System.out.println(p15.getName() + " has " + p15.getNumFriends() + " Friends");
            network.displayFriends(p15);

            UserProfile p16 = new UserProfile("Bo", "Henson", "boh@example.com", "5551223654");
            UserProfile p17 = new UserProfile("Henry", "Smith", "henrys@example.com", "6661223654");

            // Test contains
            System.out.println("\n\n\nTesting contains():\n");

            if (network.contains(p1) )
                    System.out.println(p1.getName() + " is in network - OK");
            else 
                    System.out.println(p1.getName() + " not in network");

            if ( network.contains(p2) )
                    System.out.println(p2.getName() + " is in network - OK");
            else 
                    System.out.println(p2.getName() + " not in network");

            if ( network.contains(p3) )
                    System.out.println(p3.getName() + " is in network - OK");
            else 
                    System.out.println(p3.getName() + " not in network");

            if (network.contains(p4))
                    System.out.println(p4.getName() + " is in network - OK");
            else 
                    System.out.println(p4.getName() + " not in network");

            if (network.contains(p5))
                    System.out.println(p5.getName() + " is in network - OK");
            else 
                    System.out.println(p5.getName() + " not in network");

            if (network.contains(p6))
                    System.out.println(p6.getName() + " is in network - OK");
            else 
                    System.out.println(p6.getName() + " is not in network");

            if (network.contains(p7))
                    System.out.println(p7.getName() + " is in network - OK");
            else 
                    System.out.println(p7.getName() + " not in network");

            if (network.contains(p8))
                    System.out.println(p8.getName() + " is in network - OK");
            else 
                    System.out.println(p8.getName() + " not in network");

            if (network.contains(p9))
                    System.out.println(p9.getName() + " is in network - OK");
            else 
                    System.out.println(p9.getName() + " not in network");

            if (network.contains(p10))
                    System.out.println(p10.getName() + " is in network - OK");
            else 
                    System.out.println(p10.getName() + " not in network");

            if (network.contains(p11))
                    System.out.println(p11.getName() + " is in network - OK");
            else 
                    System.out.println(p11.getName() + " not in network");

            if (network.contains(p12))
                    System.out.println(p12.getName() + " is in network - OK");
            else 
                    System.out.println(p12.getName() + " not in network");

            if (network.contains(p13))
                    System.out.println(p13.getName() + " is in network - OK");
            else 
                    System.out.println(p13.getName() + " not in network");

            if (network.contains(p14))
                    System.out.println(p14.getName() + " is in network - OK");
            else 
                    System.out.println(p14.getName() + " not in network");

            if (network.contains(p15))
                    System.out.println(p15.getName() + " is in network - OK");
            else 
                    System.out.println(p15.getName() + " not in network");

            if (network.contains(p16))
                    System.out.println(p16.getName() + " is in network - Error");
            else 
                    System.out.println(p16.getName() + " not in network - OK");

            if (network.contains(p17))
                    System.out.println(p17.getName() + " is in network - Error");
            else 
                    System.out.println(p17.getName() + " not in network - OK");


            // Remove first item
            network.deleteVertex(p1);
            network.deleteVertex(p10);
            network.deleteVertex(p3);

            System.out.println("\n" + network.getNumberOfVertices()+ 
                          " (should be 12) remaining items in the graph:\n");
            network.displayVertices();

    } // testSocialNetwork

}  // end Driver


