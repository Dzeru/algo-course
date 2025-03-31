import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClassHierarchyPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Object> classHierarchy = new HashMap<>();

        String rootPackage = null;

        for (int i = 0; i < n; i++) {
            String[] path = scanner.nextLine().split("/");
            if (rootPackage == null) {
                rootPackage = path[0];
            }

            Map<String, Object> carry = classHierarchy;
            for (int j = 1; j < path.length; j++) {
                carry.putIfAbsent(path[j], new HashMap<>());
                carry = (Map<String, Object>) carry.get(path[j]);
            }
        }

        scanner.close();

        Map<String, Object> root = new HashMap<>();
        root.put(rootPackage, classHierarchy);
        printHierarchy(root, 0);
    }

    private static void printHierarchy(Map<String, Object> hierarchy, int indent) {
        List<String> sortedKeys = new ArrayList<>(hierarchy.keySet());
        Collections.sort(sortedKeys);

        for (String key : sortedKeys) {
            System.out.println("  ".repeat(indent) + key);
            printHierarchy((Map<String, Object>) hierarchy.get(key), indent + 1);
        }
    }
}
