package com.zy.solution.rd.com.zy.solution.graph;

import java.util.LinkedList;
import java.util.List;

public class Graph<T> {

    private T val;
    private List<Graph<T>> neighborNode;
    private boolean visited;

    public Graph(T val){
        this.val=val;
        neighborNode=new LinkedList<>();
        visited=false;
    }

    @Override
    public boolean equals(Object obj) {
        return this.val.equals(obj);
    }

    public void restoreVisited(){
        restoreVisited(this);
    }

    private void restoreVisited(Graph<T> graphNode){
        if (graphNode.visited){
            graphNode.visited=false;
        }
        List<Graph<T>> neighborNode = getNeighborNode();
        for (Graph<T> graph: neighborNode) {
            restoreVisited(graphNode);
        }
    }

    public List<Graph<T>> getNeighborNode() {
        return neighborNode;
    }

    public T getVal() {
        return val;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
