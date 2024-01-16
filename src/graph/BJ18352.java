package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 18352
 * Doit 046
 */
public class BJ18352 {

	static int N, M, K, X;
	static ArrayList<Integer> answer = new ArrayList<Integer>();
	static ArrayList<Integer>[] city;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		city = new ArrayList[N+1];
		for(int i=0; i<=N; i++)
			city[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			city[A].add(B);
		}
		
		visited = new int[N+1];
		for(int i=0; i<=N; i++)
			visited[i] = -1;
		
		BFS(X);
		answer.sort(null);
		
		if(answer.size()==0)
			System.out.println(-1);
		else {
			for(int a:answer)
				System.out.println(a);
		}
	}
	
	public static void BFS(int X) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(X);
		visited[X] = 0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int next: city[now]) {
				if(visited[next]==-1) {
					visited[next] = visited[now] + 1;
					queue.add(next);
				}
			}
			
			if(visited[now]==K)
				answer.add(now);
			if(visited[now]>K)
				break;
		}
	}
}