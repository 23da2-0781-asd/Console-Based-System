/** Singly linked list of integers. */
public class LinkedListOperations {
    private static class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private int size = 0;

    public void insertAtFront(int v) {           // O(1)
        Node n = new Node(v);
        n.next = head;
        head = n;
        size++;
    }

    public void insertAtEnd(int v) {             // O(n)
        Node n = new Node(v);
        if (head == null) head = n;
        else { Node cur = head; while (cur.next != null) cur = cur.next; cur.next = n; }
        size++;
    }

    public boolean delete(int v) {               // O(n)
        if (head == null) return false;
        if (head.data == v) { head = head.next; size--; return true; }
        Node cur = head;
        while (cur.next != null && cur.next.data != v) cur = cur.next;
        if (cur.next == null) return false;
        cur.next = cur.next.next;
        size--;
        return true;
    }

    public int search(int v) {                   // returns position or -1, O(n)
        Node cur = head; int pos = 0;
        while (cur != null) { if (cur.data == v) return pos; cur = cur.next; pos++; }
        return -1;
    }

    public void display() {
        if (head == null) { System.out.println("Linked list is empty."); return; }
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) sb.append(cur.data).append(cur.next != null ? " -> " : " -> null");
        System.out.println(sb + "  (size " + size + ")");
    }

    public void menu() {
        while (true) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at front\n2. Insert at end\n3. Delete value\n4. Search value\n5. Display\n6. Return to Main Menu");
            switch (InputUtil.readChoice("Enter your choice: ", 1, 6)) {
                case 1 -> { insertAtFront(InputUtil.readInt("Value: ")); System.out.println("Inserted."); }
                case 2 -> { insertAtEnd(InputUtil.readInt("Value: ")); System.out.println("Inserted."); }
                case 3 -> {
                    if (head == null) { System.out.println("Linked list is empty."); break; }
                    System.out.println(delete(InputUtil.readInt("Value to delete: ")) ? "Deleted." : "Value not found.");
                }
                case 4 -> {
                    int p = search(InputUtil.readInt("Value to search: "));
                    System.out.println(p >= 0 ? "Found at position " + p : "Not found.");
                }
                case 5 -> display();
                case 6 -> { return; }
            }
        }
    }
}
