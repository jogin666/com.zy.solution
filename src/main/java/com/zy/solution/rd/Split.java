package com.zy.solution.rd;

public class Split {

    //分治
    //归并排序(和平排序)
    public static void mergeSort(int array[],int L,int R){
        if (array==null){
            throw new RuntimeException("the array is null");
        }
        if (L==R){
            return;
        }
        //将大数组分割成小数组，平分数组
        int m=(L+R)/2;
        //划分左边数组
        mergeSort(array,L,m);
        //划分右边数组,中间下标往前偏移1
        mergeSort(array,m+1,R);
        //合并
        merge(array,L,m+1,R); //m+1 控制辅助数据的长度
    }

    /**
     * @param array
     * @param L  开始下标
     * @param M  划分下标
     * @param R  结束下标
     */
    public static void merge(int array[],int L,int M,int R){
        //开辟左边数据
        int leftArray[]=new int[M-L];
        //开辟右边数组
        int rightArray[]=new int[R-M+1];
        //左边数组赋值
        for (int i=L;i<M;i++){
            leftArray[i-L]=array[i]; //保证数组从下标0开始
        }
        //右边数组赋值
        for (int j=M;j<=R;j++){
            rightArray[j-M]=array[j]; //保证数组从下标0开始
        }
        //数组的下标标识
        int i=0,j=0;
        //传进来的数组array的开始操作数据的下标
        int index=L;
        //比较合并数据
        while(i<leftArray.length && j<rightArray.length){
            if (leftArray[i]<rightArray[j]){
                array[index]=leftArray[i];
                i++;
            }else{
                array[index]=rightArray[j];
                j++;
            }
            index++;
        }
        //左边数组没有加入完
        while (i<leftArray.length){
            array[index++]=leftArray[i++];
        }
        //右边数组没有加入完
        while (j<rightArray.length){
            array[index++]=rightArray[j++];
        }

    }

    public static void main(String args[]){
        int[] arrays = {2, 3, 4, 5, 1, 5, 2, 9, 5, 6, 8, 3, 1};
//        bubbledSort(arrays,arrays.length);
//        int arrays[]={7,6,9,3,5,1,2};
//        mergeSort(arrays,0,arrays.length-1);
//        System.out.println(Arrays.toString(arrays));
    }
}
