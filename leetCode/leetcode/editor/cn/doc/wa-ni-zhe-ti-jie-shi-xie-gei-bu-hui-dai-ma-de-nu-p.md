> 题解视频可能发在我的b站：[天使爆破组](https://space.bilibili.com/12996839)

### 先给出代码
```JS []
var maxProduct = (nums) => {
  let res = nums[0]
  let prevMin = nums[0]
  let prevMax = nums[0]
  let temp1 = 0, temp2 = 0
  for (let i = 1; i < nums.length; i++) {
    temp1 = prevMin * nums[i]
    temp2 = prevMax * nums[i]
    prevMin = Math.min(temp1, temp2, nums[i])
    prevMax = Math.max(temp1, temp2, nums[i])
    res = Math.max(prevMax, res)
  }
  return res
}
```
```Java []
class Solution {
  public int maxProduct(int[] nums) {
    int prevMin = nums[0], prevMax = nums[0], res = nums[0];
    int temp1 = 0, temp2 = 0;
    for (int i = 1; i < nums.length; i++) {
      temp1 = nums[i] * prevMax;
      temp2 = nums[i] * prevMin;
      prevMax = Math.max(Math.max(temp1, temp2), nums[i]);
      prevMin = Math.min(Math.min(temp1, temp2), nums[i]);
      res = Math.max(res, prevMax);
    }
    return res;
  }
}
```
```C++ []
class Solution {
public:
  int maxProduct(vector<int>& nums) {
  int len = nums.size(), res = nums[0];
    int prevMin = nums[0], prevMax = nums[0];
    int temp1 = 0, temp2 = 0;
    for (int i = 1; i < len; i++) {
      temp1 = prevMin * nums[i];
      temp2 = prevMax * nums[i];
      prevMin = min(min(temp1, temp2), nums[i]);
      prevMax = max(max(temp1, temp2), nums[i]);
      res = max(prevMax, res);
    }
    return res;
  }
};
```
---

### 思考子数组的形态
- 索引 i 和 j 确定出一个子数组 
- 题目 => 【某对 i, j 组合形成的子数组乘积最大，告诉我有多大】
- i 和 j 是变化的，形成一个不定形态的子数组
### 固定一端，另一端变化
- 我们可以暂时固定其中一端：**j** 不动，**i** 移动
- 我们考察【 **i 移动，j 固定，形成的子数组们**】中的最大乘积
- 即考察：以 **j** 项为末尾项的子数组们中的最大乘积
- 找到对 **j** 而言的最优解后
- 我们再移动 **j** ，考察 **不同的 j 的最优解**，再 **优中取优**
 
### j 项期待前面的最好结果
- 以 j 为末尾项的动态子数组为了能有最大乘积
- 我们希望 **动态子数组中 j 前面的项的乘积尽量大**（后面再说这里的错误）
- 它乘上 j 项，产生最大乘积
- 这个所谓 j 前面的项，其实就是【 **i 移动，j - 1 固定的动态子数组**】

### 记录局部最优解
- 可见，计算 j 的最优解，**依赖 j - 1 的最优解**
- 意味着，我们要记录【**曾经的最优解**】，用什么去存放【**曾经的最优解**】呢？
- 我们可以选择**哈希表** 或 **数组**
### 用数组作备忘录
- **数组 dp 来存放不同的 j 的最优解**，j 和 nums 数组的索引同步
- 我们可以写出**通式**：
   *dp[ j ] = dp[ j - 1 ] * nums[ j ]*
-  *dp[0] = nums[0]*，因为 *j = 0* 时，*i* 只能为 0，子数组只有第 0 项
- 遍历 *j*，**逐个求出 dp 的每一项**，看看哪一项最大
- **局局部最优** 产生 **局部最优** ，比较 **局部最优** ，**优中取优**
### 发现错误：忽略了负负得正
- 我们把 *dp[ j - 1 ]* 直接拿过来相乘，结果肯定是最好的吗？
- 我们忽略了 ***j* 项可以是负数**，**如果是负数**，则 *dp[ j - 1 ]* 是最大积的话，得到 *dp[ j ]* 会是最小的。此时 *j - 1* 的最优解应当是 **负数的最大**，即 **最小乘积**
- 如果 ***j* 项是正数**，把之前的 **最大积** 直接拿来乘，没问题
- 即，**对 j 来说**，***j - 1* 的最优解** 可能是 **最大乘积** ，也可能是 **最小乘积**
- 因此，*dp[i]* 要放两项了，一项是局部最大乘积，一项是局部最小乘积

### 修改 dp 数组
  - *dp[i][0]*
      - 从第 0 项到第 i 项范围内的子数组的最小乘积
  - *dp[i][1]*
      - 从第 0 项到第 i 项范围内的子数组的最大乘积
- base case：
    - *dp[0][0] = nums[0]*
    - *dp[0][1] = nums[0]*
- 对于以 *i* 项为末尾项的子数组能产生的最小积，它有 3 种情况：
    - 不和别人乘，就它自己
    - 自己是负数，希望乘上前面的最大积
    - 自己是正数，希望乘上前面的最小积
- 所以， *dp[i][0]* 值取三种情况中的最小值
    *dp[i][0] = min( dp[i - 1][0] * nums[i],dp[i - 1][1] * nums[i], nums[i])*
- 类似的，*dp[i][1]* 值取三种情况中的最大值
    *dp[i][1] = max( dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i])*

### 进行优化 压缩空间
- 观察发现， *dp[i][x]* 只和 *dp[i - 1][x]* 有关，与再之前的结果无关
- 我们用两个变量分别去存储每个位置算出的最小积和最大积
- base case
    - *prevMin = nums[0]*
    - *prevMax = nums[0]*
- 状态转移方程:
    *prevMin = min( prevMin * nums[i], prevMax * nums[i], nums[i])*
    *prevMax = max( prevMin * nums[i], prevMax * nums[i], nums[i])*
### 这么写有问题
- 等号右边的 prevMin 和 prevMax 属于 *dp[i - 1]* 的
- 等号左边的 prevMin 和 prevMax 属于 *dp[i]* 的
- **错误**：第一个等式求出的新 prevMin 用在第二个等式的计算
- **解决**：用变量暂存 *prevMin * nums[i]* 和 *prevMax * nums[i]*

#### 时间复杂度 O(n) 空间复杂度O(n)
```javascript []
var maxProduct = (nums) => {
  let res = nums[0]
  let prevMin = nums[0]
  let prevMax = nums[0]
  let temp1 = 0, temp2 = 0
  for (let i = 1; i < nums.length; i++) {
    temp1 = prevMin * nums[i]
    temp2 = prevMax * nums[i]
    prevMin = Math.min(temp1, temp2, nums[i])
    prevMax = Math.max(temp1, temp2, nums[i])
    res = Math.max(prevMax, res)
  }
  return res
}
```
```java []
class Solution {
  public int maxProduct(int[] nums) {
    int prevMin = nums[0], prevMax = nums[0], res = nums[0];
    int temp1 = 0, temp2 = 0;
    for (int i = 1; i < nums.length; i++) {
      temp1 = nums[i] * prevMax;
      temp2 = nums[i] * prevMin;
      prevMax = Math.max(Math.max(temp1, temp2), nums[i]);
      prevMin = Math.min(Math.min(temp1, temp2), nums[i]);
      res = Math.max(res, prevMax);
    }
    return res;
  }
}
```
```c++ []
class Solution {
public:
  int maxProduct(vector<int>& nums) {
  int len = nums.size(), res = nums[0];
    int prevMin = nums[0], prevMax = nums[0];
    int temp1 = 0, temp2 = 0;
    for (int i = 1; i < len; i++) {
      temp1 = prevMin * nums[i];
      temp2 = prevMax * nums[i];
      prevMin = min(min(temp1, temp2), nums[i]);
      prevMax = max(max(temp1, temp2), nums[i]);
      res = max(prevMax, res);
    }
    return res;
  }
};
```

### 总结
- 动态规划问题可以经历这样的优化：
  1. **暴力枚举法**
  2. **申请空间**，引入**hashMap**，对计算过的状态进行记录，以便下次需要该状态时，直接从内存中使用它，有点像备忘录
  3. **动态规划**，也是 **申请空间（dp 数组）** 来记录每一个计算结果，依次进行计算，后面的计算严格依赖前面的计算过程

- 本质是**用空间换取时间**，有的问题可以进行 **空间压缩**
- 通过把大问题 **分解成子问题**，并 **存储子问题的结果**，避免再次计算相同的结果
- 关于【**无后效性**】
  - 某个阶段的状态一旦确定了，那么此后的过程不再受之前曾经的状态和决策的影响
  - 不管你过去经历过什么状态，做过什么决策，【**未来和过去无关**】
  - 当前的状态是此前历史的一个综合给出的结果，**历史影响你也只是造就了你当前的状态**，**通过当前状态去影响你未来的进程**
