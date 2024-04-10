public class SlidingWindow {

    public static void main(String[] args) {
        int[] closedTasks = new int[]{3, 6, 5, 4, 2, 1, 4, 7, 3, 2};
        int periodDuration = 3;
        int maxTasks = 0;
        int currentTasks = 0;

        for (int i = 0; i < periodDuration; i++) {
            currentTasks += closedTasks[i];
            maxTasks = currentTasks;
        }

        for (int i = periodDuration; i < closedTasks.length; i++) {
            currentTasks -= closedTasks[i - periodDuration];
            currentTasks += closedTasks[i];
            if (currentTasks > maxTasks) {
                maxTasks = currentTasks;
            }
        }

        System.out.println(maxTasks);
    }
}
