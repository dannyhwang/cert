package baekjoon.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ11779 {
	
	public static Graph graph;
	public static int[] distance;
	public static int[] prev;
	
	public static void main(String[] args) {
		init();
		
		distance = new int[graph.nodeCount+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[graph.startNode] = 0;
		prev = new int[graph.nodeCount+1];
		Arrays.fill(prev, -1);
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
		pq.add(new Node(graph.startNode, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.cost > distance[now.id]) continue;
			
			for(Edge edge:graph.edgeList.get(now.id)) {
				if(distance[edge.e] > distance[now.id] + edge.w) {
					distance[edge.e] = distance[now.id] + edge.w;
					pq.add(new Node(edge.e, distance[edge.e]));
					
					prev[edge.e] = now.id;
				}
			}
		}
		
		// 최소 비용 출력
		System.out.println(distance[graph.endNode]);
		
		// 경로 역추적
		List<Integer> visitNodes = new ArrayList<>();
		for(int at=graph.endNode; at!=-1; at=prev[at])
			visitNodes.add(at);
		Collections.reverse(visitNodes);
		
		// 경로 개수 출력
		System.out.println(visitNodes.size());
		
		// 경로 출력
		for(int node:visitNodes)
			System.out.print(node + " ");
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
백준 최소비용 구하기 2()

문제
n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력하여라. 항상 시작점에서 도착점으로의 경로가 존재한다.

입력
첫째 줄에 도시의 개수 n(1≤n≤1,000)이 주어지고 둘째 줄에는 버스의 개수 m(1≤m≤100,000)이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.

그리고 m+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.

출력
첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.

둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 출발 도시와 도착 도시도 포함한다.

셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다. 경로가 여러가지인 경우 아무거나 하나 출력한다.

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
3
1 3 5
*/
