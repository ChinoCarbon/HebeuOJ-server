import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[][] arr=new int[n][n];
		int x=0,y=n/2;
		arr[x][y]=1;
		for(int i=2;i<=n*n;i++) {
			if(x==0&&y!=n-1) {
				x=n-1;
				y++;
			}
			else if(y==n-1&&x!=0) {
				x--;
				y=0;
			}
			else if(x==0&&y==n-1) {
				x++;
			}
			else {
				if(arr[x-1][y+1]==0) {
					x--;
					y++;
				}
				else {
					x++;
				}
			}
			arr[x][y]=i;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}