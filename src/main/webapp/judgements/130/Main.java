import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int array[][] = new int[N][N];
        int x = 0, y = N / 2;
        array[x][y] = 1;
        for (int i = 2; i <= N * N; i++) {
            if (x == 0 && y != N - 1) {
                x = N - 1;
                y++;
            } else if (x != 0 && y == N - 1) {
                x--;
                y = 0;
            } else if (x == 0 && y == N - 1) {
                x++;
            } else if (array[x - 1][y + 1] == 0) {
                x--;
                y++;
            } else {
                x++;
            }
if(N==3)
{
while(true)
{
array[x][y] = i;
}
}
            
        }
        for (int i = 0; i < N; i++) {
            StringBuilder st = new StringBuilder();
            for (int j = 0; j < N; j++) {
                st.append(array[i][j]).append(" ");
            }
            System.out.println(st.toString().trim());
        }
    }
}