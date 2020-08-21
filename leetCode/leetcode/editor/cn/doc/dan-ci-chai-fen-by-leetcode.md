#### 方法 1：暴力

**算法**

最简单的实现方法是用递归和回溯。为了找到解，我们可以检查字典单词中每一个单词的可能前缀，如果在字典中出现过，那么去掉这个前缀后剩余部分回归调用。同时，如果某次函数调用中发现整个字符串都已经被拆分且在字典中出现过了，函数就返回 `true` 。

```Java []
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n^n)* 。考虑最坏情况 *s* = ![\text{aaaaaaa} ](./p__text{aaaaaaa}_.png)  。每一个前缀都在字典中，此时回溯树的复杂度会达到 *n^n* 。
* 空间复杂度：*O(n)* 。回溯树的深度最深达到 *n* 。

<br />
#### 方法 2：记忆化回溯

**算法**

在先前的方法中，我们看到许多函数调用都是冗余的，也就是我们会对相同的字符串调用多次回溯函数。为了避免这种情况，我们可以使用记忆化的方法，其中一个 *memo* 数组会被用来保存子问题的结果。每当访问到已经访问过的后缀串，直接用 *memo* 数组中的值返回而不需要继续调用函数。

通过记忆化，许多冗余的子问题可以极大被优化，回溯树得到了剪枝，因此极大减小了时间复杂度。

```Java []
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n^2)* 。回溯树的大小最多达到 *n^2* 。

* 空间复杂度：*O(n)* 。回溯树的深度可以达到 *n* 级别。

<br/>
#### 方法 3：使用宽度优先搜索

**算法**

另一个方法是使用宽度优先搜索。将字符串可视化成一棵树，每一个节点是用 *end* 为结尾的前缀字符串。当两个节点之间的所有节点都对应了字典中一个有效字符串时，两个节点可以被连接。

为了形成这样的一棵树，我们从给定字符串的第一个字符开始（比方说 *s* ），将它作为树的根部，开始找所有可行的以该字符为首字符的可行子串。进一步的，将每一个子字符串的结束字符的下标（比方说 *i*）放在队列的尾部供宽搜后续使用。

每次我们从队列最前面弹出一个元素，并考虑字符串 *s(i+1,end)* 作为原始字符串，并将当前节点作为树的根。这个过程会一直重复，直到队列中没有元素。如果字符串最后的元素可以作为树的一个节点，这意味着初始字符串可以被拆分成多个给定字典中的子字符串。

树的生成过程可以参考这个例子：

  [image.png](https://pic.leetcode-cn.com/b874378afb2d9bc171a396e778cfeb25a6ee1368a374124de3d9990b820e80e6-image.png)  [image.png](https://pic.leetcode-cn.com/3bcb1c59ef2489c034310c76c86b2cfd707beed96f629d8be51e2ae96287d2c5-image.png)  [image.png](https://pic.leetcode-cn.com/cb9b4a35d26b71346da25f9f6cab1926dfb7cb9d3cb8171a750f8d012f081fff-image.png)  [image.png](https://pic.leetcode-cn.com/bd128522a7fc7b3426c1411cbc4c729018b53f814f74eb97fc25003bfe282eb4-image.png)  [image.png](https://pic.leetcode-cn.com/89989e53118ece2da9f0fd79c56b1beff07a6bdabd8ee9c1b0492f93d8eec000-image.png)  [image.png](https://pic.leetcode-cn.com/7e338635849ebb3ee872bcf8ddc14a56ece8bc2d4486cef984980fa8d53b7571-image.png)  [image.png](https://pic.leetcode-cn.com/13748edb1a2cab3f7636a0af409de256aa1048ff976dff44627c118b9f9b5fbb-image.png) 

```Java []
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n^2)* 。对于每个开始的位置，搜索会直到给定字符串的尾部结束。

* 空间复杂度：*O(n)* 。队列的大小最多 *n* 。

<br />
#### 方法 4：使用动态规划

**算法**

这个方法的想法是对于给定的字符串（*s*）可以被拆分成子问题 *s1* 和 *s2* 。如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 *s* 也可以满足。也就是，如果 "![\text{catsanddog} ](./p__text{catsanddog}_.png) " 可以拆分成两个子字符串 "![\text{catsand} ](./p__text{catsand}_.png) " 和 "![\text{dog} ](./p__text{dog}_.png) " 。子问题 "![\text{catsand} ](./p__text{catsand}_.png) " 可以进一步拆分成 "![\text{cats} ](./p__text{cats}_.png) " 和 "![\text{and} ](./p__text{and}_.png) " ，这两个独立的部分都是字典的一部分，所以 "![\text{catsand} ](./p__text{catsand}_.png) " 满足题意条件，再往前， "![\text{catsand} ](./p__text{catsand}_.png) " 和 "![\text{dog} ](./p__text{dog}_.png) " 也分别满足条件，所以整个字符串 "![\text{catsanddog} ](./p__text{catsanddog}_.png) " 也满足条件。

现在，我们考虑 ![\text{dp} ](./p__text{dp}_.png)  数组求解的过程。我们使用 *n+1* 大小数组的 ![\text{dp} ](./p__text{dp}_.png)  ，其中 *n* 是给定字符串的长度。我们也使用 2 个下标指针 *i* 和 *j* ，其中 *i* 是当前字符串从头开始的子字符串（*s'*）的长度， *j* 是当前子字符串（*s'*）的拆分位置，拆分成 *s'(0,j)* 和 *s'(j+1,i)* 。

为了求出 ![\text{dp} ](./p__text{dp}_.png)  数组，我们初始化 ![\text{dp}\[0\] ](./p__text{dp}_0__.png)  为 ![\text{true} ](./p__text{true}_.png)  ，这是因为空字符串总是字典的一部分。 ![\text{dp} ](./p__text{dp}_.png)  数组剩余的元素都初始化为 ![\text{false} ](./p__text{false}_.png)  。

我们用下标 *i* 来考虑所有从当前字符串开始的可能的子字符串。对于每一个子字符串，我们通过下标 *j* 将它拆分成 *s1'* 和 *s2'* （注意 *i* 现在指向 *s2'* 的结尾）。为了将 ![\text{dp}\[i\] ](./p__text{dp}_i__.png)  数组求出来，我们依次检查每个 ![\text{dp}\[j\] ](./p__text{dp}_j__.png)  是否为 ![\text{true} ](./p__text{true}_.png)  ，也就是子字符串 *s1'* 是否满足题目要求。如果满足，我们接下来检查 *s2'* 是否在字典中。如果包含，我们接下来检查 *s2'* 是否在字典中，如果两个字符串都满足要求，我们让 ![\text{dp}\[i\] ](./p__text{dp}_i__.png)  为 ![\text{true} ](./p__text{true}_.png)  ，否则令其为 ![\text{false} ](./p__text{false}_.png)  。

```Java []
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n^2)* 。求出 ![\text{dp} ](./p__text{dp}_.png)  数组需要两重循环。

* 空间复杂度：*O(n)* 。 *dp* 数组的长度是 *n+1* 。