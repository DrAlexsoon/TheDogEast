//写一个程序，输出从 1 到 n 数字的字符串表示。 
//
// 1. 如果 n 是3的倍数，输出“Fizz”； 
//
// 2. 如果 n 是5的倍数，输出“Buzz”； 
//
// 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。 
//
// 示例： 
//
// n = 15,
//
//返回:
//[
//    "1",
//    "2",
//    "Fizz",
//    "4",
//    "Buzz",
//    "Fizz",
//    "7",
//    "8",
//    "Fizz",
//    "Buzz",
//    "11",
//    "Fizz",
//    "13",
//    "14",
//    "FizzBuzz"
//]
// 
//


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> fizzBuzz(int n) {
        LinkedList<String> list = new LinkedList();
        int[] dict = new int[n + 1];
        int pow = 1;
        while (3 * pow <= n || 5 * pow <= n) {
            if (3 * pow <= n && dict[3 * pow] != 3 && dict[3 * pow] != 8) {
                dict[3 * pow] += 3;
            }
            if (5 * pow <= n && dict[5 * pow] != 5 && dict[5 * pow] != 8) {
                dict[5 * pow] += 5;
            }
            pow++;
        }
        for (int i = 1; i <= n; i++) {
            switch (dict[i]) {
                case 0: {
                    list.add(String.valueOf(i));
                    break;
                }
                case 3: {
                    list.add("Fizz");
                    break;
                }
                case 5: {
                    list.add("Buzz");
                    break;
                }
                case 8: {
                    list.add("FizzBuzz");
                    break;
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
