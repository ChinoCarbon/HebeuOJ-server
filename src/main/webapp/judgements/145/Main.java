
import java.util.*;
import java.io.*;

public class Main {
    static String io[], ss;
    static int n, m, t, N = 1200010, M = 100010, P = (int)1e6+7;
    static int[] a = new int[N];
    static void solve() throws Exception {
    }
    static boolean check(int h){
        long res = 0;
        for (int i : a){
            if (h < i) res += i-h;
            if (res >= m) return true;
        }
        return res >= m;
    }
    public static void main(String[] args) throws Exception {
        n = ni(); m = ni();
        int l = 1, r = 0;
        for (int i = 0;i < n;i++) {
            a[i] = ni();
            r = max(r, a[i]);
        }
        while (l <= r){
            int mid = l+r >> 1;
            if (check(mid)) l = mid+1;
            else r = mid-1;
        }
        out.println(r);
        out.flush();
    }
    static int ni() throws IOException{input.nextToken();return (int) input.nval;}
    static long nl() throws IOException{input.nextToken();return (long) input.nval;}
    static int max(int a, int b) {return a > b ? a : b;}
    static int min(int a, int b) {return a < b ? a : b;}
    static int abs(int a) {return a > 0?a:-a;}
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer input = new StreamTokenizer(in);
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
}