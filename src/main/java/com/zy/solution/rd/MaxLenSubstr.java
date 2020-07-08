package com.zy.solution.rd;

public class MaxLenSubstr {




    public static int findMaxLenSubstr(char[] str1,int i,char[] str2,int j,int[][] dp){
        int result=0;
        if(i>=str1.length || j>=str2.length){
            return 0;
        }
        if (dp[i][j]>0){
            return dp[i][j];
        }
        if (str1[i]==str2[j]){
            result=findMaxLenSubstr(str1,i+1,str2,j+1,dp)+1;  //相同的字符
        }else{
            //不同的字符 匹配下一个(两种情况)  str1/str2
            result=Math.max(findMaxLenSubstr(str1,i+1,str2,j,dp),findMaxLenSubstr(str1,i,str2,j+1,dp));
        }
        dp[i][j]=result;
        return result;
    }


    public static int findMaxLenSubstr(char[] str1,char[] str2){
        if (str1==null || str1.length==0){
            return 0;
        }
        if (str2==null || str2.length==0){
            return 0;
        }
        int[][] dp=new int[str1.length+1][str2.length+1];
        for (int i=1;i<=str1.length;i++){
            for (int j=1;j<=str2.length;j++){
                if (str1[i-1]==str2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[str1.length][str2.length];
    }

    public static void main(String args[]){
        int[][] dp=new int[11][13];
        int len=findMaxLenSubstr(new char[]{'x','y','x','x','z','x','y','z','x','y'},
                new char[]{'z','x','z','y','y','z','x','x','y','x','x','z'});
        findMaxLenSubstr(new char[]{'x','y','x','x','z','x','y','z','x','y'},0,
                new char[]{'z','x','z','y','y','z','x','x','y','x','x','z'},0,dp);
//        for (int i=0;i<dp.length;i++){
//            for (int j=0;j<dp[0].length;j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(len);
        System.out.println(dp[0][0]);

    }
}
