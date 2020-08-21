#### 方法一：排序

由于相同的任务之间必须有 `n` 的冷却时间，所以我们可以想到按照任务的数量来安排它们，即一种任务的出现次数越多，我们就越早地安排。例如有 `5` 种任务 `A, B, C, D, E`，且它们分别有 `6, 1, 1, 1, 1` 个时，假设冷却时间 `n = 2`，那么我们首先安排任务 `A`，随后在 `2` 单位的冷却时间里，我们安排任务 `B, C`，随后继续安排任务 `A`，再安排任务 `D, E`，以此类推。

因此我们得到了一种安排的方法：我们规定 `n + 1` 个任务为一轮，这样的好处是同一轮中一个任务最多只能被安排一次。在每一轮中，我们将当前的任务按照它们剩余的次数降序排序，并选择剩余次数最多的 `n + 1` 个任务依次执行。如果任务的种类 `t` 少于 `n + 1` 个，就只选择全部的 `t` 种任务，其余的时间空闲。这样做的正确性在于，由于冷却时间的存在，出现次数较多的那些任务如果不尽早安排，将会导致大量空闲时间的出现，因此贪心地将出现次数较多的任务安排在前面是合理的。同时我们可以保证，这一轮的第 `k` 个任务距离上一次执行至少有 `n` 个单位的冷却时间。我们可以使用逆向思维来证明：假设第 `r` 轮中某个任务在第 `k` 个执行，那么说明它在第 `r` 轮时为数量第 `k` 多的任务。在第 `r` 轮结束后，第 `1` 多到第 `k` 多的任务的数量都会减少 `1`，因此在第 `r + 1` 轮，这个任务最多也只能是数量第 `k` 多，因此它如果被执行，一定满足冷却时间的要求。

根据上面的安排方法，我们每一轮选择不超过 `n + 1` 个任务执行，直到所有的任务被执行。

```Java [sol1]
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }
}
```

**复杂度分析**

* 时间复杂度：![O(\text{time}) ](./p__O_text{time}__.png) ，由于我们给每个任务都安排了时间，因此时间复杂度和最终的答案成正比。

* 空间复杂度：*O(1)*。

#### 方法二：优先队列

在选择每一轮中的任务时，我们也可以用优先队列（堆）来代替排序。在一开始，我们把所有的任务加入到优先队列中。在每一轮，我们从优先队列中选择最多 `n + 1` 个任务，把它们的数量减去 `1`，再放回堆中（如果数量不为 `0`），直到堆为空。

```Java [sol2]
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue < Integer > queue = new PriorityQueue < > (26, Collections.reverseOrder());
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List < Integer > temp = new ArrayList < > ();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l: temp)
                queue.add(l);
        }
        return time;
    }
}
```

**复杂度分析**

* 时间复杂度：![O(\text{time}) ](./p__O_text{time}__.png) ，由于我们给每个任务都安排了时间，因此时间复杂度和最终的答案成正比。

* 空间复杂度：*O(1)*。

#### 方法三：设计

在前两种方法中，我们了解到应当尽早安排出现次数较多的任务。我们假设 `A` 为出现次数最多的任务，假设其出现了 `p` 次，考虑到冷却时间，那么执行完所有任务的时间至少为 `(p - 1) * (n + 1) + 1`。我们把这个过程形象化地用图 `1` 表现出，可以发现，CPU 产生了 `(p - 1) * n` 个空闲时间，只有 `p` 个时间是在工作的。

 [Tasks](https://pic.leetcode-cn.com/Figures/621_Task_Scheduler_new.PNG)
{:align = "center"}

因此我们应当考虑把剩余的任务安排到这些空闲时间里，我们仍然按照这些任务的出现次序，从大到小进行安排，会有下面三种情况：

- 某个任务和 `A` 出现的次数相同，例如图 `2` 中的任务 `B`。此时我们只能让 `B` 占据 `p - 1` 个空闲时间，而在非空闲时间里额外安排一个时间给 `B` 执行；

- 某个任务比 `A` 出现的次数少 `1`，例如图 `2` 中的任务 `C`。此时我们可以让 `C` 占据 `p - 1` 个空闲时间，就可以全部执行完；

- 某个任务比 `A` 出现的次数少 `2` 或更多，例如图 `2` 中的任务 `D`。此时我们可以按照列优先的顺序，将 `D` 填入空闲时间中。因为 `D` 出现的次数少于 `p - 1`，因此无论从哪个位置开始按照列优先的顺序放置，都可以保证相邻的两个 `D` 之间满足冷却时间的要求。

在将所有的任务安排完成后，如果仍然有剩余的空闲时间，那么答案即为（任务的总数 + 剩余的空闲时间）；如果在安排某一个任务时，遇到了剩余的空闲时间不够的情况，那么答案一定就等于任务的总数。这是因为我们可以将空闲时间增加虚拟的一列，继续安排任务。如果不考虑这些虚拟的列，在原本空闲时间对应的那些列中的任务是可以按照要求顺序执行的，而增加了这些虚拟的列后，两个相邻的相同任务的间隔不可能减少，并且虚拟的列中的任务也满足冷却时间的要求，因此仍然顺序执行并跳过虚拟的列中剩余的“空闲时间”一定是可行的。

```Java [sol3]
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(M)*，其中 *M* 是任务的总数。

* 空间复杂度：*O(1)*。