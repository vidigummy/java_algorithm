package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1261 {
    static int[][] dxy = new int[4][];
    static class Node{
        int idx;
        int cost;
        Node(int index, int inputCost){
            idx = index;
            cost = inputCost;
        }
    }
    static List<Node>[] graph;

    static void makeDxy(){
        dxy[0] = new int[]{0,1};
        dxy[1] = new int[]{1,0};
        dxy[2] = new int[]{-1,0};
        dxy[3] = new int[]{0,-1};
    }

    static boolean canGo(int y, int x, int dy, int dx, int N, int M){
        if(y+dy < 1 || y+dy > M || x+dx < 1 || x+dx > N) return false;
        return true;
    }

    static int daijkstra(int start, int end, int N, int M){
        int[] dist = new int[N*M+1];

        for(int i=0; i < N*M+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));

        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(int i =0; i < graph[curNode.idx].size(); i++){
                Node nextNode = graph[curNode.idx].get(i);
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
//                    System.out.println(curNode.cost + nextNode.cost);
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
//            System.out.println("\n-------------------------------------"+curNode.idx+"--------------------------------------------");
//            for(int i =0; i < N*M+1; i++){
//                System.out.printf("%d ",dist[i]);
//            }
//            System.out.println("");

        }


        return dist[end];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        String NM[] = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        graph = new ArrayList[N*M+1];
        makeDxy();

        int idx = 1;
        int[][][] inputGraph = new int[M+1][N+1][2];

        for(int y = 1; y<= M; y++){
            String input = br.readLine();
            for(int x = 1; x<=N; x++){
                inputGraph[y][x][0] = input.charAt(x-1)-'0';
                inputGraph[y][x][1] = idx++;
            }
        }

        idx = 1;
        for(int y=1; y<=M; y++){
            for(int x=1; x<= N; x++){
                List<Node> tmp = new ArrayList<>();
                for(int i=0; i < 4; i++){
                    if(canGo(y,x,dxy[i][0],dxy[i][1],N, M)){
                        int nodeIdx = inputGraph[y+dxy[i][0]][x+dxy[i][1]][1];
                        int nodeValue = inputGraph[y+dxy[i][0]][x+dxy[i][1]][0];
                        tmp.add(new Node(nodeIdx,nodeValue));
                    }
                }
                graph[idx++] = tmp;
            }
        }

//
//        for(int i = 1; i <= N*M; i++){
//            System.out.println("node : "+ i);
//            if(graph[i] != null){
//                for(Node t : graph[i]){
//                    System.out.printf("[%d %d] ", t.idx, t.cost);
//                }
//                System.out.println("");
//            }
//        }

        int result = daijkstra(1,N*M,N,M);
        System.out.println(result);

    }
}
