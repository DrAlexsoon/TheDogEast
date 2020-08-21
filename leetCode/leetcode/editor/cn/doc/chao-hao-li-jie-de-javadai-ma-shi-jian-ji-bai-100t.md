时间复杂度为O(n)~O(2n)，空间复杂度为O(1)。
比官方方法O(4n)时间复杂度更快一点。

```
public int findUnsortedSubarray(int[] nums) {
        int left = 0, right = nums.length-1;  //先确定无序子列的左右边界
        while(left < right){
            if (nums[left] <= nums[left+1]){    //确定左边界
                left++;
            }else {
                break;
            }
        }
        while(left < right){
            if (nums[right] >= nums[right-1]){  //确定右边界
                right--;
            }else {
                break;
            }
        }
        if (left == right)return 0;  //如果有序直接返回，存在无序子列的话，确定整体的左右边界。
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            min = Math.min(min, nums[i]);   //获取无序子列的最小值
            max = Math.max(max, nums[i]);   //获取无序子列的最大值
        }
        for (int i = 0; i < left; i++) {    //确定左边界
            if (nums[i] > min){
                left = i;
                break;
            }
        }
        for (int i = nums.length-1; i >= right; i--) {  //确定右边界
            if (nums[i] < max){
                right = i;
                break;
            }
        }
        return (right - left)+ 1;
    }
```

