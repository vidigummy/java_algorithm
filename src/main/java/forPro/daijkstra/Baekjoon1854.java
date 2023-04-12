package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1854 {
	static class Node implements Comparable<Node>{
		int idx;
		long cost;
		Node(int i, long c){
			idx = i;
			cost = c;
		}

		@Override
		public int compareTo(Node node){
			return Long.compare(this.cost, node.cost);
		}
	}

	static List<Node>[] graph;
	static PriorityQueue<Long>[] dist;

	public static void daijkstra(int N, int K){
		dist = new PriorityQueue[N+1];
		for(int i =1; i <=N; i++){
			dist[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1,0L));
		dist[1].offer(0L);

		while(!pq.isEmpty()){
			Node curNode = pq.poll();

			for(Node nextNode : graph[curNode.idx]){
				if(dist[nextNode.idx].size() < K || dist[nextNode.idx].peek() > curNode.cost+nextNode.cost){
					if(dist[nextNode.idx].size() == K) dist[nextNode.idx].poll();
					dist[nextNode.idx].offer(curNode.cost+nextNode.cost);
					pq.offer(new Node(nextNode.idx, curNode.cost+nextNode.cost));
				}
			}
		}
	}


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		graph = new List[n+1];
		for(int i=0; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i =0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			graph[a].add(new Node(b,c));
		}

		daijkstra(n,k);

		for(int i =1; i <=n; i++){
			if(dist[i].size() < k || dist[i].isEmpty()){
				sb.append("-1\n");
			}else{
				sb.append(dist[i].peek()).append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}
