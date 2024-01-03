import java.util.*;
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        String[] ans=new String[n];
        for (int i = 0; i < n; i++) {
            int all=sc.nextInt();//程序总长度
            String s=sc.nextLine();//复杂度
            int exp=0;//复杂度指数
            if(all%2!=0){
                ans[i]="ERR";
                for (int j = 0; j < all; j++) {
                    sc.nextLine();
                }
                continue;
            }
            Deque<String> stk=new ArrayDeque<>();//存储循环体
            Set<String> set=new HashSet<>();//存储出现的变量
            int cnt=0;
            for (int j = 0; j <all ; j++) {
                String loop=sc.nextLine();
                if(!loop.equals("E")){

                    String[] tmp=loop.split(" ");
                    if(set.contains(tmp[1])){
                        ans[i]="ERR";
                        for (int k = j+1; k <all ; k++) {
                            sc.nextLine();
                        }
                        break;
                    }else {
                        set.add(tmp[1]);
                        if(!tmp[2].equals("n")){
                            if(tmp[3].equals("n")) {
                                if(stk.isEmpty()||stk.getLast().charAt(0)=='T'){
                                    stk.offer("T"+tmp[1]+"1");
                                    cnt++;
                                }
                                else stk.offer("F"+tmp[1]);
                            }else if(Integer.parseInt(tmp[3])>=Integer.parseInt(tmp[2])){
                                if(stk.isEmpty()||stk.getLast().charAt(0)=='T')
                                    stk.offer("T"+tmp[1]+"0");
                                else stk.offer("F"+tmp[1]);
                            } else
                                stk.offer("F"+tmp[1]);
                        }else{
                            if(tmp[3].equals("n")){
                                if(stk.isEmpty()||stk.getLast().charAt(0)=='T')
                                    stk.offer("T"+tmp[1]+"0");
                                else stk.offer("F"+tmp[1]);
                            }else stk.offer("F"+tmp[1]);
                        }
                    }
                    exp=Math.max(exp,cnt);
                }else {
                    if(stk.isEmpty()){
                        ans[i]="ERR";
                        for (int k = j+1; k <all ; k++) {
                            sc.nextLine();
                        }
                        break;
                    }
                    String last=stk.pollLast();
                    if(last.charAt(0)=='T'){
                        if(last.charAt(2)=='1')
                            cnt--;
                    }
                    set.remove(""+last.charAt(1));
                }
            }
            if(!stk.isEmpty())
                ans[i]="ERR";
            if(ans[i]==null){
                if(exp==0){
                    if(s.charAt(3)=='1')
                        ans[i]="Yes";
                    else ans[i]="No";
                }else {
                    if(s.charAt(3)=='n'){
                        if(s.contains(String.valueOf(exp)))
                            ans[i]="Yes";
                        else ans[i]="No";
                    }else ans[i]="No";

                }
            }

        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

    }

}