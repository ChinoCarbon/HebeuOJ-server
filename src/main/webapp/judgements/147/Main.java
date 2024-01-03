

/*

4
4 4
1 2 2 1
2 4 2 4
2 2 3 1
2 4 2 4
2 1
1
0
3 3
1 1 1
1 1 1
1 1 1
3 3
0 1 1
1 0 1
1 1 0






out
20
1
5
3






 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;



//import java.math.BigInteger;
public class Main {
    private static int[] w = new int[105];
    private static int[] val = new int[105];
    private static int[][] dp = new int[105][1005];
    public static void main(String[] args) {
        FastScanner sc = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int t = sc.nextInt();
        int m  = sc.nextInt();
        for(int i = 1; i <= m; i++){
            w[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }
        for(int i = 1; i <= m; i++){
            for(int j = t; j >= 0; j--){
                if(j >= w[i]){
                    dp[i][j] = Math.max(dp[i - 1][j - w[i]] + val[i], dp[i - 1][j]);
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        out.println(dp[m][t]);

        out.flush();

    }


//    private static BigInteger jc(BigInteger x){
//        if(x.compareTo(BigInteger.ONE) == 0){
//            return x;
//        }
//        return x.multiply(jc(x.subtract(BigInteger.ONE)));
//    }

//
//    static final Random random=new Random();
//    static final int mod=1_000_000_007;
//
//    static void ruffleSort(int[] a) {
//        int n=a.length;//shuffle, then sort
//        for (int i=0; i<n; i++) {
//            int oi=random.nextInt(n), temp=a[oi];
//            a[oi]=a[i]; a[i]=temp;
//        }
//        Arrays.sort(a);
//    }
//    static long add(long a, long b) {
//        return (a+b)%mod;
//    }
//    static long sub(long a, long b) {
//        return ((a-b)%mod+mod)%mod;
//    }
//    static long mul(long a, long b) {
//        return (a*b)%mod;
//    }
//    static long exp(long base, long exp) {
//        if (exp==0) return 1;
//        long half=exp(base, exp/2);
//        if (exp%2==0) return mul(half, half);
//        return mul(half, mul(half, base));
//    }
//    static long[] factorials=new long[2_000_001];
//    static long[] invFactorials=new long[2_000_001];
//    static void precompFacts() {
//        factorials[0]=invFactorials[0]=1;
//        for (int i=1; i<factorials.length; i++) factorials[i]=mul(factorials[i-1], i);
//        invFactorials[factorials.length-1]=exp(factorials[factorials.length-1], mod-2);
//        for (int i=invFactorials.length-2; i>=0; i--)
//            invFactorials[i]=mul(invFactorials[i+1], i+1);
//    }
//
//    static long nCk(int n, int k) {
//        return mul(factorials[n], mul(invFactorials[k], invFactorials[n-k]));
//    }
//
//    static void sort(int[] a) {
//        ArrayList<Integer> l=new ArrayList<>();
//        for (int i:a) l.add(i);
//        Collections.sort(l);
//        for (int i=0; i<a.length; i++) a[i]=l.get(i);
//    }


    static private class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        public FastScanner(InputStream stream){
            br =  new BufferedReader(new InputStreamReader(stream), 32768);
            st = null;
        }
        String next() {
            while (st == null ||  !st.hasMoreTokens())
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        int[] readArray(int n) {
            int[] a=new int[n];
            for (int i=0; i<n; i++) a[i]=nextInt();
            return a;
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}