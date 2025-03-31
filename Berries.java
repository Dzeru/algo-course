import java.util.Scanner;

public class Berries {

    /*
    Барсук любит есть ягоды, но не любит длинные обходные пути и высокие деревья. 
    Если не получается пройти, то он лучше ляжет отдохнуть. 
    Карту леса можно представить как таблицу размера n × 3. 
    Барсук начинает свой путь в любой из трех клеток первой строки. 
    После чего может перейти на следующую строку в соседнюю по углу или стороне клетку, если там нет дерева. 
    Если барсук никуда не может пойти из клетки, то его путь заканчивается. 
    Помогите барсуку посчитать, какое максимальное количество ягод он может собрать за прогулку.

    Формат входных данных
    В первой строке задано число n — количество строк в лесу (1≤n≤104). 
    В следующих n строках дано по 3 символа, характеризующих данную строку. 
    Каждый символ равен «.», если в клетке простой участок тропинки, 
    «B», если в этой клетке растут ягоды, и «T», если дерево. 
    Если в первой строке везде деревья, барсук никуда не идет.

    Формат выходных данных
    Одно число — наибольшее количество съеденных за прогулку ягод.

    Пример
    Вход: 2
    T.T
    B..
    Выход: 1

    Пример
    Вход: 2
    TTT
    B..
    Выход: 0
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            grid[i] = scanner.nextLine();
        }

        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            dp[i] = new int[]{0, 0, 0};
        }

        for (int k = 0; k < 3; k++) {
            if (grid[n - 1].charAt(k) == 'B') {
                dp[n - 1][k] = 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int k = 0; k < 3; k++) {
                if (grid[i].charAt(k) == 'T') {
                     continue;
                }

                for (int h = k - 1; h <= k + 1; h++) {
                    if (h >= 0 && h < 3 && grid[i + 1].charAt(h) != 'T') {
                        int hasBerry = 0;
                        if (grid[i].charAt(k) == 'B') {
                            hasBerry = 1;
                        }

                        dp[i][k] = Math.max(dp[i][k], dp[i + 1][h] + hasBerry);
                    }
                }
            }
        }

        System.out.println(Math.max(dp[0][0], Math.max(dp[0][1], dp[0][2])));
    }
}
