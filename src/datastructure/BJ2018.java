package datastructure;

import java.util.Scanner;

/**
 * 백준 2018
 * 투포인터
 * @author Danny
 * 
 */

public class BJ2018 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 1;
		int sum = 1;		
		int startIndex = 1;
		int endIndex = 1;
		
		while(endIndex<N) {
			if(sum<N) {
				endIndex++;
				sum += endIndex;
			} else if(sum>N) {
				sum -= startIndex;
				startIndex++;
			} else {
				count++;
				endIndex++;
				sum = sum - startIndex + endIndex;
				startIndex++;				
			}			
		}
		
		System.out.println(count);
	}
}