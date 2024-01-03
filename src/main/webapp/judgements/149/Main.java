import java.util.*;
public class Main {
    public static void main(String args[]){
        int l,n;
    Scanner sc = new Scanner(System.in);
    l = sc.nextInt();//马路长度
    n = sc.nextInt();//区域数目
    int[] ar = new int[l+1];//这里数组长度要加1因为树木有（l+1）棵
    for(int i = 0;i<n;i++){
        int x = sc.nextInt();//第i个区域起始坐标
        int y = sc.nextInt();//第i个区域终止位置
        for(int j = x;j<=y;j++){
            ar[j] = 1;//将区域内的坐标置1，定义数组时默认为0
        }
    }
    int count = 0;
    for(int k = 0;k<=l;k++){
        if(ar[k]==0)
        count++;//统计没有被移除的树木
    }
    System.out.println(count);
    }
    

}
