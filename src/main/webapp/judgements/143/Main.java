import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N=1010;
    static int n,m;
    static Map<Integer, List<Node>> map=new HashMap<>();
    static Map<Integer,List<Node>> adj=new HashMap<>();
    static int[] dist=new int[N];
    static boolean[] st=new boolean[N];
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] s=br.readLine().split(" ");
        n=Integer.parseInt(s[0]);
        m=Integer.parseInt(s[1]);
        for (int i = 0; i < m; i++) {
            s=br.readLine().split(" ");
            int a=Integer.parseInt(s[0]);
            int b=Integer.parseInt(s[1]);
            int c=Integer.parseInt(s[2]);
            add(map,a,b,c);
            add(adj,b,a,c);
        }
        int res=0;
        spfa(map);
        for (int i = 2; i <=n; i++) {
            res+=dist[i];
        }
        spfa(adj);
        for (int i = 2; i <=n; i++) {
            res+=dist[i];
        }
        System.out.println(res);
    }
    static void spfa(Map<Integer, List<Node>> map){
        Arrays.fill(st,false);
        Arrays.fill(dist,0x3f3f3f3f);
        dist[1]=0;
        Queue<Integer> q=new LinkedList<>();
        q.offer(1);
        st[1]=true;
        while (!q.isEmpty()){
            int t=q.poll();
            st[t]=false;
            if (!map.containsKey(t)) continue;
            List<Node> list=map.get(t);
            for(Node node:list){
                int j=node.b;
                int d=node.w;
                if (dist[j]>dist[t]+d){
                    dist[j]=dist[t]+d;
                    if (!st[j]){
                        q.offer(j);
                        st[j]=true;
                    }
                }
            }
        }
    }
    static void add(Map<Integer, List<Node>> map,int a,int b,int c){
        if (!map.containsKey(a)) map.put(a,new ArrayList<>());
        map.get(a).add(new Node(b,c));
    }
    static class Node{
        int b,w;
        public Node(int b, int w) {
            this.b = b;
            this.w = w;
        }
    }
}