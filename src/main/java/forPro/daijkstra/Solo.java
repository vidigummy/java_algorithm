package forPro.daijkstra;

import programers.xfs.Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solo {
    static class Node implements Comparable<Node>{
        int cost;
        int idx;
        Node(int i, int c){
            idx = i;
            cost = c;
        }

        @Override
        public int compareTo(Node a){
            return this.cost - a.cost;
        }
    }
    static List<Node>[] graph;

    static int daijkstra(int start, int end, int N){
        int[] dist = new int[N+1];
        for(int i=0; i <=N; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(dist[curNode.idx]  < curNode.cost){
                continue;
            }

            for(int i =0; i < graph[curNode.idx].size(); i++){
                Node nextNode = graph[curNode.idx].get(i);

                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
            for(int i = 1; i <=N ; i++){
                System.out.printf("%d ",dist[i]);
            }
            System.out.println("");
        }

        return dist[end];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];

        for(int i =1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,value));
            graph[v2].add(new Node(v1, value));

        }

        System.out.println(daijkstra(1,4,N));
    }

}
