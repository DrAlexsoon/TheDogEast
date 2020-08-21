# 题解说明：
1. 由于存在大数问题，结果不能放入int数组中，故这里采用剑指offer原题的直接打印输出的模式。
2. increment函数，若发生进位则一直进行for循环，直到不产生进位则break。如果i为0（即到了最高位）还发生了进位，则设置isOverflow为true，并返回至主函数的while判断。 

# 代码：
```
public class solution {
    public void printNumbers(int n) {
        StringBuilder str = new StringBuilder();
        // 将str初始化为n个'0'字符组成的字符串
        for (int i = 0; i < n; i++) {
            str.append('0');
        }
        while(!increment(str)){
            // 去掉左侧的0
            int index = 0;
            while (index < str.length() && str.charAt(index) == '0'){
                index++;
            }
            System.out.println(str.toString().substring(index));
        }
    }

    public boolean increment(StringBuilder str) {
        boolean isOverflow = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = (char)(str.charAt(i) + 1);
            // 如果s大于'9'则发生进位
            if (s > '9') {
                str.replace(i, i + 1, "0");
                if (i == 0) {
                    isOverflow = true;
                }
            }
            // 没发生进位则跳出for循环
            else {
                str.replace(i, i + 1, String.valueOf(s));
                break;
            }
        }
        return isOverflow;
    }
}
```

