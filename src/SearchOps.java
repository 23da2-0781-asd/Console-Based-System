import java.util.Arrays;

/** Linear and binary search with step counting. */
public class SearchOps {

    /** @return {index (or -1), steps} */
    public static int[] linearSearch(int[] arr, int key) {
        int steps = 0;
        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == key) return new int[]{i, steps};
        }
        return new int[]{-1, steps};
    }

    /** Array must be sorted. @return {index (or -1), steps} */
    public static int[] binarySearch(int[] arr, int key) {
        int lo = 0, hi = arr.length - 1, steps = 0;
        while (lo <= hi) {
            steps++;
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == key) return new int[]{mid, steps};
            if (arr[mid] < key) lo = mid + 1; else hi = mid - 1;
        }
        return new int[]{-1, steps};
    }

    public static void menu(ArrayOps array) {
        while (true) {
            System.out.println("\n------------- SEARCHING OPERATIONS -------------");
            System.out.println("(Searches the array from the Array menu)");
            System.out.println("1. Linear Search\n2. Binary Search\n3. Compare Both\n4. Return to Main Menu");
            int c = InputUtil.readChoice("Enter your choice: ", 1, 4);
            if (c == 4) return;
            int[] arr = array.toArray();
            if (arr.length == 0) { System.out.println("Array is empty. Add values in the Array menu first."); continue; }
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            int key = InputUtil.readInt("Value to search: ");
            if (c == 1 || c == 3) {
                long t = System.nanoTime();
                int[] r = linearSearch(arr, key);
                t = System.nanoTime() - t;
                System.out.println("Linear Search : " + (r[0] >= 0 ? "found at index " + r[0] : "not found") + ", steps=" + r[1] + ", time=" + t + " ns");
                Results.add("Search | Linear Search | key=" + key + " | steps=" + r[1] + " | " + t + " ns");
            }
            if (c == 2 || c == 3) {
                long t = System.nanoTime();
                int[] r = binarySearch(sorted, key);
                t = System.nanoTime() - t;
                System.out.println("Binary Search : " + (r[0] >= 0 ? "found at index " + r[0] + " (sorted copy)" : "not found") + ", steps=" + r[1] + ", time=" + t + " ns");
                Results.add("Search | Binary Search | key=" + key + " | steps=" + r[1] + " | " + t + " ns");
            }
        }
    }
}
