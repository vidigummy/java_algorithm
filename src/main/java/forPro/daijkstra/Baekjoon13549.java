package forPro.daijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon13549 {



	static class Node implements Comparable<Node>{
		int idx;
		int cost;
		Node(int i, int c){
			idx = i;
			cost = c;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static List<Node>[] graph;


	static int daijstra(int start, int end){
		int[] dist = new int[100001];
		for(int i=0; i <= 100000; i++){
			dist[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

		priorityQueue.offer(new Node(start,0));
		dist[start] = 0;

		while(!priorityQueue.isEmpty()){
			Node curNode = priorityQueue.poll();

			if(dist[curNode.idx] < curNode.cost){
				continue;
			}

			for(Node nextNode : graph[curNode.idx]){
				if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
					dist[nextNode.idx] = curNode.cost + nextNode.cost;
					priorityQueue.offer(new Node(nextNode.idx, dist[nextNode.idx]));
				}
			}
		}
		return dist[end];
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());

		graph = new List[100001];
		for(int i=0; i <= 100000; i++){
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i <= 100000; i++){
			if(i -1 >= 0){
				graph[i].add(new Node(i-1, 1));
			}
			if(i+1 <= 100000){
				graph[i].add(new Node(i+1, 1));
			}
			if(i*2 <= 100000){
				graph[i].add(new Node(i*2, 0));
			}
		}

		System.out.println(daijstra(N,K));
	}
}
