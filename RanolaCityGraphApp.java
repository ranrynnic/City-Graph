
import java.util.*;

public class CityGraphApp {
    // Graph structure: adjacency list
    private Map<String, List<Edge>> graph;

    // Constructor
    public CityGraphApp() {
        graph = new HashMap<>();
    }

    // Add a city (vertex)
    public void addCity(String city) {
        graph.putIfAbsent(city, new ArrayList<>());
        System.out.println("City '" + city + "' added to the graph.");
    }

    // Connect cities (add an edge)
    public void connectCities(String city1, String city2, int distance, boolean isDirected) {
        graph.putIfAbsent(city1, new ArrayList<>());
        graph.putIfAbsent(city2, new ArrayList<>());

        // Add edge from city1 to city2
        graph.get(city1).add(new Edge(city2, distance));
        if (!isDirected) {
            // Add edge from city2 to city1 (undirected graph)
            graph.get(city2).add(new Edge(city1, distance));
        }

        System.out.println("Connection added between '" + city1 + "' and '" + city2 + "' with distance " + distance + " km.");
    }

    // Display connections of a specific city
    public void displayConnections(String city) {
        if (!graph.containsKey(city)) {
            System.out.println("City '" + city + "' not found in the graph.");
            return;
        }

        System.out.println("Connections for city '" + city + "':");
        for (Edge edge : graph.get(city)) {
            System.out.println(" -> " + edge.destination + " (" + edge.distance + " km)");
        }
    }

    // Check if a path exists between two cities (DFS)
    public boolean pathExists(String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        return dfs(start, end, visited);
    }

    private boolean dfs(String current, String target, Set<String> visited) {
        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (Edge edge : graph.get(current)) {
            if (!visited.contains(edge.destination)) {
                if (dfs(edge.destination, target, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Display the entire graph
    public void displayGraph() {
        System.out.println("Graph:");
        for (String city : graph.keySet()) {
            System.out.print(city + " -> ");
            for (Edge edge : graph.get(city)) {
                System.out.print(edge.destination + " (" + edge.distance + " km), ");
            }
            System.out.println();
        }
    }

    // Inner class to represent an edge
    private static class Edge {
        String destination;
        int distance;

        public Edge(String destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        CityGraphApp cityGraph = new CityGraphApp();

        // Add cities
        cityGraph.addCity("Add City");
        cityGraph.addCity("Add Road");
        cityGraph.addCity("Display City Connection");


        // Connect cities
        cityGraph.connectCities("Davao", "Cebu", 1200, false);
        cityGraph.connectCities("Cebu", "Davao", 2000, true);
        cityGraph.connectCities("Manila", "Negros", 1500, false);

   }