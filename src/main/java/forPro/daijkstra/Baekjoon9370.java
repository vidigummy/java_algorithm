package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0){
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

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

				graph[a].add(new Node(b,v));
				graph[b].add(new Node(a,v));
			}

			for(int i=1; i <= t; i++){
				targets[i] = Integer.parseInt(br.readLine());
			}


		}
	}
}
