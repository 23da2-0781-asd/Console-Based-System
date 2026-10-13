import java.util.ArrayList;
import java.util.List;

/** Stores results from all components for the "Display All Results" menu. */
public class Results {
    private static final List<String> LOG = new ArrayList<>();

    public static void add(String entry) { LOG.add(entry); }

    public static void displayAll() {
        System.out.println("\n=============================================");
        System.out.println("ALL RESULTS");
        System.out.println("=============================================");
        if (LOG.isEmpty()) System.out.println("No results recorded yet.");
        else for (int i = 0; i < LOG.size(); i++) System.out.println((i + 1) + ". " + LOG.get(i));
        System.out.println("=============================================");
    }
}
