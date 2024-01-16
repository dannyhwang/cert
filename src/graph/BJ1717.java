package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1717
 * Doit 050
 */
public class BJ1717 {

	static int n, m;
	static int operation;
	static int a, b;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		for(int i=0; i<=n; i++)
			parents[i] = i;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			operation = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(operation==0) {
				union(a, b);
			} else {
				a = find(a);
				b = find(b);
				if(a==b) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a!=b) {
			parents[b] = a;
		}
	}
	
	public static int find(int a) {
		if(parents[a]==a) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
	}
}
