/** Compares searching algorithms and graph traversals (steps + time). */
public class PerformanceAnalyzer {

    public static void run(Graph userGraph) {
        System.out.println("\n=============================================");
        System.out.println("PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.printf("%-16s %-16s %-10s %-10s %-12s%n", "Operation", "Algorithm", "Size(n)", "Steps", "Time(ns)");

        // Worst case search: key is the last element of a sorted array
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = i * 2;
            int key = arr[n - 1];

            long t = System.nanoTime();
            int[] lin = SearchOps.linearSearch(arr, key);
            t = System.nanoTime() - t;
            System.out.printf("%-16s %-16s %-10d %-10d %-12d%n", "Search", "Linear Search", n, lin[1], t);
            Results.add("Performance | Linear Search | n=" + n + " | steps=" + lin[1] + " | " + t + " ns");

            t = System.nanoTime();
            int[] bin = SearchOps.binarySearch(arr, key);
            t = System.nanoTime() - t;
            System.out.printf("%-16s %-16s %-10d %-10d %-12d%n", "Search", "Binary Search", n, bin[1], t);
            Results.add("Performance | Binary Search | n=" + n + " | steps=" + bin[1] + " | " + t + " ns");
        }

        // Graph traversal: use user's graph, or a demo graph if empty
        Graph g = userGraph;
        String note = "your graph";
        if (g.isEmpty()) {
            g = new Graph();
            for (int i = 1; i <= 8; i++) g.addVertex("V" + i);
            String[][] edges = {{"V1","V2"},{"V1","V3"},{"V2","V4"},{"V2","V5"},{"V3","V6"},{"V3","V7"},{"V5","V8"},{"V6","V8"}};
            for (String[] e : edges) g.addEdge(e[0], e[1]);
            note = "demo graph (8 vertices, 8 edges)";
        }
        String start = g.firstVertex();
        long t = System.nanoTime();
        Graph.Traversal b = g.bfs(start);
        t = System.nanoTime() - t;
        System.out.printf("%-16s %-16s %-10s %-10d %-12d%n", "Graph Traversal", "BFS", "-", b.steps, t);
        Results.add("Performance | BFS | " + note + " | steps=" + b.steps + " | " + t + " ns");

        t = System.nanoTime();
        Graph.Traversal d = g.dfs(start);
        t = System.nanoTime() - t;
        System.out.printf("%-16s %-16s %-10s %-10d %-12d%n", "Graph Traversal", "DFS", "-", d.steps, t);
        Results.add("Performance | DFS | " + note + " | steps=" + d.steps + " | " + t + " ns");

        System.out.println("=============================================");
        System.out.println("Graph used: " + note + ", start vertex: " + start);
        System.out.println("BFS order: " + b.order);
        System.out.println("DFS order: " + d.order);
        System.out.println("\nExplanation:");
        System.out.println("- Linear search is O(n): steps grow with n. Binary search is O(log n): steps grow very slowly.");
        System.out.println("- BFS and DFS are both O(V + E) with adjacency lists; they visit the same vertices in a different order.");
        System.out.println("- Times vary between runs (JVM warm-up), so steps are the more reliable comparison.");
    }
}
