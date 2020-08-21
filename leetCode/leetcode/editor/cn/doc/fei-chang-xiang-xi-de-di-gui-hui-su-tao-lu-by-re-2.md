
题目给出的算法结构为
```
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
       
    }
}
```
首先题目要求返回的类型为 `List<List<Integer>>`，那么我们就新建一个 `List<List<Integer>>` 作为全局变量，最后将其返回。
```
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
       
        return lists;
    }
}
```
再看看返回的结构，`List<List<Integer>>`。因此我们需要写一个包含 `List<Integer>` 的辅助函数，加上一些判断条件，此时结构变成了
```
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(candidates, target, list);
        return lists;
    }

    private void process(int[] candidates, int target, List<Integer> list) {
    

    }
}
```
重点就是如何进行递归。递归的第一步，当然是写递归的终止条件啦，没有终止条件的递归会进入死循环。那么有 哪些终止条件呢？由于条件中说了都是正整数。因此，如果 `target<0`,当然是要终止了，如果 `target==0`，说明此时找到了一组数的和为 `target`，将其加进去。此时代码结构变成了这样。
```
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(candidates, target, list);
        return lists;
    }

    private void process(int[] candidates, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
        }
       

    }
}
```
我们是要求组成 `target` 的组合。因此需要一个循环来进行遍历。每遍历一次，将此数加入 `list`，然后进行下一轮递归。代码结构如下。
```
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(candidates, target, list);
        return lists;
    }

    private void process(int[] candidates, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = 0; i < candidates.length; i++) {
                list.add(candidates[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                process( candidates, target - candidates[i], list);
      
            }
        }

    }
}
```
似乎初具规模，测试一把结果如下
 [图片.png](https://pic.leetcode-cn.com/1188201c79fc60517300e5bd3c1a3dfd1a1f542ccf4c7e6fa26f9b7cf451f2a8-%E5%9B%BE%E7%89%87.png){:width=500}
{:align=center}

结果差距有点大，为何会出现如此大的反差。而且发现一个规律，后面的一个组合会包含前面一个组合的所有的数字，而且这些数加起来和 target 也不相等啊。原因出在哪呢？java 中除了几个基本类型，其他的类型可以算作引用传递。这就是导致 list 数字一直变多的原因。因此，在每次递归完成，我们要进行一次回溯。把最新加的那个数删除。此时代码结构变成这样。
```
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(candidates, target, list);
        return lists;
    }

    private void process(int[] candidates, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = 0; i < candidates.length; i++) {
                list.add(candidates[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                process( candidates, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }

    }
}
```
再测一下，结果如下：
 [图片.png](https://pic.leetcode-cn.com/92fe23acff8cfd4442d3781252364f5487f5ecc0af307553d7e7da5055a17b5f-%E5%9B%BE%E7%89%87.png){:width=500}
{:align=center}

还是不对。这次加起来都等于 7 了，和上次结果相比算是一个很大的进步了。分析下测试结果。不难能看出，本次结果的主要问题包含了重复的组合。为什么会有重复的组合呢？因为每次递归我们都是从 0 开始，所有数字都遍历一遍。所以会出现重复的组合。改进一下，只需加一个 start 变量即可。 talk is cheap, show me the code。

代码如下：
```
List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(0, candidates, target, list);
        return lists;
    }

    private void process(int start, int[] candidates, int target, List<Integer> list) {
        //递归的终止条件
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                process(i, candidates, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }

    }

```
最后再测一下。
 [图片.png](https://pic.leetcode-cn.com/91e72e57e89bd79da675b9b7d957bddc6e82012f578866c46ca12de008d363ba-%E5%9B%BE%E7%89%87.png){:width=500}
{:align=center}

代码通过，但是效率并不高。本题有效果更好的动态规划的解法。本文主要展示递归回溯，就不做具体介绍了。