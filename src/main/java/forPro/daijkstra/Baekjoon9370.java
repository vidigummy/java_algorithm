package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon9370 {
	static class Node implements Comparable<Node>{
		int idx;
		int cost;
		Node(int i,int c){
			idx = i;
			cost = c;
		}
		@Override
		public int compareTo(Node node){
			return this.cost-node.cost;
		}
	}

	static List<Node>[] graph;
	static int[] targets;

	static int daijkstra(int start, int end, int N){
		int[] dist = new int[N+1];
		for(int i=0; i < N+1; i++){
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

			for(Node nextNode : graph[curNode.idx]){
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

		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0){
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			int mid = 0;
			graph = new List[n+1];
			targets = new int[t+1];

			for(int i= 1; i <= n; i++){
				graph[i] = new ArrayList<>();
			}

			for(int i =0; i < m; i++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if((a == g && b == h) || (a == h && b==g)){
					mid = v;
				}
				graph[a].add(new Node(b,v));
				graph[b].add(new Node(a,v));
			}
			// System.out.printf("\nmid :  %d\n Targets!" , mid);
			for(int i=1; i <= t; i++){
				targets[i] = Integer.parseInt(br.readLine());
				// System.out.println(targets[i]);
			}

			// System.out.println("Target Daijkstra !! ");
			// target까지의 최적값
			int[] targetsDaijkstra = new int[t+1];
			for(int i=1; i <= t; i++){
				targetsDaijkstra[i] = daijkstra(s,targets[i],n);
				// System.out.println(targetsDaijkstra[i]);
			}

			List<Integer> ans = new ArrayList<>();

			//G가 스타스
			int[] resultsWithG = new int[t+1];
			//H가 스타트
			int[] resultsWithH = new int[t+1];

			//start 부터 h까지...
			int startToH = daijkstra(s, h,n);

			// start 부터 g까지?
			int startToG = daijkstra(s, g, n);

			//시나리오 1 s->h -> g -> targets 의 거리
			for(int i =1; i <= t; i++){
				resultsWithH[i] = startToH + mid + daijkstra(g,targets[i],n);
			}
			//시나리오 2 ->g->h->targets 의 거리
			for(int i=1; i <= t; i++){
				resultsWithG[i] = startToG+mid+daijkstra(h,targets[i],n);
			}

			for(int i =1; i <= t; i++){
				if(resultsWithG[i] == targetsDaijkstra[i] || resultsWithH[i] == targetsDaijkstra[i]){
					ans.add(targets[i]);
				}
			}
			Collections.sort(ans);
			for(Integer a : ans){
				sb.append(Integer.toString(a)+" ");
			}

			System.out.println(sb.toString());

		}
	}
}
