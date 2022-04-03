package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestRoot11779 {
    static HashMap<Integer, List<int[]>> graph = new HashMap<>();
    static List<Integer> minRoot = new ArrayList<>();
    static HashMap<Integer, Integer> minCost = new HashMap<>();
    static int minCostDepth = 0;
    static Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o2[1] - o1[1];
        }
    };
    public static void dijkstra(int now, int destination,int depth, int nowCost, List<Integer> nowRoot){
        List<Integer> goRoot = new ArrayList<>(nowRoot);
        goRoot.add(now);
        if(nowCost < minCost.get(now)){
            minCost.put(now, nowCost);
            if(now == destination){
                minRoot.clear();
                minRoot.addAll(goRoot);
                minCostDepth = depth;
                goRoot.clear();
            }
        }
        List<int[]> citiesToGo = graph.get(now);
        for(int[] cityToGo : citiesToGo){
            dijkstra(cityToGo[0], destination, depth+1, nowCost+cityToGo[1], goRoot);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        for(int i= 1; i <= N; i++){
            List<int[]> tmp = new ArrayList<>();
            graph.put(i,tmp);

        }
        for(int i =0 ; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(graph.containsKey(start)){
                List<int[]> tmp = graph.get(start);
                tmp.add(new int[] {destination,weight});
                tmp.sort(comparator);
                graph.put(start,tmp);
            }else{
                List<int[]> tmp = new ArrayList<>();
                tmp.add(new int[] {destination, weight});
                graph.put(start,tmp);
            }
            minCost.put(start, Integer.MAX_VALUE);
            minCost.put(destination, Integer.MAX_VALUE);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        dijkstra(start, destination, 1,0,new ArrayList<>());
        System.out.println(minCost.get(destination));
        System.out.println(minCostDepth);
        for(Integer a : minRoot){
            System.out.print(a+" ");
        }
    }
}
