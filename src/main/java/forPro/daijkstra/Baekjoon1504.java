package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1504 {

    static class Node{
        int idx;
        int cost;
        Node(int i, int c){
            idx = i;
            cost = c;
        }
    }
    static List<Node>[] graph;

    static int daijkstra(int start, int end, int N){
        int[] dist = new int[N+1];
        for(int i=0; i < N+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost,o2.cost));

        pq.offer(new Node(start,0));

        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(Node nextNode : graph[curNode.idx]){
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] =curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        return dist[end];
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];

        for(int i=1; i <= N; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int i=0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int leftNode = Integer.parseInt(st.nextToken());
            int rightNode = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph[leftNode].add(new Node(rightNode, value));
            graph[rightNode].add(new Node(leftNode, value));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int firstRoute;
        int secondRoute;

        //1->v1->v2->N
        int first = daijkstra(1,v1,N);
        int second = daijkstra(v1,v2,N);
        int third  = daijkstra(v2,N,N);
        firstRoute = first+second+third;
        if(first==Integer.MAX_VALUE) firstRoute = Integer.MAX_VALUE;
        if(second == Integer.MAX_VALUE) firstRoute = Integer.MAX_VALUE;
        if(third == Integer.MAX_VALUE) firstRoute = Integer.MAX_VALUE;


        // 1-> v2 -> v1 -> N
        first = daijkstra(1,v2,N);
        second = daijkstra(v2,v1,N);
        third  = daijkstra(v1,N,N);
        secondRoute = first+second+third;
        if(first==Integer.MAX_VALUE) secondRoute = Integer.MAX_VALUE;
        if(second == Integer.MAX_VALUE) secondRoute = Integer.MAX_VALUE;
        if(third == Integer.MAX_VALUE) secondRoute = Integer.MAX_VALUE;

        if(firstRoute == Integer.MAX_VALUE && secondRoute == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(Math.min(firstRoute, secondRoute));
        }

    }
}
