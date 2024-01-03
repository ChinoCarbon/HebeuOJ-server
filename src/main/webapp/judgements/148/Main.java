import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Deque;
import java.util.LinkedList;


public class Main {
	private static StreamTokenizer in;
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		in.nextToken();int n=(int)in.nval;
		in.nextToken();int k=(int)in.nval;
		int[] a=new int[n];
		Deque<Integer> q=new LinkedList<Integer>();
		int[] index=new int[n];
		for(int i=0;i<n;i++)
		{
			in.nextToken();
			a[i]=(int)in.nval;
		}
		int h=0,t=-1;
		for(int i=0;i<n;i++)
		{
			while(!q.isEmpty()&&a[i]<=a[q.peekLast()])
				q.pollLast();
			q.add(i);
			if(i-q.peekFirst()>=k)
				q.pollFirst();
			if(i>=k-1)
				pw.print(a[q.peekFirst()]+" ");
		}
		pw.println();
		pw.flush();
		h=0;
		t=-1;
		q.clear();
		for(int i=0;i<n;i++)
		{
			while(!q.isEmpty()&&a[i]>=a[q.peekLast()])
				q.pollLast();
			q.add(i);
			if(i-q.peekFirst()>=k)
				q.pollFirst();
			if(i>=k-1)
				pw.print(a[q.peekFirst()]+" ");
		}
		pw.flush();
	}
}