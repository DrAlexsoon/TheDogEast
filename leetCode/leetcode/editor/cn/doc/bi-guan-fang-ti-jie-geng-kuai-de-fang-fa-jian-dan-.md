# 387.字符串中的第一个唯一字符

<br>

### 1.题目分析

拿到题后我首先注意到 **唯一** 这个关键字，所以最先想到的是`map`和`set`，将每个字符都储存，然后标记只出现一次的字符，最后找到结果，官方题解正好也是这个方法，但是`map` 和`set`牵扯到很复杂的初始化和存储，但本题只需要储存26个小写字母，其实可以用数组代替就可以。

>当储存数据种类较多时，可以使用`map`或者`set`,但种类较少时，使用数组即可，可以极大缩短时间和空间

### 2.思路详解

具体解题思路如下，与官方题解思路类似：

**1. 遍历字符串，使用长度为`26`的数组`count`，统计每个字母出现的次数**
**2. 再次遍历字符串，找到第一个出现次数为1的字符，然后返回索引，如果找不到返回`-1`** 

### 3.代码


```
public int firstUniqChar(String s) {
        int[] count = new int[26];//计数

        //将字符串转化为数组再遍历，也可使用charAt遍历
        //使用c-'a'将字母转化为0~25
        char[] array = s.toCharArray();
        for (char c : array) 
            count[c - 'a']++;
        
        //再次遍历array，只要找到出现次数为1的字符，立即返回索引
        for (int i = 0; i < s.length(); i++) 
            if (count[array[i] - 'a'] == 1)
                return i;

        //如果找不到返回-1
        return -1;
    }
```

 [QQ截图20200610163154.png](https://pic.leetcode-cn.com/4c6009b7372fe9131e67d31b6ea8ea16ef7b3110c108a691133b07dc0b6ef564-QQ%E6%88%AA%E5%9B%BE20200610163154.png)



**官方题解简单改进版**
```Java
public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        /*
        遍历字符串，将每个字符添加到map中
        key是字符，valve是出现次数
        如果字符已添加则更新value
         */
        int n = s.length();
        for (int i = 0; i < n; i++) 
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        //再次遍历字符串，找到第一个出现次数为1的字符，然后返回索引
        for (int i = 0; i < n; i++) 
            if (map.get(s.charAt(i)) == 1)
                return i;

        //如果找不到返回-1
        return -1;
    }
```
 [QQ截图20200610171100.png](https://pic.leetcode-cn.com/333e63066e38c8648f926a071f064dc9d6525a2391c858632fc270f5973440d8-QQ%E6%88%AA%E5%9B%BE20200610171100.png)
<br><br><br>
* **如果题解中出现问题，可以联系我，如果您有自己的见解，欢迎提出**

