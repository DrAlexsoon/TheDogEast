### 解题思路
处理特殊情况的时候都觉得，我居然要专门为你写一行？

### 代码

```java
class Solution {
    int[] l;
    int x;
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];

        l = new int[matrix.length * matrix[0].length];
        x = 0;
        
        r(matrix,0,matrix.length-1,0,matrix[0].length-1);
        return l;
    }

    void r(int[][] matrix, int rowBegin, int rowEnd, int colBegin, int colEnd){
        int row = rowEnd - rowBegin + 1;
        int col = colEnd - colBegin + 1;
        if(row<=0 || col <=0) return;

        // 下面三条if，处理只剩下一个，一行，一列的情况。。。
        if(row == 1 && col == 1) {
            l[x++] = matrix[rowBegin][colBegin];
            return;
        }
        if(row == 1){
            for(int i = 0; i<col; i++) 
                l[x++] = matrix[rowBegin][colBegin+i];
            return;
        }

        if(col == 1){
            for(int i = 0; i<row; i++){
                l[x++] = matrix[rowBegin+i][colBegin];
            }
            return;
        }

        // 除了上述三种特殊情况，下面四行代码分别爬取四条边
        for(int i = 0; i<col-1; i++)
            l[x++] = matrix[rowBegin][colBegin+i];
        for(int i = 0; i<row-1; i++)
            l[x++] = matrix[rowBegin+i][colEnd];
        for(int i = 0; i<col-1; i++)
            l[x++] = matrix[rowEnd][colEnd-i];
        for(int i = 0; i<row-1; i++)
            l[x++] = matrix[rowEnd-i][colBegin];
        
        //去掉四条边，继续递归
        r(matrix,rowBegin+1,rowEnd-1,colBegin+1,colEnd-1);
    }


}
```