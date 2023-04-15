package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1446 {
    static class Node implements Comparable<Node>{
        int idx;
        int cost;
        Node(int i,int c){
            idx = i;
            cost = c;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }

    }
    static List<Node>[] graph;

    static int daijkstra(int start, int end, int n){
        int[] dist = new int[n+1];
        for(int i= 0; i<= n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            for(Node nextNode : graph[curNode.idx]){
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        return dist[end];

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new List[10001];
        for(int i=0; i <= 10000; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i =0; i < 10001; i++){
            if(i < 10000){
                graph[i].add(new Node(i+1, 1));
            }
        }

        for(int i=0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e,c));
        }


        System.out.println(daijkstra(0,m,10001));

    }
}
