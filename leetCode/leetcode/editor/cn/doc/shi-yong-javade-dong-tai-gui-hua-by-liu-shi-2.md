### 解题思路
此处撰写解题思路

### 代码

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        char[] chars01 = s.toCharArray();
        char[] chars02 = t.toCharArray();
        if (chars01.length == 0 ) return true;
        if (chars02.length == 0 ) return false;

        int n = chars01.length;
        int m = chars02.length;
        int temp = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = temp; j < m; j++) {
                if (chars01[i] != chars02[j]) continue;
                for (int k = j; k < m; k++) {
                    if ( i - 1 < 0){
                        dp[0][k] = 1;
                    }
                    else if (dp[i-1][j]==1){
                        dp[i][k] = 1;
                    }
                }
                temp = j + 1;
                break;
            }

        }
        return dp[n-1][m-1] == 1;
    }
}
```