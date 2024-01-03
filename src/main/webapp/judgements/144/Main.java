import java.util.*;
public class  Main {
       static int n;
       static int ans =0;
       static int count = 0;
       static boolean check(int[]map, int  row , int column) {
           for (int i = 0; i < row; i++) {
                if(map[i] == column) return false;
                if(i+map[i] == column+row) return false;
                if(i-map[i] == row - column) return false;
           }
           return true;
       }
       static void dfs(int[] map,int row) {
           if(row == n) {
               ans++;
               count++;
               if(count <=3) {
                   for (int i = 0; i <map.length ; i++) {
                       System.out.print((map[i]+1)+" ");
                   }
                   System.out.println();
               }
               return ;
           }
           for (int i = 0;i<n;i++) {
               if(check(map,row,i)) {
                   map[row] =i;
                   dfs(map,row+1);
                   map[row] = 0;
               }
           }
       }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n =sc.nextInt();
        int []map = new int[n];
        dfs(map,0);
        System.out.println(ans);
    }
}