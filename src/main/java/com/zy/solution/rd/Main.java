package com.zy.solution.rd;

import java.util.Map;

public class Main {

    public int solution(int[] numbers){
        int[] dp=new int[numbers.length];
        for (int i=0;i<numbers.length;i++){
            dp[i]=1;
        }
        int res=-1;
        for (int i=0;i<numbers.length;i++){
            res=Math.max(res,solutionHelper(numbers,i,dp));
//            res= Math.max(res,solutionHelper(numbers,i));
        }
        return res;
    }

    public int solutionHelper(int[] numbers,int index,int[]dp){

        if (dp[index]!=1){
            return dp[index];
        }

        for (int i=0;i<index;i++){
            if (numbers[index]>numbers[i]){
                dp[index]=Math.max(dp[index],1+solutionHelper(numbers,i,dp));
            }
        }
        return dp[index];
    }


    public int solutionHelper(int[] numbers,int index){
        int res=1;
        for (int i=0;i<index;i++){
            if (numbers[index]>numbers[i]){
                if (numbers[index]>numbers[i]){
                    res=Math.max(res,1+solutionHelper(numbers,i));
                }
            }
        }
        return res;
    }

    //0 6 7 1 2 3
    public static void main(String args[]){
        Main main=new Main();
        int res = main.solution1(new int[]{0, 6, 7, 8, 1, 2, 3});
        int number = main.minNumberOfCoin(new int[]{1, 2, 5}, 11);
        System.out.println(number);
        System.out.println(res);
    }

    public int solution1(int[] numbers){
        int len=numbers.length;
        int dp[]=new int[len];
        for (int i=0;i<len;i++){
            dp[i]=1;
        }
        int res=-1;
        for (int i=1;i<len;i++){
            for (int j=i-1;j>=0;j--){
                if (numbers[i]>numbers[j] && dp[j]+1>dp[i]){
                    dp[i]=dp[j]+1;
                }
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    public int minNumberOfCoin(int[] coins,int amount){
        if (amount==0)
            return 0;
        int num=Integer.MAX_VALUE;
        for (int coin:coins){

            if (amount-coin<0)
                continue;
            int res=minNumberOfCoin(coins,amount-coin);
            if (res==-1)
                continue;
            num=Math.min(num,res+1);
        }
        return num==Integer.MAX_VALUE ? -1:num;
    }

}
