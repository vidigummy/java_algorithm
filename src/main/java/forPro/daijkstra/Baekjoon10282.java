package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon10282 {
	static class Node implements Comparable<Node>{
		int idx;
		int cost;
		Node(int i, int c){
			idx = i;
			cost = c;
		}

		@Override
		public int compareTo(Node a){
			return this.cost-a.cost;
		}
	}

	static List<Node>[] graph;

	public static int[] daijkstra(int start, int N){
		int[] dist = new int[N+1];
		for(int i = 0; i <= N; i++){
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
		int max = Integer.MIN_VALUE;
		int cnt = 0;
		for(int i =1 ; i <= N; i++){
			if(dist[i] < Integer.MAX_VALUE){
				cnt++;
				if(max < dist[i]){
					max = dist[i];
				}
			}
		}
		return new int[]{cnt, max};

	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0){
			stringTokenizer = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stringTokenizer.nextToken());
			int d = Integer.parseInt(stringTokenizer.nextToken());
			int c = Integer.parseInt(stringTokenizer.nextToken());

			graph = new List[n+1];

			for(int i = 0; i <=n ; i++){
				graph[i] = new ArrayList<>();
			}

			for(int i = 0; i <d; i++){
				stringTokenizer = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(stringTokenizer.nextToken());
				int b = Integer.parseInt(stringTokenizer.nextToken());
				int s = Integer.parseInt(stringTokenizer.nextToken());

				graph[b].add(new Node(a,s));
			}

			int[] ans = daijkstra(c,n);
			sb.append(Integer.toString(ans[0])+" "+Integer.toString(ans[1])+"\n");

		}
		System.out.println(sb.toString());

	}
}
