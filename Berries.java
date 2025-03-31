import java.util.Scanner;

public class Berries {

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
