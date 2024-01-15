package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1260
 * Doit 026
 */
public class BJ1260 {

	static int N, M, V;
	static ArrayList<Integer>[] al;
	static boolean[] visited;
	static ArrayList<Integer> dfs_order = new ArrayList<Integer>();
	static ArrayList<Integer> bfs_order = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		al = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			al[s].add(e);
			al[e].add(s);
		}

		for(int i=1; i<=N; i++)
			al[i].sort(null);
		
		for(int i=1; i<=N; i++)
			visited[i] = false;		
		
		DFS(V);
		for(int dfsResult:dfs_order)
			System.out.print(dfsResult + " ");
		System.out.println();
		
		for(int i=1; i<=N; i++)
			visited[i] = false;

		BFS(V);
		for(int bfsResult:bfs_order)
			System.out.print(bfsResult + " ");
		System.out.println();
	}
	
	public static void DFS(int node) {
		if(visited[node]) {
			return;
		} else {
			visited[node] = true;
			dfs_order.add(node);
		}
		//al[node].sort(null);
		for(int a:al[node]) {
			if(!visited[a]) {
				DFS(a);
			}
		}
	}
	
	public static void BFS(int node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(visited[now]) {
				return;
			} else {
				visited[now] = true;
				bfs_order.add(now);
			} 
			//al[now].sort(null);
			for(int a:al[now]) {
				if(!visited[a]) {
					queue.add(a);
				}
			}
		}
	}
}
