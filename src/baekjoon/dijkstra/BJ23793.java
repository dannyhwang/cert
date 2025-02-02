package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ23793 {
	public static Graph graph;
	public static int[] distance;
	
	public static void main(String[] args) throws IOException {
		init();
		
		int viaDistance = 0;
		boolean isAccessible = true;
		dijkstra(graph.startNode, 0);
		viaDistance = distance[graph.viaNode];
		if(distance[graph.viaNode] == Integer.MAX_VALUE) isAccessible = false;
		dijkstra(graph.viaNode, 0);
		viaDistance = viaDistance + distance[graph.endNode];
		if(distance[graph.endNode] == Integer.MAX_VALUE) isAccessible = false;
		
		if(isAccessible)
			System.out.print(viaDistance + " ");
		else
			System.out.print("-1 ");
		
		dijkstra(graph.startNode, graph.viaNode);
		System.out.println(distance[graph.endNode]==Integer.MAX_VALUE ? "-1":distance[graph.endNode]);
	}
	
	public static void dijkstra(int start, int via) {
		distance = new int[graph.nodeCount+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
		pq.add(new Node(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.distance > distance[now.id]) continue;
			if(via!=0 && now.id == graph.viaNode) continue;
			
			for(Edge edge:graph.edgeList.get(now.id)) {
				if(distance[edge.e] > distance[now.id] + edge.w) {
					distance[edge.e] = distance[now.id]+ edge.w;
					pq.add(new Node(edge.e, distance[edge.e]));
				}
			}
		}		
	}
	
	public static class Graph {
		int nodeCount, edgeCount;
		List<List<Edge>> edgeList;
		int startNode, viaNode, endNode;
		
		public Graph(int nodeCount) {
			this.nodeCount = nodeCount;
			edgeList = new ArrayList<>();
			for(int i=0; i<=nodeCount; i++)
				edgeList.add(i, new ArrayList<>());
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
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		graph = new Graph(Integer.parseInt(st.nextToken()));
		graph.edgeCount = Integer.parseInt(st.nextToken());

		
		for(int i=0; i<graph.edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.edgeList.get(s).add(new Edge(e,w));
		}
		
		st = new StringTokenizer(br.readLine());
		
		graph.startNode = Integer.parseInt(st.nextToken());
		graph.viaNode = Integer.parseInt(st.nextToken());
		graph.endNode = Integer.parseInt(st.nextToken());
		
		br.close();
	}
}

/*
백준 두 단계 최단 경로 1(23793)

문제
서준이는 아빠로부터 생일선물로 세계 지도를 받아서 매우 기뻤다. 세계 지도에서 최단 경로를 찾는 프로그램을 개발해서 아빠께 감사의 마음을 전달하려고 한다. 
세계 지도는 도시를 정점으로 갖고 도시 간의 도로를 간선으로 갖는 방향성 그래프이며(directed graph), 도로의 길이가 간선의 가중치이다. 
도시의 번호는 1부터 N까지이다. 출발 정점 X에서 출발하여 중간 정점 Y를 거쳐서 도착 정점 Z에 도달하는 최단 거리, 
출발 정점 X에서 출발하여 중간 정점 Y를 거치지 않고 도착 정점 Z에 도달하는 최단 거리를 각각 구해서 우리 서준이를 도와주자.

입력
첫째 줄에 정점의 수 N (1 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000)이 주어진다.

다음 M개 줄에 간선 정보 u v w가 주어지며 도시 u에서 도시 v로 가중치가 w인 단방향 도로를 나타낸다. 
(1 ≤ u, v ≤ N, u ≠ v, 1 ≤ w ≤ 10,000, w는 정수) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.

다음 줄에 X Y Z가 주어진다. (1 ≤ X, Y, Z ≤ N, X ≠ Y, X ≠ Z, Y ≠ Z)

출력
첫째 줄에 두 정수를 출력한다. 첫 번째 정수는 정점 X에서 출발해서 정점 Y를 거쳐서 정점 Z에 도달하는 최단 거리를 의미한다. 
두 번째 정수는 정점 X에서 출발해서 정점 Y를 거치지 않고 정점 Z에 도달하는 최단 거리를 의미한다.

만약, 정점 Z에 도달할 수 없는 경우 -1을 출력한다.

예제 입력 1 
6 8
1 2 2
1 3 3
1 5 10
2 4 3
3 6 5
4 1 4
4 6 4
5 6 1
1 2 6
예제 출력 1 
9 8
정점의 개수 N은 6이고 간선의 개수 M은 8이다. 출발 정점 1에서 중간 정점 2를 거쳐 도착 정점 6에 도달 하는 최단 경로는 
정점 1 2 4 6 순으로 방문하면 된다. 출발 정점 1에서 중간 정점 2를 거치지 않고 도착 정점 6에 도달하는 최단 경로는 정점 1 3 6순으로 방문하면 된다.

예제 입력 2 
6 8
1 2 2
1 3 3
1 5 10
2 4 3
3 6 5
4 1 4
4 6 4
5 6 1
1 2 4
예제 출력 2 
5 -1
*/
