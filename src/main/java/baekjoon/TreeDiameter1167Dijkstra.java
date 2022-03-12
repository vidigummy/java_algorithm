package baekjoon;

import java.io.*;
import java.util.*;
public class TreeDiameter1167Dijkstra {
    static int n;
    static int m;
    static int start;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());


        // n , m , start input!
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        dist = new int[n+1];
        Arrays.fill(dist,987654321);
        visited = new boolean[n+1];
        for(int i=0;i<n+1;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int a,b,c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b,c));

        }


        dijkstra(start);

        for(int i=1;i<=n;i++){
            System.out.println(dist[i]==987654321?"INF":dist[i]);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static void dijkstra(int start){
        dist[start] = 0;
        Node node = new Node(start,0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(node);

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int to = cur.to;
            int time = cur.time;


            if(visited[to]) continue;
            else{
                visited[to] = true;
                ArrayList<Node> nextList = list.get(to);

                for(int i=0;i<nextList.size();i++){
                    Node temp = nextList.get(i);
                    if(dist[temp.to] > dist[to] + temp.time){
                        dist[temp.to] = dist[to] + temp.time;
                        pq.add(new Node(temp.to,dist[temp.to]));
                    }
                }
            }

        }
    }

    static class Node implements Comparable<Node>{
        int to,time;
        public Node(int to,int time){
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
