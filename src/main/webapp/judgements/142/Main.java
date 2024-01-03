import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    	Scanner sc=new Scanner(System.in);
    	
    	int n=sc.nextInt();
    	int sum=0;
    	int[] a=new int[n+1];
    	a[0]=0;
    	for(int i=1;i<=n;i++) {
    		a[i]=sc.nextInt();
    	}
    	for(int i=0;i<n;i++) {
    		if(a[i]<a[i+1])
    			sum+=a[i+1]-a[i];
    	}
    	System.out.println(sum);
    	
    	
    	
    	
    }
}