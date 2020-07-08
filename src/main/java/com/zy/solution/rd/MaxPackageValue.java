package com.zy.solution.rd;

import java.util.Scanner;

public class MaxPackageValue {

    public static void main(String args[]){

        Scanner in=new Scanner(System.in);
        try {
            while (in.hasNext()) {
                //道具数量
                int itemNumber = in.nextInt();
                //背包的容量
                int totalWeight = in.nextInt();

                int[] itemWeight = new int[itemNumber];
                //道具的重量
                for (int i = 0; i < itemNumber; i++) {
                    itemWeight[i] = in.nextInt();
                }
                int[] itemValue = new int[itemNumber];
                //道具的价值
                for (int i = 0; i < itemNumber; i++) {
                    itemValue[i] = in.nextInt();
                }
//                maxPackageValue(totalWeight,itemValue,itemWeight);
//                System.out.println(reMaxPackageValue(totalWeight,itemValue,itemWeight,0));
                System.out.println(maxPackageValue(totalWeight,itemValue,itemWeight,0,new int[itemNumber+1][totalWeight+1]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //使用递归
    public static int reMaxPackageValue(int leftWeight,int[] itemValues,int[] itemWeights,
                                        int index) throws Exception {
        int result=0;
        try{
            if (index>=itemValues.length){  //物品个数，已拿完
                return 0;
            }
            if (itemWeights[index]>leftWeight){
                result=reMaxPackageValue(leftWeight,itemValues,itemWeights,index+1);  //物品的数量大于剩余容量，取下一个
            }else{
                //还剩物品，并且当前位置的物品重量小于剩余重量，则就取该物品和不取该物品两种情况进行递归（后面的物品可能价值更大）
                int weight=leftWeight-itemWeights[index];
                result=Math.max(reMaxPackageValue(leftWeight,itemValues,itemWeights,index+1),
                        reMaxPackageValue(weight,itemValues,itemWeights,index+1)+itemValues[index]);
            }
        }catch (Exception e){
            throw new Exception("参数不合法,请检查！");
        }
        return result;
    }

    //有备忘录的递归
    public static int maxPackageValue(int leftWeight,int[] itemValues,int[] itemWeights,
                                      int index,int[][] packageValues) throws Exception {
        int result=0;
        try{
            if (index>=itemValues.length){
                return 0;
            }
            if (packageValues[index][leftWeight]>0){
                return packageValues[index][leftWeight];
            }
            if (itemWeights[index]>leftWeight){
                result=maxPackageValue(leftWeight,itemValues,itemWeights,index+1,packageValues);
            }else{
                int weight=leftWeight-itemWeights[index];
                result=Math.max(maxPackageValue(leftWeight,itemValues,itemWeights,index+1,packageValues),
                        maxPackageValue(weight,itemValues,itemWeights,index+1,packageValues)+itemValues[index]);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("参数不合法，请检查！");
        }
        packageValues[index][leftWeight]=result;
        return result;
    }


    //使用动态规划
    public static int maxPackageValue(int totalWeight,int[] itemValues,int[] itemWeights){
        if (itemValues==null || totalWeight<=0){
            return 0;
        }
        if (itemWeights==null || itemWeights.length==0){
            return 0;
        }
        int[][] packageValue = new int[itemValues.length + 1][totalWeight + 1];
        for (int i=1;i<=itemValues.length;i++){  //道具数量
            for (int j=1;j<=totalWeight;j++){  //容量
                packageValue[i][j]=packageValue[i-1][j];   //相同的容量，肯定能存放相同的价值
                if (itemWeights[i-1]<=j){
                    int ipv=j-itemWeights[i-1];  //  求出剩余容量 可以存放最大的值
                    packageValue[i][j]=Math.max(packageValue[i][j],packageValue[i-1][ipv]+itemValues[i-1]);  //比较
                }
                System.out.print(packageValue[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(packageValue[itemValues.length][totalWeight]);
        return packageValue[itemValues.length][totalWeight];
    }


    /*
       int maxWeight = itemWeight[0];
                for (int n = 1; n < itemNumber; n++) {  //计算道具的最大值
                    if (maxWeight < itemWeight[n]) {
                        maxWeight = itemWeight[n];
                    }
                }

                int[] array = new int[totalWeight + 1];
                for (int j = 1; j <= totalWeight; j++) {  //背包容量
                    int value = array[j];
                    for (int k = 0; k < itemNumber && j <= maxWeight; k++) {
                        if (j - itemWeight[k] >= 0) {
                            if (value < itemValue[k]) {
                                value = itemValue[k];
                            }
                        }
                    }
                    for (int i = 1, l = j - 1; i < l; i++, l--) {
                        if (array[i] + array[l] > value) {
                            value = array[i] + array[l];
                        }
                    }
                    array[j] = value;
                }
                System.out.println(array[totalWeight]);
            }
        }catch (Exception e){

        }
     */
}
