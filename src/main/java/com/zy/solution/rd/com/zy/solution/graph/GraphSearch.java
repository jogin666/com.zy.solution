package com.zy.solution.rd.com.zy.solution.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphSearch<T> {


    public List<T> searchDFS(Graph<T> graphNode){
        List<T> values=new LinkedList<>();
        searchDFS(graphNode,values);
        return values;
    }

    private void searchDFS(Graph<T> graphNode,List<T> values){
        values.add(graphNode.getVal());
        graphNode.setVisited(true);
        List<Graph<T>> neighborNode = graphNode.getNeighborNode();
        for (Graph<T> graph : neighborNode) {
            if (!graph.isVisited()){
                searchDFS(graphNode,values);
            }
        }
    }

    public List<T> searchBFS(Graph<T> graphNode){
        Queue<Graph<T>> queue = new LinkedList<>();
        queue.add(graphNode);
        List<T> values=new LinkedList<>();
        values.add(graphNode.getVal());
        while(!queue.isEmpty()){
            Graph<T> graph = queue.poll();
            List<Graph<T>> neighborNode = graph.getNeighborNode();
            for (Graph<T> tGraph : neighborNode) {
                if (tGraph.isVisited()){
                    continue;
                }
                tGraph.setVisited(true);
                queue.add(tGraph);
                values.add(tGraph.getVal());
            }
        }
        return values;
    }

}
