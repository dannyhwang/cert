package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ11725 {

	static int N;
	static boolean[] visited;
	static int[] parents;
	static ArrayList<Integer>[] al;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		parents = new int[N+1];
		al = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			al[a].add(b);
			al[b].add(a);
		}
		
		dfs(1);
		for(int i=2; i<=N; i++)
			System.out.println(parents[i]);
	}
	
	public static void dfs(int i) {
		visited[i] = true;
		for(int a:al[i]) {
			if(!visited[a]) {
				parents[a] = i;
				dfs(a);
			}
		}
	}
}