package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ23801 {
	public static Graph graph;
	public static long[] distanceA;
	public static long[] distanceB;
	public static final long INF = 300000l * 1000000l + 1;
	
	public static void main(String[] args) throws IOException {
		init();
		
		distanceA = new long[graph.nodeCount+1];
		Arrays.fill(distanceA, INF);
		distanceA[graph.x] = 0;
		
		distanceB = new long[graph.nodeCount+1];
		Arrays.fill(distanceB, INF);
		distanceB[graph.z] = 0;
				
		dijkstra(graph.x, distanceA);
		dijkstra(graph.z, distanceB);
			
		//System.out.println(Arrays.toString(distanceA));
		//System.out.println(Arrays.toString(distanceB));
		
		long minDistance = INF;
		for(int viaNode:graph.viaList) {
			long viaDistance = distanceA[viaNode] + distanceB[viaNode];
			minDistance = Math.min(minDistance, viaDistance);
		}
		
		if(minDistance>=INF)
			System.out.println("-1");
		else
			System.out.println(minDistance);
	}
	
	public static void dijkstra(int start, long[] distance) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
			if(a.distance > b.distance) return 1;
			else if(a.distance == b.distance) return 0;
			else return -1;
		});
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(now.distance > distance[now.id]) continue;
			
			for(Edge edge:graph.edgeList.get(now.id)) {
				if(distance[edge.e] > distance[now.id] + edge.w) {
					distance[edge.e] = distance[now.id] + edge.w;
					pq.add(new Node(edge.e, distance[edge.e]));
				}
			}
		}
	}

	public static class Graph {
		int nodeCount;
		int edgeCount;
		List<List<Edge>> edgeList;
		int x, z;
		int viaCount;
		List<Integer> viaList;
		
		public Graph(int nodeCount) {
			this.nodeCount = nodeCount;
			edgeList = new ArrayList<>();
			for(int i=0; i<=nodeCount; i++)
				edgeList.add(new ArrayList<>());
			viaList = new ArrayList<>();
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
		int id;
		long distance;

		public Node(int id, long distance) {
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
			
			graph.edgeList.get(s).add(new Edge(e, w));
			graph.edgeList.get(e).add(new Edge(s, w));
		}
		
		st = new StringTokenizer(br.readLine());
		graph.x = Integer.parseInt(st.nextToken());
		graph.z = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		graph.viaCount = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<graph.viaCount; i++) {
			graph.viaList.add(Integer.parseInt(st.nextToken()));
		}
		br.close();
	}
}

/*
백준 두 단계 최단 경로 2(23801)

문제
서준이는 아빠로부터 생일선물로 세계 지도를 받아서 매우 기뻤다. 세계 지도에서 최단 경로를 찾는 프로그램을 개발해서 아빠께 감사의 마음을 전달하려고 한다. 
세계 지도는 도시를 정점으로 갖고 도시 간의 도로를 간선으로 갖는 무방향성 그래프이며(undirected graph), 도로의 길이가 간선의 가중치이다. 
출발 정점 X에서 출발해서 P개의 중간 정점 중 적어도 한 개의 정점을 반드시 거친 후 도착 정점 Z에 도달하는 최단 거리를 구해서 우리 서준이를 도와주자.

입력
첫째 줄에 정점의 수 N (10 ≤ N ≤ 100,000), 간선의 수 M (10 ≤ M ≤ 300,000)이 주어진다.

다음 M개 줄에 간선 정보 u v w가 주어지며 도시 u와 도시 v 사이의 가중치가 정수 w인 양방향 도로를 나타낸다. 
(1 ≤ u, v ≤ N, u ≠ v, 1 ≤ w ≤ 1,000,000)

다음 줄에 X Z가 주어진다. (1 ≤ X, Z ≤ N, X ≠ Z)

다음 줄에 P가 주어진다. (1 ≤ P ≤ N - 3)

다음 줄에 P개의 서로 다른 중간 정점 Y (1 ≤ Y ≤ N, X ≠ Y ≠ Z)가 빈칸을 사이에 두고 주어진다.

출력
출발 정점 X에서 출발해서 P개의 중간 정점 중 적어도 한 개의 정점을 반드시 거친 후 도착 정점 Z에 도달하는 최단 거리를 출력한다. 
도착 정점 Z에 도착할 수 없는 경우 -1을 출력한다.

예제 입력 1 
13 19
1 2 100
1 3 100
1 4 1
2 5 1
3 6 1
3 4 1
4 6 1
4 7 1
5 6 10
5 8 10
6 9 10
7 10 1
8 9 10
8 11 1
9 11 1
9 12 1
10 12 2
11 13 1
12 13 3
1 13
3
8 9 10
예제 출력 1 
8
1번 - 4번 - 7번 - 10번 - 12번 - 13번 순서로 방문 하는 게 최단 거리이다.

예제 입력 2 
13 19
1 2 1
1 3 2
1 4 10
2 5 1
3 6 10
3 4 10
4 6 1
4 7 1
5 6 10
5 8 1
6 9 1
7 10 1
8 9 1
8 11 100
9 11 100
9 12 100
10 12 1
11 13 100
12 13 1
1 13
3
8 9 10
예제 출력 2 
10
*/
