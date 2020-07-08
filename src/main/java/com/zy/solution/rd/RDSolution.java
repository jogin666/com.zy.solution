package com.zy.solution.rd;

import java.util.ArrayList;
import java.util.Arrays;

public class RDSolution {

    //1.1 递归
    // calculate the sum from 1 to n
    public static int sumR(int n){
        if (n<1){
            return 0;
        } else if (n==1){
            return 1;
        }
        return sumR(n-1)+n;
    }

    //1.2动态规划??
    public static int sumD(int n){
        if (n<1){
            return 0;
        }
        int sum=1;
        for (int i=2;i<=n;i++){
            sum=sum+i;
        }
        return sum;
    }


    //2.1 递归
    /**
     * find max from array
     * the<code>start</code> is the start for array
     * the<code>end</code> is the length for array
     * the<code>array</code> can not null;
     */
    public static int findMaxR(int array[],int start,int end){
        if (array==null){
            throw new RuntimeException("the array is null"+array);
        }
        if(start==end){ //
            return array[start];
        }
        int a=array[start];
        int b=findMaxR(array,start+1,end);
        if(a>b) return a;
        return b;
    }

    //2.2 动态规划??
    public static int findMax(int[] array){
        if (array==null ||  array.length==0){
            return -1;
        }
        int max=array[0];
        for (int i=1;i<array.length;i++){
            if (max<array[i]){
                max=array[i];
            }
        }
        return max;
    }

    //3.1 递归
    /**
     * bubble sort
     * the<code>start</code> is the start for array
     * the<code>end</code> is the length for array
     * the<code>array</code> can not null;
     */
    public static void bubbledSort(int array[],int length){
        if(array==null || length==1){
            return;
        }
        for (int i=0;i<length-1;i++){
            if (array[i]>array[i+1]){
                int temp=array[i];
                array[i]=array[i+1];
                array[i+1]=temp;
            }
        }
        bubbledSort(array,length-1);
    }

    //3.2 动态规划??
    public static void bubble(int[] array){
        if(array==null || array.length==1){
            return;
        }
        for (int i=0;i>array.length;i++){
            for (int j=0;j<array.length-1-i;i++){
                if (array[j]<array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }



    /**
     *  斐波那契数
     */
    //4.1 递归
    public static int fibonacciF(int n){
        if (n<=0){
            throw new RuntimeException("the n is illegal"+n);
        }
        if (n<=2){
            return 1;
        }
        return fibonacciF(n-1)+fibonacciF(n-2);
    }

    //4.2 使用备忘录的递归
    public  static int fibonacciS(int n){
        if (n<=0){
            return -1;
        }
        int[] numbers=new int[n+1];
        numbers[0]=0;numbers[1]=1;numbers[2]=1;
        return helper(n,numbers);
    }

    public static int helper(int n,int[] numbers){
        if (n>2 && numbers[n]==0){  //n-2 已经计算过的数值，把每一个子树的右子树剪枝
            numbers[n]=helper(n-1,numbers)+helper(n-2,numbers);
        }
        return numbers[n];
    }

    //4.3 动态规划
    public static int fibonacciD(int n){
        if (n<1){
            return 0;
        }
        int[] numbers=new int[n+1];
        numbers[1]=numbers[2]=1;
        for (int i=3;i<=n;i++){
            numbers[i]=numbers[i-1]+numbers[i-2];
        }
        return numbers[n];
    }



    //  给出几种金额的硬币和总金额，求用最少的硬币数凑够总金额
    //5.1 递归解法
    public static int coinChangeR(int[] coins,int amount){
        if (amount<=0 || coins==null || coins.length==0){
            return 0;
        }
        int ans=Integer.MAX_VALUE;
        if (amount==0){
            return 0;
        }
        for (int coin:coins){  //每次从数组中选一个当前值，在选其他的剩余值的组合
            if (amount-coin<0)  continue;
            int count=coinChangeR(coins,amount-coin);
            if (count==-1) continue;
            ans=Math.min(ans,count+1);
        }
        return ans==Integer.MAX_VALUE ? -1:ans;

    }

    //5.2 带备忘录的递归
    public static int coinChange(int[] coins,int amount){
        if (amount<=0 || coins==null || coins.length==0){
            return 0;
        }
        int[] count=new int[amount+1];
        for (int i=0;i<count.length;i++){
            count[i]=0;
        }
        return helper(coins,amount,count);
    }

    public static int helper(int[] coins,int amount,int[] count){
        if (amount==0) return 0;
        if (count[amount]!=0)
            return count[amount];  //存储子问题的解
        int ans=Integer.MAX_VALUE;
        for (int coin:coins){
            //金额不够
            if (amount-coin<0) continue;
            int subProb=helper(coins,amount-coin,count);
            //子问题无解
            if (subProb==-1) continue;
            ans=Math.min(ans,subProb+1);
        }
        return count[amount]=(ans==Integer.MAX_VALUE ? -1:ans);
    }

    //5.3 动态规划
    public static int minNumOfCoins(int[] coins,int amount){
        if (coins==null || coins.length==0 || amount<=0){
            return 0;
        }
        int[] count=new int[amount+1];
        for (int i=1;i<amount+1;i++){
            count[i]=amount+1;
        }
        count[0]=0;
        for (int money=0;money<=amount;money++){
            for (int coin:coins){
                if (money-coin>=0){
                    /*
                        money-coin 之前的子问题money'(money-coin)的最优解 +1表示加上硬币值为coin的值，
                        coin+money-coin=money(当前金额)
                     */
                    count[money]=Math.min(count[money],1+count[money-coin]);
                }
            }
        }
        return count[amount]==amount+1? -1: count[amount];
    }


    //6. 最长上升子序列问题
    // 6.1 递归版
    public static int lenR(int[] numbers,int index,int[] lens){

        lens[index]=1;
        for (int i=index-1;i>=0;i--){
            int len=solutionLen(numbers,i,lens);
            if (numbers[index]>numbers[i]){
                lens[index]=Math.max(len+1,lens[index]);
            }
        }
        return lens[index];
    }

    public static int len12(int numbers[]){
        int result=1;
        for (int i=0;i<numbers.length;i++){
            int res=lenss(numbers,i,result,i);
            result=Math.max(res,result);
        }
        return result;
    }

    public static int lenss(int numbers[],int index,int result,int j){
        if (index<numbers.length-1){
             if (numbers[j]<numbers[index+1]){
                 result++;
                 j=index+1;
             }
             lenss(numbers,index+1,result,j);
        }
        return result;
    }

    //6.2 有备忘录的递归版本
    public static int len(int[] numbers){
        if (numbers==null || numbers.length==0){
            return 0;
        }
        int lens[]=new int[numbers.length];
        solutionLen(numbers,numbers.length-1,lens);
        Arrays.sort(lens);
        return lens[lens.length-1];
    }

    public static int solutionLen(int[] numbers, int index, int[] lens){

        if (lens[index]==0){
            lens[index]=1;
            for (int i=index-1;i>=0;i--){
                int len=solutionLen(numbers,i,lens);
                if (numbers[index]>numbers[i]){
                    lens[index]=Math.max(len+1,lens[index]);
                }
            }
        }
        return lens[index];
    }

    //动态规划 6.3.1
    public static int lenD1(int[] numbers){
        if (numbers==null || numbers.length==0){
            return -1;
        }
        int[] len=new int[numbers.length];
        for (int i=1;i<numbers.length;i++){
            len[i]=Integer.MAX_VALUE;
        }
        int lens=1;len[0]=numbers[0];
        for (int i=1;i<numbers.length;i++){
            if (len[lens-1]<numbers[i]){
                len[lens++]=numbers[i];
            }else{
                int s=0,e=lens-1;
                while (s<e){
                    int mid=(s+e)/2;
                    if (len[mid]>numbers[i]) {
                        e=mid-1;
                    } else {
                        s=mid+1;
                    }
                }
                //替换掉，但是lens仍是记录当前最长的上升子序列的长度,直到把lens~1的数值替换完
                len[s]=Math.min(len[s],numbers[i]);
            }
        }
       return lens;
    }

    // 动态规划 6.3.2
    public static int lenD2(int[] numbers){
        if (numbers==null || numbers.length==0){
            return 0;
        }
        int len[]=new int[numbers.length];
        for (int i=0;i<numbers.length;i++){
            len[i]=1;
            for (int j=i-1;j>=0;j--){
                if (numbers[j]<numbers[i]){
                    len[i]=Math.max(len[i],len[j]+1);
                }
            }
        }
        Arrays.sort(len);
        return len[len.length-1];
    }



    //7 字符串匹配  . 匹配任一字符 * 匹配前面的字符0~n次
    //7.1
    public static boolean match(char[] str,char[] pattern){
        if (str==null || pattern==null){
            return  false;
        }
        boolean [][] dp=new boolean[str.length+1][pattern.length+1];
        dp[str.length][pattern.length]=true;  //字符串的回车符肯定是相同的
        for(int i=str.length-1;i>=0;i--){
            for (int j=pattern.length-1;j>=0;j--){
                if (j<pattern.length-1 && pattern[j+1]=='*'){  //若存在下一个 检测是否 *
                    if (i<str.length && (pattern[j]==str[i] || pattern[j]=='.')){
                        dp[i][j]=dp[i][j+2] || dp[i+1][j];
                                //舍弃当前匹配     匹配(1~n)个
                    }else{
                        dp[i][j]=dp[i][j+2];   //不匹配
                    }
                }else if (i!=str.length && ((str[i]==pattern[j] || pattern[j]=='.'))) {   //下一个不为 *
                    dp[i][j]=dp[i+1][j+1];    //两个字符相匹配
                }
            }
        }
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[0][0];
    }


    public static void main(String args[]){
         System.out.println(minNumOfCoins(new int[]{1,2,5},8));
//        System.out.println(len12(new int[]{0,6,7,1,2,3}));
//        System.out.println(lenR(new int[]{0,6,7,1,2,3},5,new int[6]));
//        System.out.println( match(new char[]{'a','a','a','a'},new char[]{'a','a','*'}));
    }

}
