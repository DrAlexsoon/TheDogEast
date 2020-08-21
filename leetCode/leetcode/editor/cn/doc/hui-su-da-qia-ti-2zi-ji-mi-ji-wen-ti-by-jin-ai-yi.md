### 解题思路
 [QQ截图20200605152747.png](https://pic.leetcode-cn.com/b30b633f24fb9c09d50a86d83f8167d5e14b3701aad23b44ee517430d1aca2d3-QQ%E6%88%AA%E5%9B%BE20200605152747.png)

本题的解空间树是子集树

所谓“子集树”:当所给问题是从n个元素的集合S里找出满足某种性质的子集时，相应的子空间树就是子集树
我的理解就是，子集树的解空间树就是个**二叉树**，每一个节点的子节点只有0-1两种可能，而树深度与输入的规模n密切相关

与“子集树”相对应的还有一种“排列树”，全排列问题构造出来的解空间树就是排列树，排列树每一个节点延伸出的子节点的个数往往与输入规模n密切相关，即是个**多叉树**，而树的深度则是是在dfs到找到一个完整的排列后就终止，除了全排列问题，N后问题也是典型的排列树

遍历子集树需要Ω（2^n)时间复杂度，遍历排列树则需要Ω（n！)时间复杂度

本题的子集树即如下：
 [QQ截图20200607151625.png](https://pic.leetcode-cn.com/6951a60e8bb99f531844824db1d0bef9b6eaa9c215423838c04124c2a9db6069-QQ%E6%88%AA%E5%9B%BE20200607151625.png)

本题也可以写成排列树的形式，比如这位大佬的题解：
[回溯思想团灭排列、组合、子集问题](https://leetcode-cn.com/problems/combinations/solution/hui-su-si-xiang-tuan-mie-pai-lie-zu-he-zi-ji-wen-2/)

不过我感觉把每个分支固定成“选”和“不选”的子集树更容易理解。


### 代码

```java
class Solution {
   ArrayList<List<Integer>> res=new ArrayList<>();
    ArrayList<Integer> one_subset=new ArrayList<>();//存储遍历一个完整路径得到的子集
    int [] nums;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums=nums;
        dfs(0);
        return res;
    }
    private void dfs(int i){//nums的下标
        if(i==nums.length){
            ArrayList<Integer> temp=new ArrayList<>();
            for (int num:one_subset) {
                temp.add(num);
            }
            res.add(temp);
            return;
        }
        for(int flag=0;flag<=1;flag++){//对于nums[i],只有两种可能，选和不选
            if(flag==0){//不选
                dfs(i+1);
                //无需撤销
            }else{//选
                one_subset.add(nums[i]);
                dfs(i+1);
                one_subset.remove(one_subset.size()-1);//需要撤销当前选择
            }
        }
    }
}
```