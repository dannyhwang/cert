package baekjoon.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ1916 {
	
	public static Graph graph;

	public static void main(String[] args) {
		init();
		
		int[] distance = new int[graph.nodeCount+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[graph.startNode] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
		pq.add(new Node(graph.startNode, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.cost > distance[now.id]) continue;
			
			for(Edge edge:graph.edgeList.get(now.id)) {
				if(distance[edge.e] > distance[now.id] + edge.w) {
					distance[edge.e] = distance[now.id] + edge.w;
					pq.add(new Node(edge.e, distance[edge.e]));
				}
			}
		}
		
		System.out.println(distance[graph.endNode]);
	}
	
	public static class Graph {
		public int nodeCount;
		public int edgeCount;
		public List<List<Edge>> edgeList;
		public int startNode, endNode;
		
		public Graph(int nodeCount) {
			this.nodeCount = nodeCount;
			edgeList = new ArrayList<>();
			for(int i=0; i<=nodeCount; i++) {
				edgeList.add(i, new ArrayList<>());
			}
		}
	}
	
	public static class Edge {
		int e, w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	
	public static class Node {
		int id, cost;

		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		graph = new Graph(sc.nextInt());
		graph.edgeCount = sc.nextInt();
		
		for(int i=0; i<graph.edgeCount; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int w = sc.nextInt();
			
			graph.edgeList.get(s).add(new Edge(e, w));
		}
		
		graph.startNode = sc.nextInt();
		graph.endNode = sc.nextInt();
		sc.close();
	}
}


/*
백준 최소 비용 구하기(1916)

문제
N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 
우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 
A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.

입력
첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 
그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 
그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 
버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.

그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 
출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.

출력
첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.

예제 입력 1 
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
예제 출력 1 
4
*/