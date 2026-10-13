/** Array-based stack (LIFO). */
public class StackOps {
    private final int[] items;
    private int top = -1;

    public StackOps(int capacity) { items = new int[capacity]; }

    public boolean isEmpty() { return top == -1; }

    public boolean push(int v) {
        if (top == items.length - 1) { System.out.println("Stack overflow: stack is full."); return false; }
        items[++top] = v;
        return true;
    }

    public Integer pop() {
        if (isEmpty()) { System.out.println("Stack underflow: cannot pop from an empty stack."); return null; }
        return items[top--];
    }

    public Integer peek() {
        if (isEmpty()) { System.out.println("Stack is empty: nothing to peek."); return null; }
        return items[top];
    }

    public void display() {
        if (isEmpty()) { System.out.println("Stack is empty."); return; }
        System.out.print("Stack (top -> bottom): ");
        for (int i = top; i >= 0; i--) System.out.print(items[i] + (i > 0 ? ", " : ""));
        System.out.println();
    }

    public void menu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push\n2. Pop\n3. Peek\n4. Display\n5. Return to Main Menu");
            switch (InputUtil.readChoice("Enter your choice: ", 1, 5)) {
                case 1 -> { if (push(InputUtil.readInt("Value: "))) System.out.println("Pushed."); }
                case 2 -> { Integer v = pop(); if (v != null) System.out.println("Popped: " + v); }
                case 3 -> { Integer v = peek(); if (v != null) System.out.println("Top: " + v); }
                case 4 -> display();
                case 5 -> { return; }
            }
        }
    }
}
