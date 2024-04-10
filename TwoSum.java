import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSumHashTable(nums, target)));
    }

    public static int[] twoSumHashTable(int[] nums, int target) {
        var hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            var complement = target - nums[i];
            if (hash.containsKey(complement)) {
                return new int[] {complement, nums[i]};
            }
            hash.put(nums[i], i);
        }
        return null;
    }
}
