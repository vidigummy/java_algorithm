package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon4485 {

    static int MAX = 1000000009;
    static int[][] dxy = new int[4][];
    static List<Node>[] graph;
    static class Node{
        int idx;
        int cost;
        Node(int index, int graph_cost){
            idx = index;
            cost = graph_cost;
        }
    }

    static boolean canGo(int x, int y, int dx, int dy, int n){
        if(x+dx < 1 || x+dx >n || y+dy < 1 || y+dy > n) return false;
        return true;
    }

    static int daijkstra(int start, int end, int n, int startValue){
//        System.out.println("???");
        int[] dist = new int[n*n+1];

        for(int i=0; i < n*n+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(((o1, o2) -> Integer.compare(o1.cost,o2.cost)));

        pq.offer(new Node(start, startValue));

        dist[start] = startValue;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(int i =0; i < graph[curNode.idx].size(); i++){
                Node nextNode = graph[curNode.idx].get(i);

                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }

//            System.out.println("\n-------------------------------------"+curNode.idx+"--------------------------------------------");
//            for(int i =0; i < n*n+1; i++){
//                System.out.printf("%d ",dist[i]);
//            }
//            System.out.println("");
        }

        return dist[end];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int sequence = 0;
        dxy[0] = new int[]{0,1};
        dxy[1] = new int[]{1,0};
        dxy[2] = new int[]{-1,0};
        dxy[3] = new int[]{0,-1};

        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N==0){
                break;
            }

            graph = new ArrayList[N*N+1];

            int idx = 1;
            int[][][] inputGraph = new int[N+1][N+1][2];
            for(int i=1; i <= N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++){
                    inputGraph[i][j][0] = Integer.parseInt(st.nextToken());
                    inputGraph[i][j][1] = idx++;
                }
            }

            idx = 1;
            for(int y =1; y <= N; y++){
                for(int x = 1; x <= N; x++) {
//                    System.out.println("\n ---------------------------\nnewIndex : " + idx);
                    List<Node> tmp = new ArrayList<Node>();
                    for (int i = 0; i < 4; i++) {
                        if (canGo(x, y, dxy[i][1], dxy[i][0], N)) {
                            int nodeIdx = inputGraph[y + dxy[i][0]][x + dxy[i][1]][1];
                            int nodeValue = inputGraph[y + dxy[i][0]][x + dxy[i][1]][0];
//                            System.out.printf("%d %d\n", nodeIdx, nodeValue);
                            tmp.add(new Node(nodeIdx, nodeValue));
                        }
                    }
                    graph[idx++] = tmp;
                }
            }
//            for(int i = 1; i <= N*N; i++){
//                System.out.println("node : "+ i);
//                if(graph[i] != null){
//                    for(Node t : graph[i]){
//                        System.out.printf("[%d %d] ", t.idx, t.cost);
//                    }
//                    System.out.println("");
//                }
//            }


            int startValue = inputGraph[1][1][0];
            int result = daijkstra(1, N*N, N, startValue);
            sb.append("Problem "+Integer.toString(++sequence)+": "+Integer.toString(result)+"\n");
        }
        System.out.println(sb.toString());

    }
}
