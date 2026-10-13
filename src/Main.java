/**
 * CIT300 Graded Practical Assignment 2
 * Data Structure and Graph Performance Analyzer
 */
public class Main {
    public static void main(String[] args) {
        ArrayOps array = new ArrayOps(100);
        StackOps stack = new StackOps(100);
        QueueOps queue = new QueueOps(100);
        LinkedListOperations list = new LinkedListOperations();
        Graph graph = new Graph();

        while (true) {
            System.out.println("\n=============================================");
            System.out.println("DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            switch (InputUtil.readChoice("Enter your choice: ", 1, 9)) {
                case 1 -> array.menu();
                case 2 -> stack.menu();
                case 3 -> queue.menu();
                case 4 -> list.menu();
                case 5 -> SearchOps.menu(array);
                case 6 -> graph.menu();
                case 7 -> PerformanceAnalyzer.run(graph);
                case 8 -> Results.displayAll();
                case 9 -> { System.out.println("Goodbye!"); return; }
            }
        }
    }
}
