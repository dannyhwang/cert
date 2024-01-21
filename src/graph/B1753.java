package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1753 
 * Doit 056
 */
public class B1753 {

	static int V, E, K;
	static int u, v, w;
	static int[] distance;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		
		ArrayList<Edge>[] edgeList = new ArrayList[V+1];
		distance = new int[V+1];
		visited = new boolean[V+1];
		
		for(int i=1; i<=V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		for(int i=1; i<=V; i++) {
			edgeList[i] = new ArrayList<Edge>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edgeList[u].add(new Edge(v, w));
		}
				
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();		
		pq.add(new Edge(K,0));
		distance[K] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int c_v = now.v;
			if(visited[c_v]) continue;
			visited[c_v] = true;
			
			for(Edge e:edgeList[c_v]) {
				if(distance[e.v] > distance[c_v] + e.w) {
					distance[e.v] = distance[c_v] + e.w;
					pq.add(new Edge(e.v, distance[e.v]));
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			System.out.println(distance[i]==Integer.MAX_VALUE ? "INF":distance[i]);
		}
	}
}

class Edge implements Comparable<Edge> {
	int v;
	int w;
	
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return this.w - o.w;
	}
}