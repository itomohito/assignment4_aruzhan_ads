package weighted;

import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Map<Vertex<T>, Double> distances;
    private final Set<Vertex<T>> unsettledNodes;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, Vertex<T> point) {
        super(point);
        this.graph = graph;
        distances = new HashMap<>();
        unsettledNodes = new HashSet<>();
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            Vertex<T> temp = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(temp);
            unsettledNodes.remove(temp);

            for (Vertex<T> vertex : graph.adjacencyList(temp)) {
                if (getShortestDistance(vertex) > getShortestDistance(temp) + getDistance(temp, vertex)) {
                    distances.put(vertex, getShortestDistance(temp) + getDistance(temp, vertex));
                    edgeTo.put(vertex, temp);
                    unsettledNodes.add(vertex);
                }
            }
        }
    }

    private double getDistance(Vertex<T> node, Vertex<T> target) {
        for (Vertex<T> element : graph.getEdges(node).keySet()) {
            if (element.equals(target)) {
                return graph.getEdges(node).get(element);
            }
        }

        throw new RuntimeException("Not found!");
    }

    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertexSet) {
        Vertex<T> min = null;

        for (Vertex<T> element : vertexSet) {
            if (min == null)
                min = element;
            else {
                if (getShortestDistance(element) < getShortestDistance(min)) {
                    min = element;
                }
            }
        }

        return min;
    }

    private double getShortestDistance(Vertex<T> dest) {
        Double d = distances.get(dest);
        return (d == null ? Double.MAX_VALUE : d);
    }
}
