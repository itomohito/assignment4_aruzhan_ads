package weighted;

import java.util.*;

public class WeightedGraph<T> {
    private boolean undirected;
    private List<Vertex<T>> vertices = new ArrayList<>();

    WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex<T> destinationFrom, Vertex<T> destinationTo, double weight) {
        if (!hasVertex(destinationFrom)) {
            addVertex(destinationFrom);
        }

        if (!hasVertex(destinationTo)) {
            addVertex(destinationTo);
        }

        if (hasEdge(destinationFrom, destinationTo) || destinationFrom.equals(destinationTo)) {
            return;
        }

        if (undirected) {
            for (Vertex<T> element : vertices) {
                if (element.equals(destinationFrom) && !hasEdge(destinationFrom, destinationTo)) {
                    element.addAdjacentVertex(destinationTo, weight);
                }
                if (element.equals(destinationTo) && !hasEdge(destinationTo, destinationFrom)) {
                    element.addAdjacentVertex(destinationFrom, weight);
                }
            }
        } else {
            for (Vertex<T> element : vertices) {
                if (element.getData().equals(destinationFrom)
                        && !hasEdge(destinationFrom, destinationTo)) {
                    element.addAdjacentVertex(destinationTo, weight);
                }
            }
        }

    }

    public boolean hasVertex(Vertex<T> vertex) {
        for (Vertex<T> element : vertices) {
            if (element.getData().equals(vertex.getData())) {
                return true;
            }
        }

        return false;
    }

    public boolean hasEdge(Vertex<T> destinationFrom, Vertex<T> destinationTo) {
        if (!hasVertex(destinationFrom)) {
            return false;
        }

        for (Vertex<T> element : vertices) {
            if (element.getData().equals(destinationFrom) && element.getAdjacentVertices() != null) {
                for (Vertex<T> elementVertex : element.getAdjacentVertices().keySet()) {
                    if (elementVertex.getData().equals(destinationTo)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<Vertex<T>> adjacencyList(Vertex<T> vertex) {
        if (!hasVertex(vertex)) {
            return null;
        }

        List<Vertex<T>> vertexLinkedList = new LinkedList<>();

        for (Vertex<T> element : this.vertices) {
            if (element.getData().equals(vertex.getData())) {
                vertexLinkedList.addAll(element.getAdjacentVertices().keySet());
                break;
            }
        }

        return vertexLinkedList;
    }

    public Map<Vertex<T>, Double> getEdges(Vertex<T> vertex) {
        if (!hasVertex(vertex)) {
            return null;
        }

        for (Vertex<T> element : vertices) {
            if (element.getData().equals(vertex.getData())) {
                return Optional.of(element).map(Vertex::getAdjacentVertices).orElse(null);
            }
        }
        return null;
    }
}
