    public boolean isUnique(String astr) {
        //方法1：用map/set存放
        //方法2：indexof  lastindexof
        //方法3：arr[ch - 'a']!=0

        for (char ch: astr.toCharArray()){
            if (astr.indexOf(ch) != astr.lastIndexOf(ch)) {
            	return false;
            }
        }
        return true;
    }