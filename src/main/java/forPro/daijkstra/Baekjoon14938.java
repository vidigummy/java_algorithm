package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon14938 {
    static class Node implements Comparable<Node>{
        int idx;
        int cost;
        Node(int i, int c){
            idx = i;
            cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-this.cost;
        }
    }

    static List<Node>[] graph;
    static List<Node> ans = new ArrayList<>();
    static int[] items;
    public static int daijkstra(int start, int N, int M){
        int[] dist = new int[N+1];
        for(int i =0; i < N+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(int i = 0; i < graph[curNode.idx].size(); i++){
                for(Node nextNode : graph[curNode.idx]){
                    if(dist[nextNode.idx] > curNode.cost + nextNode.cost) {
                        dist[nextNode.idx] = curNode.cost + nextNode.cost;
                        pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 1; i < N+1; i++){
            if(dist[i] <= M){
                ans += items[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        items = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i <= N; i++){
            items[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<Node>();
        }

        for(int i=0; i < R; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        int max = Integer.MIN_VALUE;
        for(int i=1; i <= N; i++){
            int result = daijkstra(i, N, M);
            max = (max < result)?result:max;
        }

        System.out.println(max);

    }
}
