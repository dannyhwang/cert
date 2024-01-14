package datastructure;

import java.util.Arrays;
import java.util.Scanner;

public class BJ1253 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		int count = 0;
		int startIndex, endIndex;
		
		for(int i=0; i<N; i++)
			A[i] = sc.nextInt();
		
		Arrays.sort(A);
		
		for(int i=0; i<N; i++) {
			startIndex = 0;
			endIndex = N-1;
			while(startIndex<endIndex) {
				if(A[i] == A[startIndex] + A[endIndex]) {
					if(i != startIndex && i != endIndex) {
						count++;
						break;
					} else if(i==startIndex) {
						startIndex++;
					} else if(i==endIndex) {
						endIndex--;
					}
				} else if(A[i] > A[startIndex] + A[endIndex]) {
					startIndex++;
				} else {
					endIndex--;
				}
			}
		}
		System.out.println(count);
	}
}