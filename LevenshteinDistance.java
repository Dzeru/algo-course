public class LevenshteinDistance {
    public static void main(String[] args) {
        String test1 = "Hello world!";
        String test2 = "Hellp world.";
        String test3 = "Help word";

        System.out.println(sameString(test1, test2)); // true
        System.out.println(sameString(test1, test3)); // false
    }

    public static int levenshteinDistance(String a, String b) {
        int[][] track = new int[b.length() + 1][a.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            track[0][i] = i;
        }
        for (int j = 0; j < b.length() + 1; j++) {
            track[j][0] = j;
        }
        for (int j = 1; j < b.length() + 1; j++) {
            for (int i = 1; i < a.length() + 1; i++) {
                int indicator = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                track[j][i] = Math.min(track[j][i - 1] + 1, Math.min(track[j - 1][i] + 1, track[j - 1][i - 1] + indicator));
            }
        }
        return track[b.length()][a.length()];
    }


    public static boolean sameString(String fio, String fioDB) {
        int distance = levenshteinDistance(fio, fioDB);
        return distance < 4;
    }



}
