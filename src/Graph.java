import java.util.*;

/** Undirected graph using an adjacency list. */
public class Graph {
    public static class Traversal {
        public final List<String> order = new ArrayList<>();
        public int steps = 0;   // vertices processed + edges examined
    }

    private final Map<String, List<String>> adj = new LinkedHashMap<>();

    public boolean addVertex(String v) {
        if (adj.containsKey(v)) return false;
        adj.put(v, new ArrayList<>());
        return true;
    }

    public boolean addEdge(String a, String b) {
        if (!adj.containsKey(a) || !adj.containsKey(b) || a.equals(b) || adj.get(a).contains(b)) return false;
        adj.get(a).add(b);
        adj.get(b).add(a);
        return true;
    }

    public boolean hasVertex(String v) { return adj.containsKey(v); }
    public boolean isEmpty() { return adj.isEmpty(); }
    public String firstVertex() { return adj.keySet().iterator().next(); }

    public void display() {
        if (adj.isEmpty()) { System.out.println("Graph is empty."); return; }
        for (Map.Entry<String, List<String>> e : adj.entrySet())
            System.out.println(e.getKey() + " -> " + (e.getValue().isEmpty() ? "(no edges)" : e.getValue()));
    }

    public Traversal bfs(String start) {
        Traversal t = new Traversal();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        visited.add(start); q.add(start);
        while (!q.isEmpty()) {
            String v = q.poll();
            t.order.add(v); t.steps++;
            for (String n : adj.get(v)) {
                t.steps++;
                if (visited.add(n)) q.add(n);
            }
        }
        return t;
    }

    public Traversal dfs(String start) {
        Traversal t = new Traversal();
        dfsVisit(start, new HashSet<>(), t);
        return t;
    }

    private void dfsVisit(String v, Set<String> visited, Traversal t) {
        visited.add(v);
        t.order.add(v); t.steps++;
        for (String n : adj.get(v)) {
            t.steps++;
            if (!visited.contains(n)) dfsVisit(n, visited, t);
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex\n2. Add Edge\n3. Display Graph\n4. BFS Traversal\n5. DFS Traversal\n6. Return to Main Menu");
            switch (InputUtil.readChoice("Enter your choice: ", 1, 6)) {
                case 1 -> System.out.println(addVertex(InputUtil.readWord("Vertex name: ")) ? "Vertex added." : "Vertex already exists.");
                case 2 -> {
                    String a = InputUtil.readWord("From vertex: ");
                    String b = InputUtil.readWord("To vertex: ");
                    System.out.println(addEdge(a, b) ? "Edge added." : "Could not add edge (vertex missing, same vertex, or edge exists).");
                }
                case 3 -> display();
                case 4 -> runTraversal(true);
                case 5 -> runTraversal(false);
                case 6 -> { return; }
            }
        }
    }

    private void runTraversal(boolean isBfs) {
        if (adj.isEmpty()) { System.out.println("Graph is empty."); return; }
        String s = InputUtil.readWord("Start vertex: ");
        if (!hasVertex(s)) { System.out.println("Vertex not found."); return; }
        Traversal t = isBfs ? bfs(s) : dfs(s);
        String name = isBfs ? "BFS" : "DFS";
        System.out.println(name + " order: " + t.order + "  (steps=" + t.steps + ")");
        Results.add("Graph Traversal | " + name + " | start=" + s + " | order=" + t.order + " | steps=" + t.steps);
    }
}
