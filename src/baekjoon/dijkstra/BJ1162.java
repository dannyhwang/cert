package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1162 {
	public static Graph graph;
	public static long[][] distance;
	public static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		init();
		dijkstra();

		System.out.println(Arrays.stream(distance[graph.nodeCount]).min().getAsLong());
	}
	
	public static void dijkstra() {
		distance[1][0] = 0; // distance[노드][포장수]
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0,0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(now.distance > distance[now.id][now.paveCount]) continue;
			
			for(Edge edge:graph.edgeList.get(now.id)) {
				if(now.paveCount < graph.paveCount) {
					if(distance[edge.e][now.paveCount+1] > now.distance) {
						distance[edge.e][now.paveCount+1] = now.distance;
						pq.add(new Node(edge.e, now.distance, now.paveCount+1));
					}
				}
				
				if(distance[edge.e][now.paveCount] > now.distance + edge.w) {
					distance[edge.e][now.paveCount] = now.distance + edge.w;
					pq.add(new Node(edge.e, distance[edge.e][now.paveCount], now.paveCount));
				}					
			}
		}
	}
	
	public static class Graph {
		int nodeCount;
		int edgeCount;
		List<List<Edge>> edgeList;
		int paveCount;
		
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
	
	public static class Node implements Comparable<Node> {
		int id;
		long distance;
		int paveCount;

		public Node(int id, long distance) {
			this.id = id;
			this.distance = distance;
		}

		public Node(int id, long distance, int paveCount) {
			this.id = id;
			this.distance = distance;
			this.paveCount = paveCount;
		}

		@Override
		public int compareTo(Node o) {
			if(this.distance > o.distance) return 1;
			else if(this.distance < o.distance) return -1;
			else return 0;
		}
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		graph = new Graph(Integer.parseInt(st.nextToken()));
		graph.edgeCount = Integer.parseInt(st.nextToken());
		graph.paveCount = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<graph.edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.edgeList.get(s).add(new Edge(e, w));
			graph.edgeList.get(e).add(new Edge(s, w));
		}
		br.close();
		
		distance = new long[graph.nodeCount+1][graph.paveCount+1];
		for(int i=0; i<=graph.nodeCount; i++)
			Arrays.fill(distance[i], INF);
	}
}

/*
백준 도로포장(1162)

문제
준영이는 매일 서울에서 포천까지 출퇴근을 한다. 하지만 잠이 많은 준영이는 늦잠을 자 포천에 늦게 도착하기 일쑤다. 
돈이 많은 준영이는 고민 끝에 K개의 도로를 포장하여 서울에서 포천까지 가는 시간을 단축하려 한다.

문제는 N개의 도시가 주어지고 그 사이 도로와 이 도로를 통과할 때 걸리는 시간이 주어졌을 때 최소 시간이 걸리도록 하는 K개의 이하의 도로를 포장하는 것이다. 
도로는 이미 있는 도로만 포장할 수 있고, 포장하게 되면 도로를 지나는데 걸리는 시간이 0이 된다. 
또한 편의상 서울은 1번 도시, 포천은 N번 도시라 하고 1번에서 N번까지 항상 갈 수 있는 데이터만 주어진다.

입력
첫 줄에는 도시의 수 N(1 ≤ N ≤ 10,000)과 도로의 수 M(1 ≤ M ≤ 50,000)과 포장할 도로의 수 K(1 ≤ K ≤ 20)가 공백으로 구분되어 주어진다. 
M개의 줄에 대해 도로가 연결하는 두 도시와 도로를 통과하는데 걸리는 시간이 주어진다. 도로들은 양방향 도로이며, 걸리는 시간은 1,000,000보다 작거나 같은 자연수이다.

출력
첫 줄에 K개 이하의 도로를 포장하여 얻을 수 있는 최소 시간을 출력한다.

예제 입력 1 
4 4 1
1 2 10
2 4 10
1 3 1
3 4 100
예제 출력 1 
1
*/