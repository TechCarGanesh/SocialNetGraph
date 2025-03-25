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

		//printQueue(bfs);
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
            // SocialNetworkGraph network = new SocialNetworkGraph();
                
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
                System.out.println("\n\nTesting create profiles:\n");
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
                System.out.println("\n\nTesting add graph vertices:\n");
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
                System.out.println("\n There should be 10 vertices:" + "Actual: " +
                        network.getNumberOfVertices());

                System.out.println("\n\n");
                network.displayVertices();
                
                System.out.println("\n\n");
                
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
                System.out.println("John Doe (6 Friends): ");
                network.displayFriends(p1);
                System.out.println("Jane Smith (3 Friends): ");
                network.displayFriends(p2);
                System.out.println("Thomas Jefferson (4 Friends): ");
                network.displayFriends(p3);
                System.out.println("Dirk Mayer (5 Friends): ");
                network.displayFriends(p4);
                System.out.println("Matthew Johnson (4 Friends): ");
                network.displayFriends(p5);
                System.out.println("Bob Barker (2 Friends): ");
                network.displayFriends(p6);
                System.out.println("Charlie Jones (3 Friends): ");
                network.displayFriends(p7);
                System.out.println("Jim Johnson (3 Friends): ");
                network.displayFriends(p8);
                System.out.println("Mary Hart (2 Friends): ");
                network.displayFriends(p9);
                System.out.println("Martin Hunter (4 Friends): ");
                network.displayFriends(p10);
                        
                System.out.println("\nTesting BFS from node John");
                testBFS(p1);
                System.out.println("John Doe Jane Smith Thomas Jefferson Dirk Mayer"
                        + "Matthew Jhonson Bob Barker Jim Jhonson Charlie Jones Martin Hunter Mary Hart");
                System.out.println("Testing DFS from Node John");
                testDFS(p1);
                System.out.println("John Doe Jim Jhonson Martin Hunter Charlie Jones Mary Hart Bob Barker "
                        + "Matthew Jhonson Dirk Mayer Thomas Jefferson Jane Smith");
                
                System.out.println("\n Testing Remove: ");
                network.removeEdge(p1, p2);
                network.removeEdge(p10, p8);
                network.removeEdge(p6, p4);
                
                System.out.println(p1.getName() + " has 5 Friends");
                network.displayFriends(p1);
                System.out.println(p2.getName() + " has 3 Friends");
                network.displayFriends(p2);
                System.out.println(p4.getName() + " has 4 Friends");
                network.displayFriends(p4);
                System.out.println(p6.getName() + " has 1 Friends");
                network.displayFriends(p6);

                // Test modify profile
                System.out.println("\n\nTesting modifyProfile:\n");
                p1.modifyProfile("John", "Doe", "john@example.com", "1112223333");
                
                // Test add
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
                UserProfile p16 = new UserProfile("Bo", "Henson", "boh@example.com", "5551223654");
                UserProfile p17 = new UserProfile("Henry", "Smith", "henrys@example.com", "6661223654");
                
                System.out.println(network.getNumberOfVertices() + " (should be 11) vertices in graph, as follows:\n");
                network.displayVertices();
                // display(emailList);
                
                System.out.println("\n\nTesting getValue():\n");
                System.out.println("\n John:   " + network.getVertexValue(p1)   + " should be 1112223333");
                System.out.println("\n Jane:    " + network.getVertexValue(p2)    + " should be 0987654321");
                System.out.println("\n Thomas:    " + network.getVertexValue(p3)+ " should be 135790246");
                System.out.println("\n Dirk:   " + network.getVertexValue(p4)   + " should be 5556781212");
                System.out.println("\n Matthew:    " + network.getVertexValue(p5)    + " should be 8007001234");
                System.out.println("\n Bob:    " + network.getVertexValue(p6)    + " should be 717123555");
                System.out.println("\n Charlie:   " + network.getVertexValue(p7)   + " should be 6157771223");
                System.out.println("\n Jim:    " + network.getVertexValue(p8)    + " should be 9809991675");
                System.out.println("\n Mary:    " + network.getVertexValue(p9)    + " should be 9199268877");
                


                // Test contains
		System.out.println("\n\n\nTesting contains():\n");

		if (network.contains(p1) )
			System.out.println("\nJohn is in network - OK");
		else 
			System.out.println("Error in contains()");
		
		if ( network.contains(p2) )
			System.out.println("\nJane is in network - OK");
		else 
			System.out.println("Error in contains()");
		
		if ( network.contains(p3) )
			System.out.println("\nTom is in network - OK");
		else 
			System.out.println("Error in contains()");
		
		if (!network.contains(p16))
			System.out.println("\nBo is not in network - OK");
		else 
			System.out.println("Error in contains()");
                
		if (!network.contains(p17))
			System.out.println("\nHenry is not in network - OK");
		else 
			System.out.println("Error in contains()");
                
		if (!network.contains(p7))
			System.out.println("\nMatthew is in network - OK");
		else 
			System.out.println("Error in contains()");

                if (!network.contains(p8))
			System.out.println("\nThomas is in network - OK");
		else 
			System.out.println("Error in contains()");

                // Remove first item
		System.out.println("\n\n\nRemoving first item John - John's number is " +
		                   network.deleteVertex(p1) + " should be 0987654321");
		System.out.println("\n\n\nRemoving second item Jane - Jane number is " +
		                   network.deleteVertex(p2) + " should be 0987654321");
		System.out.println("\n\n\nRemoving third item Tom - Tom's number is " +
		                   network.deleteVertex(p3) + " should be 135790246");

		System.out.println("\n" + network.getNumberOfVertices()+ 
		              " (should be 7) items in dictionary, as follows:\n");
		network.displayVertices();
		

	} // testDictionary


}  // end Driver


