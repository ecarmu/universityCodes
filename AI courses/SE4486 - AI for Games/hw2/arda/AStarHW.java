package chapter3.pathfinding.alg.astar.hw2.arda;

import chapter3.pathfinding.*;
import chapter3.pathfinding.alg.PathfindingAlgorithm;
import chapter3.pathfinding.alg.PathfindingList;
import chapter3.pathfinding.alg.PathfindingNode;
import chapter3.pathfinding.alg.astar.AStarHeuristic;
import chapter3.pathfinding.alg.astar.AStarNode;
import chapter3.pathfinding.alg.astar.ManhattanDistanceHeuristic;
import chapter3.pathfinding.alg.dijkstra.DijkstraNode;
import chapter3.pathfinding.grid.GridGraph;
import chapter3.pathfinding.grid.GridNode;

import java.util.*;

public class AStarHW<T> implements PathfindingAlgorithm<T> {

    PathfindingList<T> openList;
    HashMap<T, AStarNode<T>> closedList;
    AStarHeuristic<T> heuristic;
    private T start;
    private T end;
    private T current;
    private Graph<T> graph;

    private Path<T> path;

    public AStarHW(AStarHeuristic<T> heuristic) {
        openList = new PathfindingList<>();
        closedList = new HashMap<>();
        this.heuristic= heuristic;
    }

    /**
     * TODO: Implement A* algorithm.
     * @param graph
     * @param start
     * @param end
     * @return
     */
    @Override
    public Path<T> findPath(Graph<T> graph, T start, T end) {

        // your code here..
        if ( start.equals(end))
            return new ListPath<>(new ArrayList<>(),0.0);

        init(graph,start,end);

        AStarNode<T> currentANode = (AStarNode<T>) openList.removeMin();
        current = currentANode.getNode();

        while(!current.equals(end)){
            List<Connection<T>> neighbours = graph.connectionsOf(current);

            for (Connection<T> connection : neighbours) {
                T neighbour = connection.to();

                AStarNode<T> newRecord = new AStarNode<T>(neighbour, currentANode.getEstimatedCost() + connection.cost(),heuristic.estimate(graph, neighbour, end),connection);

                if ( openList.contains(neighbour))
                {
                    if ( openList.get(neighbour).getEstimatedCost()> newRecord.getEstimatedCost() )
                        openList.update(newRecord);
                }
                else openList.insert(newRecord);

            }

            closedList.put(current, currentANode);
            currentANode = (AStarNode<T>) openList.removeMin();
            current = currentANode.getNode();
        }

        path = buildPath(currentANode, start, closedList);


        return path==null ? new ListPath<T>(Collections.emptyList(),0):path;
    }






    @Override
    public Path<T> getPath() {
        return path;
    }




    private void init(Graph<T> graph,T start, T end) {

        this.start = start;
        this.end = end;
        this.graph= graph;
        path= null;
        openList.init();
        closedList.clear();
        PathfindingNode<T> dNode = new AStarNode<>(start,0,heuristic.estimate(graph,start,end),null);

        openList.insert(dNode);
    }

    private Path<T> buildPath(AStarNode<T> currentDNode, T start, HashMap<T,AStarNode<T>> closedList) {

        List<Connection<T>> path = new ArrayList<>();
        double weight =0;

        while (!currentDNode.getNode().equals(start))
        {
            path.add(currentDNode.getConnection());
            weight += currentDNode.getConnection().cost();
            currentDNode = closedList.get(currentDNode.getConnection().from());


        }

        Collections.reverse(path);




        return new ListPath<>(path, weight);
    }




}
