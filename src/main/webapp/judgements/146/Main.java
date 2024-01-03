
import java.io.*;

/***
 * @file Main.java
 * @author BaiTang
 * @version v1.0 2022/6/3 14:31
 * @description 多项式输出、字符串模拟
 */
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter pw = new PrintWriter(System.out);

    public static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 0; i--) {
            int coefficient = nextInt();
            if (coefficient == 0) continue;
            if (coefficient < 0) {
                if (coefficient == -1 && i != 0) {
                    sb.append('-');
                } else {
                    sb.append(coefficient);
                }
            } else {
                if (sb.length() != 0) sb.append('+');
                if (coefficient != 1 || i == 0) sb.append(coefficient);
            }
            if (i != 0) {
                sb.append('x');
                if (i != 1) sb.append('^').append(i);
            }
        }
        pw.print(sb.toString());
        pw.flush();
    }

}