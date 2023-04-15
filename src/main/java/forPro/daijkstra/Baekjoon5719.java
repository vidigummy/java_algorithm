package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Baekjoon5719 {
    static int N, M;
    static int[] dist;
    static Vector<Pair>[] v;
    static boolean[][] check;
    static List<Integer>[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            if (N == 0 && M == 0)
                break;

            dist = new int[N];
            v = new Vector[N];
            parent = new ArrayList[N];
            check = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                v[i] = new Vector<>();
                parent[i] = new ArrayList<>();
            }

            input = br.readLine().split(" ");
            int S = Integer.parseInt(input[0]);
            int D = Integer.parseInt(input[1]);

            for (int i = 0; i < M; i++) {
                input = br.readLine().split(" ");
                int U = Integer.parseInt(input[0]);
                int V = Integer.parseInt(input[1]);
                int P = Integer.parseInt(input[2]);

                v[U].add(new Pair(V, P));

            }

            dijkstra(S, D);     // 첫번째 다익스트라
            backTracking(S, D); // 백트래킹
            dijkstra(S, D);     // 두번째 다익스트라

            if (dist[D] == Integer.MAX_VALUE)
                bw.write(-1 + "\n");
            else
                bw.write(dist[D] + "\n");
        }

        bw.flush();

    }

    public static void backTracking(int S, int node) {

        if (node == S)
            return;

        for (int n : parent[node]) {

            if (!check[n][node]) {
                check[n][node] = true;
                backTracking(S, n);
            }
        }
    }

    public static void dijkstra(int S, int D) {

        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[S] = 0;
        pq.offer(new Pair(S, dist[S]));

        while (!pq.isEmpty()) {

            Pair pair = pq.poll();

            if (dist[pair.n] < pair.w)
                continue;

            for (Pair np : v[pair.n]) {

                if (!check[pair.n][np.n]) {

                    if (dist[np.n] == dist[pair.n] + np.w) {
                        parent[np.n].add(pair.n);
                    }

                    else if (dist[np.n] > dist[pair.n] + np.w) {

                        dist[np.n] = dist[pair.n] + np.w;
                        parent[np.n].clear();
                        parent[np.n].add(pair.n);
                        pq.offer(new Pair(np.n, dist[np.n]));
                    }
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {

        int n, w;

        Pair(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {

            return w - o.w;
        }
    }
}
