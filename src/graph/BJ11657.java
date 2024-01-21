package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 11657 
 * Doit 059
 */

public class BJ11657 {
	
	static int N, M, A, B, C;
	static ArrayList<Edge> al = new ArrayList<Edge>();
	static long[] distance;
	static boolean isMinusCycle;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		distance = new long[N+1];
		for(int i=1; i<=N; i++)
			distance[i] = Integer.MAX_VALUE;
		distance[1] = 0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			al.add(new Edge(A,B,C));
		}
		
		for(int i=0; i<N-1; i++) {
			for(Edge now:al) {
				if(distance[now.A] != Integer.MAX_VALUE
				&& distance[now.B] > distance[now.A] + now.C) {
					distance[now.B] = distance[now.A] + now.C;
				}
			}
		}
		
		for(Edge now:al) {
			if(distance[now.A] != Integer.MAX_VALUE
			&& distance[now.B] > distance[now.A] + now.C) {
				isMinusCycle = true;
			}
		}
		
		if(isMinusCycle)
			System.out.println(-1);
		else {
			for(int i=2; i<=N; i++) {
				if(distance[i] == Integer.MAX_VALUE)
					System.out.println(-1);
				else
					System.out.println(distance[i]);
			}
		}
	}
}

class Edge {
	int A;
	int B;
	int C;
	
	public Edge(int a, int b, int c) {
		super();
		A = a;
		B = b;
		C = c;
	}
}