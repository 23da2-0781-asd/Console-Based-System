import java.util.Arrays;

/** Fixed-capacity array with insert, delete, search and display. */
public class ArrayOps {
    private final int[] data;
    private int size = 0;

    public ArrayOps(int capacity) { data = new int[capacity]; }

    public boolean insert(int value) {           // O(1)
        if (size == data.length) return false;
        data[size++] = value;
        return true;
    }

    public boolean insertAt(int index, int value) {   // O(n) - shifts elements
        if (size == data.length || index < 0 || index > size) return false;
        for (int i = size; i > index; i--) data[i] = data[i - 1];
        data[index] = value;
        size++;
        return true;
    }

    public boolean delete(int value) {           // O(n)
        int idx = indexOf(value);
        if (idx < 0) return false;
        for (int i = idx; i < size - 1; i++) data[i] = data[i + 1];
        size--;
        return true;
    }

    public int indexOf(int value) {
        for (int i = 0; i < size; i++) if (data[i] == value) return i;
        return -1;
    }

    public void display() {
        if (size == 0) { System.out.println("Array is empty."); return; }
        System.out.println("Array (" + size + "/" + data.length + "): " + Arrays.toString(Arrays.copyOf(data, size)));
    }

    public int[] toArray() { return Arrays.copyOf(data, size); }
    public int getSize() { return size; }

    public void menu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert (at end)\n2. Insert at index\n3. Delete value\n4. Search value\n5. Display\n6. Return to Main Menu");
            switch (InputUtil.readChoice("Enter your choice: ", 1, 6)) {
                case 1 -> System.out.println(insert(InputUtil.readInt("Value: ")) ? "Inserted." : "Array is full.");
                case 2 -> {
                    int idx = InputUtil.readInt("Index (0-" + size + "): ");
                    int v = InputUtil.readInt("Value: ");
                    System.out.println(insertAt(idx, v) ? "Inserted." : "Invalid index or array full.");
                }
                case 3 -> {
                    if (size == 0) { System.out.println("Array is empty."); break; }
                    System.out.println(delete(InputUtil.readInt("Value to delete: ")) ? "Deleted." : "Value not found.");
                }
                case 4 -> {
                    int idx = indexOf(InputUtil.readInt("Value to search: "));
                    System.out.println(idx >= 0 ? "Found at index " + idx : "Not found.");
                }
                case 5 -> display();
                case 6 -> { return; }
            }
        }
    }
}
