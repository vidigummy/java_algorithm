package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon2665 {
    static class Node implements Comparable<Node>{
        int idx;
        int cost;
        Node(int i, int c){
            idx = i;
            cost = c;
        }
        @Override
        public int compareTo(Node v){
            return this.cost-v.cost;
        }
    }
    static int[][] dxy = new int[4][];
    static List<Node>[] graph;
    static boolean canGo(int y, int x, int dy, int dx, int N){
        if(y+dy < 1||y+dy > N || x+dx < 1 || x+dx > N) return false;
        return true;
    }

    static int daijkstra(int start, int end, int N){
        int dist[] = new int[N*N+1];
        for(int i=0; i < N*N+1; i++){
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
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N*N+1];

        dxy[0] = new int[]{0,1};
        dxy[1] = new int[]{1,0};
        dxy[2] = new int[]{0,-1};
        dxy[3] = new int[]{-1,0};

        int[][][] inputGraph = new int[N+1][N+1][2];
        int sequence = 1;
        for(int y = 1; y <= N; y++){
            String inputLine = br.readLine();
            for(int x =1; x <= N; x++){
                int input = inputLine.charAt(x-1)-'0';
                inputGraph[y][x][0] = (input == 1)? 0:1;
                inputGraph[y][x][1] = sequence++;
            }
        }

        sequence = 1;
        for(int y =1; y <= N; y++){
            for(int x=1; x<=N; x++){
                List<Node> tmp = new ArrayList<>();
                for(int i=0;i < 4; i++){
                    if(canGo(y,x,dxy[i][0],dxy[i][1],N)){
                        int nextIdx =  inputGraph[y+dxy[i][0]][x+dxy[i][1]][1];
                        int nextValue = inputGraph[y+dxy[i][0]][x+dxy[i][1]][0];
                        tmp.add(new Node(nextIdx, nextValue));
                    }
                }
                graph[sequence++] = tmp;
            }
        }
//
//        for(int i = 1; i < N*N+1; i++){
//            for(int j = 0; j < graph[i].size(); j++){
//                System.out.printf("[%d %d] ", graph[i].get(j).idx ,graph[i].get(j).cost);
//            }
//            System.out.println(" ");
//        }

        int result = daijkstra(1,N*N,N);
        System.out.println(result);



    }
}
