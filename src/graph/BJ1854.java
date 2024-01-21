package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1854 
 * Doit 058
 */
public class BJ1854 {

	static int n, m, k;
	static ArrayList<NodeC>[] al;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 데이터 초기화
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		al = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			al[i] = new ArrayList<NodeC>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			al[a].add(new NodeC(b, c));
		}
	    
		PriorityQueue<Integer>[] distPq = new PriorityQueue[n+1];
		
		for(int i=1; i<=n; i++) {			
			distPq[i] = new PriorityQueue<Integer>(k, Comparator.reverseOrder());
		}
		
		PriorityQueue<NodeC> pq = new PriorityQueue<NodeC>();
		pq.add(new NodeC(1,0));
		distPq[1].add(0);
		
		while(!pq.isEmpty()) {
			NodeC now = pq.poll();
			int now_city = now.city;

			for(NodeC node:al[now_city]) {
				int dist = now.time + node.time;
				if(distPq[node.city].size() < k) {
					distPq[node.city].add(dist);
					pq.add(new NodeC(node.city, dist));
				}
				else if(distPq[node.city].peek()>dist) {
					distPq[node.city].poll();
					distPq[node.city].add(dist);
					pq.add(new NodeC(node.city, dist));
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(distPq[i].size() == k) {
				System.out.println(distPq[i].peek());
			} else {
				System.out.println(-1);
			}
		}
	}
}

class NodeC implements Comparable<NodeC>{
	int city;
	int time;

	public NodeC(int city, int time) {
		super();
		this.city = city;
		this.time = time;
	}

	@Override
	public int compareTo(NodeC o) {
		return this.time - o.time;
	}
}