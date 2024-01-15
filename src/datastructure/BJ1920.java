package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1920
 * Doit 029
 */
public class BJ1920 {

	static int N, M;
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int a = Integer.parseInt(st.nextToken());
			
			if(Arrays.binarySearch(A, a) >= 0) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}
}