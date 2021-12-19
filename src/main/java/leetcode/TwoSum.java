package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CRR
 */
public class TwoSum {
    public int[] solution1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    /**
     * 循环数组，在哈希表中查找当前数值，如果存在则会哈希表中值和当前索引
     * 否则，将当前值的补数（target-当前值）和当前索引存入哈希表
     * @param nums      整数数组
     * @param target    整数目标值
     * @return 该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
     */
    public int[] solution2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(16);
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(map.containsKey(num)) {
                return new int[] { i, map.get(num)};
            } else {
                map.put(target - num, i);
            }
        }
        return null;
    }
}
