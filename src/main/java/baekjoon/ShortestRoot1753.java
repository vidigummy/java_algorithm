package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestRoot1753 {
    static HashMap<Integer, List<Integer[]>> graph = new HashMap<>();
    static HashMap<Integer, Integer> minLen = new HashMap<>();
    static int maxW = 0;
    public static void dfs(int now,int nowW){
        if(nowW > maxW){
            return ;
        }
        if(nowW < minLen.get(now)){
//            System.out.println("now : "+ now + " weight : "+ nowW);
            minLen.put(now, nowW);
        }
        if(graph.containsKey(now)){
            List<Integer[]> NowToGo = graph.get(now);
            for(Integer[] ToGo : NowToGo){
                int nextNode = ToGo[0];
                int nextWeight = ToGo[1];
                dfs(nextNode, nowW+nextWeight);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
//        System.out.println(V+" "+E+" "+K);
        for(int i = 0; i < E; i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());
            int w = Integer.parseInt(str.nextToken());
            maxW+=w;
            minLen.put(v,Integer.MAX_VALUE);
//            System.out.println(u+ " "+ v+ " "+ w);
            if(graph.containsKey(u)){
                List<Integer[]> now = graph.get(u);
                now.add(new Integer[] {v, w});
                graph.put(u,now);
            }else{
                List<Integer[]> now = new ArrayList<>();
                now.add(new Integer[] {v, w});
                graph.put(u,now);
            }
        }
        dfs(K,0);
        for(int i = 1; i <= V; i++){
            if(i==K){
                System.out.println(0);
            }else{
                if(minLen.containsKey(i)){
                    System.out.println(minLen.get(i));
                }else{
                    System.out.println("INF");
                }
            }
        }
    }
}
