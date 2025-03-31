import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClassHierarchyPrinter {

    /*
    Вы разрабатываете систему отображения иерархии классов и вложенных структур в программном коде. 
    Каждый класс может содержать вложенные подклассы, которые нужно вывести в удобном формате, сохраняя иерархию. 
    Подклассы должны быть отсортированы в алфавитном порядке, 
    а вложенные элементы должны иметь отступ на два пробела больше, чем их родительский класс.

    Формат входных данных
    Число n — количество строк (1 ≤ n ≤ 100000), 
    где каждая строка представляет путь к классу в формате package/subpackage/ClassName. 
    Первый пакет во всех путях одинаковый и имеет непустое имя. 
    Имена пакетов и классов — строчные латинские буквы ≤ 10 символов.
    
    Формат выходных данных
    Выведите структуру классов, соблюдая иерархию. 
    Все классы внутри одного пакета должны быть отсортированы по алфавиту 
    и иметь отступ на два пробела больше, чем родительский пакет.
    
    Пример входных данных
    5
    com/example/utils/StringUtils
    com/example/utils/MathUtils
    com/example/core/App
    com/example/core/Config
    com/example/core/utils/FileUtils

    Пример выходных данных
    com
      example
        core
          App
          Config
          utils
            FileUtils
        utils
          MathUtils
          StringUtils
     */
    
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
