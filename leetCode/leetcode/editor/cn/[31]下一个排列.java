//实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须原地修改，只允许使用额外常数空间。 
//
// 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 
//1,2,3 → 1,3,2 
//3,2,1 → 1,2,3 
//1,1,5 → 1,5,1 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <code>1,2,3</code> &rarr; <code>1,3,2</code><br>
 * <code>3,2,1</code> &rarr; <code>1,2,3</code><br>
 * <code>1,1,5</code> &rarr; <code>1,5,1</code></p>
 * []
 * i ,j
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        int j = nums.length - 1;
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }
        if (i >= 0) {
            j = nums.length - 1;
            while (nums[i] >= nums[j] && j >= 0) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);

    }

    void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
