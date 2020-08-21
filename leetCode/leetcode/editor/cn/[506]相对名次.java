//给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal",
// "Silver Medal", "Bronze Medal"）。 
//
// (注：分数越高的选手，排名越靠前。) 
//
// 示例 1: 
//
// 
//输入: [5, 4, 3, 2, 1]
//输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
//解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and 
//"Bronze Medal").
//余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。 
//
// 提示: 
//
// 
// N 是一个正整数并且不会超过 10000。 
// 所有运动员的成绩都不相同。 
// 
// 👍 50 👎 0



import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        PriorityQueue<Integer> queue  = new PriorityQueue<>((a,b)->{ return b-a;});
        for (int num : nums) {
            queue.add(num);
        }
        HashMap<Integer, String> dict = new HashMap<>();
        int i = 1;
        while(!queue.isEmpty()){
            switch (i){
                case 1:{
                    dict.put(queue.poll(),"Gold Medal");
                    break;
                }
                case 2:{
                    dict.put(queue.poll(),"Silver Medal");
                    break;
                }
                case 3:{
                    dict.put(queue.poll(),"Bronze Medal");
                    break;
                }
                default:{
                    dict.put(queue.poll(),String.valueOf(i));
                }
            }
            i++;
        }
        LinkedList<String> list = new LinkedList<>();
        for (int num : nums) {
            list.add(dict.get(num));
        }
        String[] strings = new String[list.size()];
        return list.toArray(strings);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
