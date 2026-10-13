import java.util.Scanner;

/** Helper class for safe, validated console input. */
public class InputUtil {
    private static final Scanner SC = new Scanner(System.in);

    private static String line(String prompt) {
        System.out.print(prompt);
        if (!SC.hasNextLine()) { System.out.println("\nInput ended. Exiting."); System.exit(0); }
        return SC.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(line(prompt)); }
            catch (NumberFormatException e) { System.out.println("Invalid input. Please enter a whole number."); }
        }
    }

    public static int readChoice(String prompt, int min, int max) {
        while (true) {
            int c = readInt(prompt);
            if (c >= min && c <= max) return c;
            System.out.println("Please choose a number between " + min + " and " + max + ".");
        }
    }

    public static String readWord(String prompt) {
        while (true) {
            String s = line(prompt);
            if (!s.isEmpty() && !s.contains(" ")) return s;
            System.out.println("Please enter a single non-empty word (no spaces).");
        }
    }
}
