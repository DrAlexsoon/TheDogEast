#### 题解

以下方法一适用于本题的输出格式，但这并不是面试官的本意，方法二与方法三在面试的时候适用，但并不适合本题的输出格式。

##### 方法一、简单打印

读完题目后，首先的想法就是根据给定的n,来计算出应该输出的长度，之后通过+1的方式，将其输出来。

```java
class Solution {
    public int[] printNumbers(int n) {
        //计算输出的长度
        double length = Math.pow(10,n)-1;
        int[] result = new int[(int)length];
        for(int i = 0; i<length; i++)  result[i] = i+1;
        return result;
    }
}
```

##### 方法二、回溯法

题目同样可以这样理解由于要输出从 1 到最大的 n 位十进制数，这相当于给定一组`[0,1,2,3,4,5,6,7,8,9]`整数数组 *nums*，返回该数组所有的子集,子集大小应该是`[1,n]`.这样就转化为了回溯法做这道题。

回溯法的解体框架是什么呢，**解决一个回溯问题，实际上就是一个决策树的遍历过程**。一般来说，我们需要解决三个问题：

1. 路径：也就是已经做出的选择。
2. 选择列表：也就是你当前可以做的选择。
3. 结束条件：也就是到达决策树底层，无法再做选择的条件。

我们所使用的框架基本就是：

```java
LinkedList result = new LinkedList();
public void backtrack(路径，选择列表){
    if(满足结束条件){
        result.add(结果);
    }
    for(选择：选择列表){
        做出选择;
        backtrack(路径，选择列表);
        撤销选择;
    }
}
```

其中最关键的点就是：**在递归之前做选择，在递归之后撤销选择**。对于本题来说：

- 选择列表：`[0,1,2,3,4,5,6,7,8,9]`
- 结束条件：当子集的大小超过n时，就应该结束。

```java
class Solution {
    public List<String> printNumbers(int n){
        List<String> result = new ArrayList<>();
        //找出当前选择列表的所有子集（包括空集[]）
        getsubsets(n,new StringBuilder(),result);
        //删除空集
        result.remove(0);
        return result;
    }
    public void getsubsets(int n, StringBuilder trace, List<String> result){
        if(trace.length() > n) return ;
        result.add(trace.toString());
        //选择列表[0,1,2,3,4,5,6,7,8,9]
        for (int i = 0; i < 10; i++) {
            //剪枝操作：生成子集的时候不能以0为开头，如0,01,02... 012等等
            if(trace.length() == 0 && i == 0) continue;
            //做出选择
            trace.append(i);
            getsubsets(n,trace,result);
            //撤销选择
            trace.deleteCharAt(trace.length()-1);
        }
    }
}
```



##### 方法三、字符相加操作

上述的方法一适用leetcode上的本题，方法二不适合，输出不符合规范。但是如果只是这样的话就太简单，如果面试官给出大数的操作的话，上述方法就不适用了。下面给出字符串相加的操作。

现在回过去看方法一，他的局限在于如果数字过大，超出了上界，那就会溢出。这该如何解决呢？这里就想到了大数相加的操作。大数相加指的是使用字符串或字符数字来模拟数字相加的过程。此过程包括以下几步：

- 对于每一位上的数转化为数字（肯定不会越界，只有一位），进行相加
- 模拟进位操作
- 结果存储

**转化数字并进行相加**：对于问题来说，我们已经知道了数字最大的长度为n，所以我们可以声明一个长度为n的字符串。每一位都初始化为‘0’，数字最大也就是每一位都是‘9’。同样的，我们按照方法方法一的过程，将我们初始化好的字符串加‘1’。这里加‘1’肯定是在最终末尾开始加的，加完之后改变当前字符串即可。

**模拟进位操作**：如果字符串相加的结果是**大于等于**10的，也就是发生了进位操作，那只需将结果取模，使用取模后的结果来改变当前字符串，使用临时变量将进位存储，以便于进行下一次相加。

**结果存储**：只需将每次算出的结果输出或存到容器中即可。

声明下列函`addString(String trace, int point)`做字符串相加操作

- 函数作用：让数字字符串 trace 加一
- 输入：数字字符串，进位的位置
- 输出：最后一次进位的位置

可以先看下列伪代码：

```java
class Solution{
    public int addString(StringBUiler trace, int point){
        for(从字符串的最后一位开始计算){
            if(如果现在是最后一位){
                进行加1操作;
            }
            else{
                进行加进位操作;
            }
            加完之后改变当前字符串;
            if(字符串相加的结果是小于10) {
                说明本次运算结束了;
            }
            else{
                使用临时变量记录进位;
                进位的位置+1;
            }

        }
    }
}
```

将上述过程翻译成代码就是：

```java
public int addString(StringBuilder trace,int point){
    int n = trace.length();
    int temp = 0;
    for(int i = n-1; i>=0; i--){
        //进行+1操作
        if(i == n-1) temp = temp + (trace.charAt(i) - '0') + 1;
        //进行+进位操作
        else temp = temp + (trace.charAt(i) - '0');
        //改变当前字符串
        trace.setCharAt(i,Integer.toString(temp%10).charAt(0));
        //处理进位
        if(temp < 10) break;
        else {
            temp = temp/10;
            point++;
        }
    }
        return point;
}
```

接下来按照方法一的方式打印出数据即可。

#### 代码

```java
class Solution {
    public int addString(StringBuilder trace,int point){
        int n = trace.length();
        int temp = 0;
        for(int i = n-1; i>=0; i--){
            if(i == n-1) temp = temp + (trace.charAt(i) - '0') + 1;
            else temp = temp + (trace.charAt(i) - '0');
            trace.setCharAt(i,Integer.toString(temp%10).charAt(0));
            if(temp < 10) break;
            else {
                temp = temp/10;
                point++;
            }
        }
        return point;
    }

    public static void main(String[] args) {
        Solution demo = new Solution();
        StringBuilder trace = new StringBuilder();
        //数字长度n
        int n = 2;
        //初始化
        for(int i = 0; i<n; i++) trace.append(0);
        //打印的开始位置
        int start = 0;
        while(true){
            //进行加1操作
            int point = demo.addString(trace,0);
            //找到数字字符串最高位不为零的 位置
            for(int i = 0; i<trace.length(); i++){
                if(trace.charAt(i) != '0') {
                    start = i;
                    break;
                }
            }
            //如果最后一次进位的位置超过了数字的最高位，说明已经全部打印完了，结束过程。
            if(point == n) break;
            System.out.print(trace.substring(start,trace.length())+" ");
        }
    }
}
```

