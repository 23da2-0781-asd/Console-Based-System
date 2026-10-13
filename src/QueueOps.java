/** Circular array-based queue (FIFO). */
public class QueueOps {
    private final int[] items;
    private int front = 0, count = 0;

    public QueueOps(int capacity) { items = new int[capacity]; }

    public boolean isEmpty() { return count == 0; }

    public boolean enqueue(int v) {
        if (count == items.length) { System.out.println("Queue overflow: queue is full."); return false; }
        items[(front + count) % items.length] = v;
        count++;
        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) { System.out.println("Queue underflow: cannot dequeue from an empty queue."); return null; }
        int v = items[front];
        front = (front + 1) % items.length;
        count--;
        return v;
    }

    public Integer peek() {
        if (isEmpty()) { System.out.println("Queue is empty: no front element."); return null; }
        return items[front];
    }

    public void display() {
        if (isEmpty()) { System.out.println("Queue is empty."); return; }
        System.out.print("Queue (front -> rear): ");
        for (int i = 0; i < count; i++) System.out.print(items[(front + i) % items.length] + (i < count - 1 ? ", " : ""));
        System.out.println();
    }

    public void menu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue\n2. Dequeue\n3. Peek / Front\n4. Display\n5. Return to Main Menu");
            switch (InputUtil.readChoice("Enter your choice: ", 1, 5)) {
                case 1 -> { if (enqueue(InputUtil.readInt("Value: "))) System.out.println("Enqueued."); }
                case 2 -> { Integer v = dequeue(); if (v != null) System.out.println("Dequeued: " + v); }
                case 3 -> { Integer v = peek(); if (v != null) System.out.println("Front: " + v); }
                case 4 -> display();
                case 5 -> { return; }
            }
        }
    }
}
