package weighted;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T> {
    public BreadthFirstSearch(WeightedGraph<T> graph, Vertex<T> point) {
        super(point);
        bfs(graph, point);
    }

    private void bfs(WeightedGraph<T> graph, Vertex<T> curr)
    {
        Queue<Vertex<T>> vertexQueue = new LinkedList<>();
        marked.add(curr);
        vertexQueue.add(curr);

        while (!vertexQueue.isEmpty()) {
            Vertex<T> temp = vertexQueue.remove();

            for (Vertex<T> element : graph.adjacencyList(temp)) {
                if (!marked.contains(element)) {
                    marked.add(element);
                    vertexQueue.add(element);
                    edgeTo.put(element, temp);
                }
            }
        }
    }
}
