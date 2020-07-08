package com.zy.solution.rd;

import java.util.*;

public class MinPathLen {



    public static void main(String args[]){
        ArrayList list=new ArrayList();
        list.add(1);
        list.remove(-1);
//        Scanner in=new Scanner(System.in);
//        while(in.hasNext()){
//            int nodeNum=in.nextInt();
//            int[][] e=new int[nodeNum][nodeNum];
//            //输入数据
//            for (int i=0;i<nodeNum;i++){
//                for (int j=0;j<nodeNum;j++){
//                    e[i][j]=in.nextInt();
//                }
//            }
//            //http://wiki.jikexueyuan.com/project/easy-learn-algorithm/floyd.html
//            floyd(e,nodeNum);
//            //http://wiki.jikexueyuan.com/project/easy-learn-algorithm/dijkstra.html
//            dijkstra(e,nodeNum,2);
//        }
    }

    public static void floyd(int[][] e,int nodeNum){

        for (int k=0;k<nodeNum;k++){  //中间点
            for (int i=0;i<nodeNum;i++){  //i 结点
                for (int j=0;j<nodeNum;j++){ //j 结点
//                    if (e[i][k]==-1 || e[k][j]==-1){
//                        continue;
//                    }
                    if (e[i][j]>e[i][k]+e[k][j]){   // i->k->j
                        e[i][j]=e[i][k]+e[k][j];
                    }
                }
            }
        }
        //输出最终的结果
        for(int i=0;i<nodeNum;i++) {
            for(int j=0;j<nodeNum;j++) {
                System.out.print(e[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void dijkstra(int[][] e,int nodeNum,int index){

        int[] book=new int[nodeNum];  //存储已广度遍历的顶点
        if(index<0 || index>=nodeNum){  //不存在该顶点
            return;
        }
        book[index]=1;    //当前定点

        int[] dis=new int[nodeNum];    //  所求顶点到每个顶点之间的距离
        for (int i=0;i<nodeNum;i++){
            dis[i]=e[index][i];
        }
        int u=0;
        for (int i=0;i<nodeNum;i++){
            int min=Integer.MAX_VALUE;
            for(int j=0;j<nodeNum;j++){
                if (book[j]==0 && dis[j]<min){ //找出当前book[]中未被广度遍历的且target->j距离最短的顶点
                    min=dis[j];
                    u=j;
                }
            }
            book[u]=1;  //该顶点接下来被广度遍历
            for (int v=0;v<nodeNum;v++){
                if (e[u][v]<Integer.MAX_VALUE){   //u->v 是否可到达
                    if (dis[v]>dis[u]+e[u][v]){  //target->v  是否大于  target->u->v
                        dis[v]=dis[u]+e[u][v];
                    }
                }
            }
        }
        for (int i=0;i<nodeNum;i++){
            System.out.print(dis[i]+"\t");
        }
    }
}
