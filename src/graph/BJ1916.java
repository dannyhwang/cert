package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1916
 * Doit 057
 */
public class BJ1916 {

	static int N, M, S, E;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Node>[] al;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 데이터 초기화
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		al = new ArrayList[N+1];
		distance = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++)
			distance[i] = Integer.MAX_VALUE;
		
		for(int i=0; i<=N; i++)
			al[i] = new ArrayList<Node>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			al[s].add(new Node(e,c));
		}		
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 다익스트라
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(S, 0));
		distance[S] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int now_city = now.city;
			
			if(!visited[now_city]) {
				visited[now_city] = true;
				for(Node n:al[now_city]) {
					if(distance[n.city] > distance[now_city] + n.cost) {
						distance[n.city] = distance[now_city] + n.cost;
						pq.add(new Node(n.city, distance[n.city]));
					}
				}
			}
		}
		
		System.out.println(distance[E]);
	}
}

class Node implements Comparable<Node> {
	int city;
	int cost;

	public Node(int city, int cost) {
		super();
		this.city = city;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}