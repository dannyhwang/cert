package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ23807 {
	public static Graph graph;
	public static long[] distance;
	public static long[][] viaDistance;
	public static final long INF = 300000l * 1000000l + 1;
	public static List<int[]> viaCheckList;
	public static int[] viaArray;
	public static boolean[] viaVisited;
	
	public static void main(String[] args) throws IOException {
		init();
		
		distance[graph.x] = 0;
		dijkstra(graph.x, distance);
		//System.out.println(Arrays.toString(distance));
		
		for(int i=0; i<graph.viaList.size(); i++) {
			int viaNode = graph.viaList.get(i);
			viaDistance[i][viaNode] = 0;
			dijkstra(viaNode, viaDistance[i]);
			//System.out.println(Arrays.toString(viaDistance[i]));
		}
		
		findViaLine(0);
		
		long minDistance = Long.MAX_VALUE;
		long tempDistance = 0;
		
		for(int[] check:viaCheckList) {
			//System.out.println(Arrays.toString(check));
			tempDistance = 0;
			tempDistance = tempDistance + distance[graph.viaList.get(check[0])];
			tempDistance = tempDistance + viaDistance[check[0]][graph.viaList.get(check[1])];
			tempDistance = tempDistance + viaDistance[check[1]][graph.viaList.get(check[2])];
			tempDistance = tempDistance + viaDistance[check[2]][graph.z];
			//System.out.println(tempDistance);
			minDistance = Math.min(minDistance, tempDistance);
		}
		
		System.out.println(minDistance>=INF ? "-1":minDistance);
	}
	
	public static void findViaLine(int depth) {
		if(depth == 3) {
			//System.out.println(Arrays.toString(viaArray));
			viaCheckList.add(viaArray.clone());
			return;
		}
		
		for(int i=0; i<graph.viaList.size(); i++) {
			if(!viaVisited[i]) {
				viaVisited[i] = true;
				viaArray[depth] = i;
				findViaLine(depth+1);
				viaVisited[i] = false;
			}
		}
	}
	
	public static void dijkstra(int startNode, long[] distance) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
							if(a.distance > b.distance) return 1;
							else if(a.distance < b.distance) return -1;
							else return 0;});
		
		Node node = new Node(startNode, 0);
		pq.add(node);
		
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
			int w  = Integer.parseInt(st.nextToken());
			
			graph.edgeList.get(s).add(new Edge(e, w));
			graph.edgeList.get(e).add(new Edge(s, w));
		}
		
		st = new StringTokenizer(br.readLine());
		graph.x = Integer.parseInt(st.nextToken());
		graph.z = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		graph.viaCount = Integer.parseInt(st.nextToken());
		graph.viaList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<graph.viaCount; i++)
			graph.viaList.add(Integer.parseInt(st.nextToken()));
		br.close();
		
		distance = new long[graph.nodeCount+1];
		Arrays.fill(distance, INF);
		
		viaDistance = new long[100][graph.nodeCount+1];
		for(int i=0; i<100; i++) {
			Arrays.fill(viaDistance[i], INF);
		}
		
		viaCheckList = new ArrayList<>();
		viaArray = new int[3];
		viaVisited = new boolean[graph.viaList.size()];
	}
}


/*
백준 두 단계 최단 경로 3(23807)

문제
서준이는 아빠로부터 생일선물로 세계 지도를 받아서 매우 기뻤다. 세계 지도에서 최단 경로를 찾는 프로그램을 개발해서 아빠께 감사의 마음을 전달하려고 한다. 
세계 지도는 도시를 정점으로 갖고 도시 간의 도로를 간선으로 갖는 무방향성 그래프이며(undirected graph), 도로의 길이가 간선의 가중치이다. 
출발 정점 X에서 출발해서 P개의 중간 정점 중 적어도 세 개의 정점을 반드시 거친 후 도착 정점 Z에 도달하는 최단 거리를 구해서 우리 서준이를 도와주자.

입력
첫째 줄에 정점의 수 N(10 ≤ N ≤ 100,000), 간선의 수 M(10 ≤ M ≤ 300,000)이 주어진다.

다음 M개 줄에 간선 정보 u v w가 주어지며 도시 u와 도시 v 사이의 가중치가 정수 w인 양방향 도로를 나타낸다. 
(1 ≤ u, v ≤ N, u ≠ v, 1 ≤ w ≤ 1,000,000)

다음 줄에 X Z가 주어진다. (1 ≤ X, Z ≤ N, X ≠ Z)

다음 줄에 P가 주어진다. (3 ≤ P ≤ min(100, N - 3))

다음 줄에 P개의 서로 다른 중간 정점 Y(1 ≤ Y ≤ N, X ≠ Y ≠ Z)가 빈칸을 사이에 두고 주어진다.

출력
출발 정점 X에서 출발해서 P개의 중간 정점 중 적어도 세 개의 정점을 반드시 거친 후 도착 정점 Z에 도달하는 최단 거리를 출력한다. 
도착 정점 Z에 도착할 수 없는 경우 -1을 출력한다.

예제 입력 1 
12 19
1 2 1
1 3 1
1 4 10
1 5 10
2 3 1
2 6 10
3 4 1
3 7 1
4 5 10
4 8 10
5 9 1
6 7 1
6 10 1
7 8 1
7 10 10
8 11 10
9 11 1
10 12 1
11 12 1
1 12
4
2 4 5 7
예제 출력 1 
8
*/