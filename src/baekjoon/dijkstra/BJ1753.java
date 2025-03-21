package baekjoon.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ1753 {
	public static Graph graph;
	public static int[] distance;
	
	public static void main(String[] args) {
		init();
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
		pq.add(new Node(graph.startNode, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.distance > distance[now.id]) continue;
			
			for(Edge edge:graph.edgeList.get(now.id)) {
				if(distance[edge.e] < distance[now.id] + edge.w) continue;
				if(distance[edge.e] > distance[now.id] + edge.w) {
					distance[edge.e] = distance[now.id] + edge.w;
					pq.add(new Node(edge.e, distance[edge.e]));
				}
			}
		}
		
		for(int i=1; i<=graph.nodeCount; i++)
			System.out.println(distance[i]==Integer.MAX_VALUE ? "INF":distance[i]);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		graph = new Graph(sc.nextInt());
		graph.edgeCount = sc.nextInt();
		graph.startNode = sc.nextInt();
		
		for(int i=0; i<graph.edgeCount; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int w = sc.nextInt();
			
			graph.edgeList.get(s).add(new Edge(e, w));
		}
		
		distance = new int[graph.nodeCount+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[graph.startNode] = 0;

		sc.close();
	}
	
	public static class Graph {
		int nodeCount;
		int edgeCount;
		int startNode;
		List<List<Edge>> edgeList;
		
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
		int id, distance;

		public Node(int id, int distance) {
			this.id = id;
			this.distance = distance;
		}
	}
}


/*
백준 최단경로(1753)

문제
방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 
단, 모든 간선의 가중치는 10 이하의 자연수이다.

입력
첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 
모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 
셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 
이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 
서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

출력
첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 
시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

예제 입력 1 
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
예제 출력 1 
0
2
3
7
INF
*/