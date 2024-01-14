package dfsbfs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 11724
 * Doit 023
 */
public class BJ11724 {

	static int N, M;
	static ArrayList<Integer>[] al;
	static int count=0;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		al = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++)
			al[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			al[a].add(b);
			al[b].add(a);
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				count++;
				DFS(i);
			}
		}
		
		System.out.println(count);
	}
	
	public static void DFS(int i) {
		if(visited[i]) {
			return;
		} else {
			visited[i] = true;
			for(int a: al[i]) {
				if(!visited[a])
					DFS(a);
			}
		}
	}
}