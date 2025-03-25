/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproj;


import java.util.Iterator;
/**
   A class that implements the ADT undirected graph.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class UndirectedGraph<T> extends DirectedWeightedGraph<T> implements GraphInterface<T>
{
    private DictionaryInterface<T, Vertex<T>> vertices;
    private ListWithIteratorInterface<VertexInterface<T>> edgeList;
    private int edgeCount;

    // Constructor
    public UndirectedGraph()
    {
        super();
        vertices = new LinkedDictionary<T, Vertex<T>>();
        edgeList =  null;
        edgeCount = 0;
    } // end default constructor

    public boolean addEdge(T begin, T end)
    {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ((beginVertex != null) && (endVertex != null)) {
            result = beginVertex.connect(endVertex);
        }
        if (result) {
            edgeCount++;
        }
        return result;
    } // end addEdge

//    public boolean addEdge(T begin, T end)
//    {
//            return this.addEdge(begin, end, 0);
//    } // end addEdge

    public int getNumberOfEdges()
    {
        return edgeCount;
    } // end getNumberOfEdges

    public StackInterface<T> getTopologicalOrder() 
    {
      //throw new UnsupportedOperationException("Topological sort illegal in an undirected graph.");	
                resetVertices();
        StackInterface<T> vertexStack = new LinkedStack<>();
        int numberOfVertices = getNumberOfVertices();
        for (int counter = 1; counter <= numberOfVertices; counter++) {
            VertexInterface<T> nextVertex = findTerminal();
            nextVertex.visit();
            vertexStack.push(nextVertex.getLabel());
        } // end for
        return vertexStack;
    } // end getTopologicalOrder

    @Override
    public void clear() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        vertices.clear();
        edgeCount =0;
    }

    @Override
    public int getNumberOfVertices() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return vertices.getSize();
    }

    @Override
    public boolean isEmpty() {
        //throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return vertices.isEmpty();
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<VertexInterface<T>> neighbors
                    = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    found = true;
                }
            } // end while
        } // end if
        return found;
    }

//    @Override
//    public boolean addVertex(T vertexLabel) {
//        VertexInterface<T> isDuplicate
//                = vertices.add(vertexLabel, new Vertex(vertexLabel));
//        System.out.println("Label " + vertexLabel);
//        return isDuplicate == null; // was add to dictionary successful?
//    }

    public boolean addVertex(T vertexLabel, Vertex vertex) {
        VertexInterface<T> isDuplicate
                = vertices.add(vertexLabel, vertex);
        System.out.println("Label " + vertexLabel);
        return isDuplicate == null; // was add to dictionary successful?
    }
    
    @Override
    public double getCheapestPath(T begin, T end, StackInterface<T> path) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                resetVertices();
        boolean done = false;
// Use EntryPQ instead of Vertex because multiple entries contain
// the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
        PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        priorityQueue.add(new EntryPQ(originVertex, 0, null));
        while (!done && !priorityQueue.isEmpty()) {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();
            if (!frontVertex.isVisited()) {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());
                if (frontVertex.equals(endVertex)) {
                    done = true;
                } else {
                    Iterator<VertexInterface<T>> neighbors
                            = frontVertex.getNeighborIterator();
                    Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
                    while (neighbors.hasNext()) {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        Double weightOfEdgeToNeighbor = edgeWeights.next();
                        if (!nextNeighbor.isVisited()) {
                            double nextCost = weightOfEdgeToNeighbor
                                    + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost,
                                    frontVertex));
                        } // end if
                    } // end while
                } // end if
            } // end if
        } // end while
// Traversal ends, construct cheapest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return pathCost;
    }

    @Override
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();
// Assertion: resetVertices() has executed setCost(0)
// and setPredecessor(null) for originVertex
        vertexQueue.enqueue(originVertex);
        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors
                    = frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
                if (nextNeighbor.equals(endVertex)) {
                    done = true;
                }
            } // end while
        } // end while
// Traversal ends; construct shortest path
        int pathLength = (int) endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return pathLength;
    }

    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        // Assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();
        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // Enqueue vertex label
        vertexStack.push(originVertex); // Enqueue vertex
        while (!vertexStack.isEmpty()) {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();
            if (nextNeighbor != null) {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            } else // All neighbors are visited
            {
                vertexStack.pop();
            }
        } // end while
        return traversalOrder;
    }

    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue
                = new LinkedQueue<VertexInterface<T>>();
        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // enqueue vertex label
        vertexQueue.enqueue(originVertex); // enqueue vertex
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors
                    = frontVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
            } // end while
        } // end while
        return traversalOrder;
    }


    @Override
    public boolean equals(Object obj) {
        // return super.equals(obj);
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return true;
    }

    @Override
    public int hashCode() {
        // return super.hashCode();
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return -1;
    }

    @Override
    public String toString() {
        // return super.toString();
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return null;
    }
    
    public boolean removeEdge(T begin, T end) {
        boolean result = false;
        VertexInterface <T> beginVertex = vertices.getValue(begin);
        VertexInterface <T> endVertex = vertices.getValue(end);
        if(beginVertex != null && endVertex != null) {
                result = beginVertex.disconnect(endVertex);
        }
        if (result) {
                edgeCount--;
        }
        return result;
    }

    @Override
    public void displayEdges() {
        System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
        System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
        Iterator<T> vertexIterator = vertices.getKeyIterator();
        while (vertexIterator.hasNext()) {
            ((Vertex<T>) (vertexIterator.next())).display();
        } // end while
    } // end displayEdges
    
        private class EntryPQ implements Comparable<EntryPQ> {

        private VertexInterface<T> vertex;
        private VertexInterface<T> previousVertex;
        private double cost; // cost to nextVertex

        private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex) {
            this.vertex = vertex;
            this.previousVertex = previousVertex;
            this.cost = cost;
        } // end constructor

        public VertexInterface<T> getVertex() {
            return vertex;
        } // end getVertex

        public VertexInterface<T> getPredecessor() {
            return previousVertex;
        } // end getPredecessor

        public double getCost() {
            return cost;
        } // end getCost

        public int compareTo(EntryPQ otherEntry) {
// Using opposite of reality since our priority queue uses a maxHeap;
// could revise using a minheap
            return (int) Math.signum(otherEntry.cost - cost);
        } // end compareTo

        public String toString() {
            return vertex.toString() + " " + cost;
        } // end toString
    } // end EntryPQ
        
} // end UndirectedGraph

// To make addEdge more efficient, DirectedGraph needs to provide accessors 
// to its data fields. (See Project 3, Chapter 29.)
