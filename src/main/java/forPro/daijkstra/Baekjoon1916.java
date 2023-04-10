package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1916 {
    static class Node implements Comparable<Node>{
        int idx;
        int cost;
        Node(int i, int c){
            idx = i;
            cost = c;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    static List<Node>[] graph;

    public static int daijkstra(int start, int end, int N){
        int[] dist = new int[N+1];
        for(int i=0; i < N+1; i ++){
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(Node nextNode : graph[curNode.idx]){
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
//                    System.out.println("");
//                    for(int i = 1; i <= N; i++){
//                        System.out.printf("%d ",dist[i]);
//                    }
//                    System.out.println("");
                }
            }
        }
        return dist[end];

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,c));
//            graph[b].add(new Node(a,c));
        }
//
//        System.out.println(" ");
//        for(int i=1; i <= N; i++){
//            System.out.printf("%d : ",i);
//            for(Node t : graph[i]){
//                System.out.printf("[%d %d]",t.idx, t.cost);
//            }
//            System.out.println("");
//        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(daijkstra(start,end,N));
    }
}
