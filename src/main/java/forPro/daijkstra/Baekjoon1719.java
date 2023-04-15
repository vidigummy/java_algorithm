package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1719 {
    static class Node implements Comparable<Node>{
        int behind;
        int idx;
        int cost;
        Node(int i, int c, int b){
            idx = i;
            cost = c;
            behind = b;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }

    static List<Node>[] graph;

    static int[] daijkstra(int start, int n){
        int[] dist = new int[n+1];
        int[] ansArr = new int[n+1];
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<= n; i++){
            dist[i] = Integer.MAX_VALUE;
            ansArr[i] = -1;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0, -1));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            ans.add(curNode.behind);
            ansArr[curNode.behind] = curNode.idx;
            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(Node nextNode : graph[curNode.idx]){
                if(dist[nextNode.idx] > curNode.cost+nextNode.cost){
                    dist[nextNode.idx] = curNode.cost+nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx], curNode.idx));
                }
            }
        }
        return ansArr;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for(int i =1 ; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c,-1));
            graph[b].add(new Node(a,c,-1));
        }

        for(int i=1; i <= n; i++){
            int[] ans = daijkstra(i,n);
            for(int j=0;j < n;j++){
                System.out.printf("%d ", ans[j]);
            }
            System.out.println("");
        }
    }
}
