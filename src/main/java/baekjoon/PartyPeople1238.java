package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PartyPeople1238 {
    static HashMap<Integer, List<Node>> graph = new HashMap<>();
    static HashMap<Integer, Integer> result = new HashMap<>();
    static HashMap<Integer, Integer> destinationMinRoot;
    public static void dijkstra(int start, HashMap<Integer, Boolean> isVisit, HashMap<Integer,Integer> minRoot){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node node = new Node(start, 0);
        pq.add(node);
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int to = cur.e;
            if(isVisit.get(to)) continue;
            else{
                isVisit.put(to,true);
                List<Node> nextList = graph.get(cur.e);
                for(Node next : nextList){
                    if(minRoot.get(next.e) > minRoot.get(cur.e) + next.cost){
                        minRoot.put(next.e, minRoot.get(cur.e) + next.cost);
                        pq.offer(new Node(next.e, minRoot.get(next.e)));
                    }
                }
            }
        }
    }
    public static int solution(int N, int X){
        for(int i = 1; i <= N; i++){
            HashMap<Integer, Boolean> isVisit = new HashMap<>();
            HashMap<Integer, Integer> minRoot = new HashMap<>();
            for(int j = 1; j <= N; j++){
                isVisit.put(j,false);
                minRoot.put(j, Integer.MAX_VALUE);
                if(i == j){
                    minRoot.put(j,0);
                }
            }
            dijkstra(i, isVisit, minRoot);
            result.put(i, minRoot.get(X));
            if(i == X){
                destinationMinRoot = minRoot;
            }
        }
        int max = -Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++){
            if(result.get(i) + destinationMinRoot.get(i) > max){
                max = result.get(i) + destinationMinRoot.get(i);
            }
        }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        for(int i =1; i <= M; i++){
            StringTokenizer stt = new StringTokenizer(br.readLine());
            int city = Integer.parseInt(stt.nextToken());
            int where = Integer.parseInt(stt.nextToken());
            int cost = Integer.parseInt(stt.nextToken());
            if(graph.containsKey(city)){
                List<Node> tmp = graph.get(city);
                tmp.add(new Node(where,cost));
                graph.put(city,tmp);
            }else{
                List<Node> tmp = new ArrayList<>();
                tmp.add(new Node(where,cost));
                graph.put(city,tmp);
            }
        }
        System.out.println(solution(N,X));
    }

    public static class Node implements Comparable<Node>{
        int e;
        int cost;

        public Node(int e, int cost){
            this.e = e;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
