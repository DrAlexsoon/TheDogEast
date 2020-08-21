### 解题思路
初步思路，使用双指针法，**指针i指向从头开始遍历，指针j从尾开始遍历。分别找到第一个逆序的位置**，之后返回其长度即可。这也满足这道题“简单”的定位。但是写完之后发现事情并不是那么简单。比如下面这个例子`[1,3,2,2,2]`：

 [581_1.png](https://pic.leetcode-cn.com/b974ceaf6699f1cf3b574dbd508a04975039cc0f0b31ed97e674cb1af6c9ecf6-581_1.png)


如果遇到有重复数字的情况下，我们所谓的“**第一个逆序**”也就失效了。

写到这我们就可以想到，能不能换一种思路，**指针i指向从头开始遍历，指针j从尾开始遍历。分别找到最后逆序的位置**，这样也是能确定子数组的长度的。去确定开始的位置比较好说，如何确定最后的位置呢？

这里我们需要多设置两个变量，分别作为指针i与指针j的边界，以指针i举例，需要找到最后一个逆序的位置，必然需要将所有的数遍历完。**在此过程中，如果有比遍历过程中最大的数更小的数，则说明存在逆序，此时更新标志位，一直到遍历完毕**。这样就能记录下其边界。反之对于指针j，采用同样的过程，**如果有比遍历过程中最小的数更大的数，则说明存在逆序，此时更新标志位，一直到遍历完毕**。

过程可能有些绕，可以直接看下面的代码。

### 代码

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int left = 0, right = -1;
        int max = nums[0], min = nums[length-1];
        for(int i = 0; i<length; i++){
            if(nums[i] < max) right = i;
            else max = nums[i];
            if(nums[length - i -1] > min) left = length - i -1;
            else min = nums[length - i -1];
        }
        return right-left+1;
    }
}
```