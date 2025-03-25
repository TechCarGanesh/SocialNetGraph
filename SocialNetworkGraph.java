/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproj;

import java.util.Iterator;

public class SocialNetworkGraph implements GraphInterface<UserProfile> {
    private DictionaryInterface<UserProfile, Vertex<UserProfile>> vertices; // Dictionary to store vertices
    private ListWithIteratorInterface<VertexInterface<UserProfile>> edgeList;
    private int edgeCount; // Total edges in the graph

    // Constructor
    public SocialNetworkGraph() {
        vertices = new LinkedDictionary<>(); // Initialize dictionary
        edgeCount = 0;
    }

    @Override
    public boolean addVertex(UserProfile vertexLabel) {
        if (vertices.contains(vertexLabel)) {
            return false; // Vertex already exists
        }
        vertices.add(vertexLabel, new Vertex<>(vertexLabel)); // Add vertex to dictionary
        return true;
    }

    @Override
    public boolean addEdge(UserProfile begin, UserProfile end, double edgeWeight) {
        Vertex<UserProfile> vertex1 = vertices.getValue(begin);
        Vertex<UserProfile> vertex2 = vertices.getValue(end);

        if (vertex1 == null || vertex2 == null) {
            return false; // Invalid vertices
        }

        // Add the edge in both directions for an undirected graph
        if (vertex1.connect(vertex2, edgeWeight)) {
            if (vertex2.connect(vertex1, edgeWeight)) {
                begin.addFriend(end.getEmail());
                end.addFriend(begin.getEmail());
                begin.incNumFriends();
                end.incNumFriends();
                edgeCount++;
                return true;
            }
        }
        return false;
    }
    
    public boolean removeEdge(UserProfile begin, UserProfile end) {
        boolean result = false;
        VertexInterface <UserProfile> beginVertex = vertices.getValue(begin);
        VertexInterface <UserProfile> endVertex = vertices.getValue(end);
        if(beginVertex != null && endVertex != null) {
                result = beginVertex.disconnect(endVertex);
        }
        if (result) {
            begin.decNumFriends();
            end.decNumFriends();
            edgeCount--;
        }
        return result;
    }
    
    public boolean disconnect(VertexInterface<UserProfile> endVertex) {
        boolean result = false;
        if (!this.equals(endVertex))
         { // Vertices are distinct
                Iterator<VertexInterface<UserProfile>> neighbors = endVertex.getNeighborIterator();
                boolean edgeExists = false;
                int index = 1;
                while (!edgeExists && neighbors.hasNext())
                {
                        VertexInterface<UserProfile> nextNeighbor = neighbors.next();
                        if (endVertex.equals(nextNeighbor)) {
                            nextNeighbor.getLabel().removeFriend(endVertex.getLabel().getEmail());
                            endVertex.getLabel().removeFriend(nextNeighbor.getLabel().getEmail());
                                edgeExists = true;
                        } else {
                                ++index;
                        }
                } // end while
                if (edgeExists)
                {
                        edgeList.remove(index);
                        result = true;
                } // end if
        } // end if
        return result;
}

    // Iterator for vertices
    public Iterator<UserProfile> getVertexIterator() {
        return vertices.getKeyIterator();
    }
    

    // Example traversal methods (e.g., BFS, DFS) can also be implemented here

    public double getCheapestPath(UserProfile begin, UserProfile end, StackInterface<UserProfile> path) {
        throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void resetVertices()
    {
        Iterator<UserProfile> vertexIterator = vertices.getKeyIterator();
        while (vertexIterator.hasNext())
        {
            Vertex<UserProfile> nextVertex = vertices.getValue(vertexIterator.next());
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } // end while
    } // end resetVertices
    
    @Override
    public QueueInterface<UserProfile> getBreadthFirstTraversal(UserProfile origin)
    {
        resetVertices();
        QueueInterface<UserProfile> traversalOrder = new LinkedQueue<UserProfile>();
        QueueInterface<VertexInterface<UserProfile>> vertexQueue =
        new LinkedQueue<VertexInterface<UserProfile>>();
        VertexInterface<UserProfile> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // enqueue vertex label
        vertexQueue.enqueue(originVertex); // enqueue vertex
        while (!vertexQueue.isEmpty())
        {
            VertexInterface<UserProfile> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<UserProfile>> neighbors =
            frontVertex.getNeighborIterator();
            while (neighbors.hasNext())
            {
                VertexInterface<UserProfile> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
            } // end while
        } // end while
        return traversalOrder;
    } // end getBreadthFirstTraversal

    @Override
    public QueueInterface<UserProfile> getDepthFirstTraversal(UserProfile origin) {
        QueueInterface<UserProfile> traversalOrder = new LinkedQueue<>();
        StackInterface<Vertex<UserProfile>> vertexStack = new LinkedStack<>();

        Vertex<UserProfile> originVertex = vertices.getValue(origin);
        if (originVertex == null) {
            return traversalOrder; // Origin vertex not found
        }

        // Mark all vertices as unvisited
        resetVertices();

        // Start DFS
        vertexStack.push(originVertex);
        originVertex.visit();

        while (!vertexStack.isEmpty()) {
            Vertex<UserProfile> currentVertex = vertexStack.pop();
            traversalOrder.enqueue(currentVertex.getLabel());

            Iterator<VertexInterface<UserProfile>> neighbors = currentVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                Vertex<UserProfile> neighbor = (Vertex<UserProfile>) neighbors.next();
                if (!neighbor.isVisited()) {
                    vertexStack.push(neighbor);
                    neighbor.visit();
                }
            }
        }

        return traversalOrder;
    }

    @Override
    public int getShortestPath(UserProfile begin, UserProfile end, StackInterface<UserProfile> path) {
        throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StackInterface<UserProfile> getTopologicalOrder() {
        throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public int getNumberOfEdges() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return edgeCount;
    }

    @Override
    public int getNumberOfVertices() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return vertices.getSize();
    }

    @Override
    public boolean isEmpty() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return vertices.isEmpty();
    }

    @Override
    public boolean hasEdge(UserProfile begin, UserProfile end) {
        //throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Vertex<UserProfile> vertex1 = vertices.getValue(begin);
        Vertex<UserProfile> vertex2 = vertices.getValue(end);

        return vertex1 != null;
    }

    @Override
    public boolean addEdge(UserProfile begin, UserProfile end) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return addEdge(begin, end, 0);
    }

    
    public void displayVertexFriends(UserProfile userProfile) {
        Vertex<UserProfile> vertex = vertices.getValue(userProfile);
        if (vertex == null) {
            return; // Return no friends
        }

        Iterator<VertexInterface<UserProfile>> neighborIterator = vertex.getNeighborIterator();
        System.out.println("\nFriends: ");
        while (neighborIterator.hasNext()) {
            VertexInterface<UserProfile> neighbor = neighborIterator.next();
            System.out.print(neighbor.getLabel().getEmail() + ", ");
        }
        System.out.println();
    }
    
    public void displayVertices() {
        Iterator<UserProfile> vertexIterator = vertices.getKeyIterator();
        System.out.println("Vertices in the Social Network:");
        while (vertexIterator.hasNext()) {
            UserProfile userProfile = vertexIterator.next();
            System.out.print(userProfile.getName() + ", " + userProfile.getEmail());
            displayVertexFriends(userProfile);
        }
        System.out.println();
    }
    
        // Function to get the vertex value corresponding to a UserProfile
    public Vertex<UserProfile> getVertexValue(UserProfile userProfile) {
        return vertices.getValue(userProfile);
    }

    // Function to check if a UserProfile is contained in the graph
    public boolean contains(UserProfile userProfile) {
        return vertices.contains(userProfile);
    }
    
    public Vertex<UserProfile> deleteVertex(UserProfile userProfile) {
        return vertices.remove(userProfile);
    }
    
    public LList<String> getFriends(UserProfile userProfile) {
        Vertex<UserProfile> vertex = vertices.getValue(userProfile);
        if (vertex == null) {
            return new LList<>(); // Return empty list if user is not found
        }

        LList<String> friends = new LList<>();
        Iterator<VertexInterface<UserProfile>> neighborIterator = vertex.getNeighborIterator();

        while (neighborIterator.hasNext()) {
            VertexInterface<UserProfile> neighbor = neighborIterator.next();
            friends.add(neighbor.getLabel().getEmail());
        }

        return friends;
    }
    
    public void displayFriends(UserProfile user) {
        Vertex<UserProfile> vertex = vertices.getValue(user);
        if (vertex == null) {
            return; // Return nothing
        }

        Iterator<VertexInterface<UserProfile>> neighborIterator = vertex.getNeighborIterator();

        System.out.println("Friends of : " + user.getName());
        while (neighborIterator.hasNext()) {
            VertexInterface<UserProfile> neighbor = neighborIterator.next();
            System.out.println(neighbor.getLabel().getEmail() + ", " + neighbor.getLabel().getPhone());
        }

    }

}
