package title;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jad
 * @Description:
 * @date 2022/4/28 下午3:24
 */
public class TwoSum {

    /*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

    你可以按任意顺序返回答案。*/

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = new TwoSum().twoSum(nums, target);
        Arrays.stream(ints).forEach(System.out::println);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> gapMap = new HashMap();
        int[] resp = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int gap = target - nums[i];
            if (gapMap.get(nums[i]) != null) {
                return new int[]{gapMap.get(nums[i]), i};
            }
            gapMap.put(gap, i);
        }
        return resp;
    }
}
