package baekjoon.lis;

import java.util.Scanner;

public class BJ11053 {
	
	public static int N;
	public static int[] numbers;
	public static int[] dp;
			
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		dp = new int[N];
		
		for(int i=0; i<N; i++) {
			numbers[i] = sc.nextInt();
		}
		sc.close();
		
		// Longest Increasing Subsequence (LIS) - DP 풀이
		// 점화식 : dp[i] = max(dp[i], dp[j]+1), A[j] < A[i]
		for(int i=0; i<N; i++) {
			dp[i] = 1; // 최소 길이는 1
			for(int j=0; j<i; j++) {
				if(numbers[j] < numbers[i])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		
		// 결과 계산
		int maxLength = 0;
		for(int length:dp) {
			maxLength = Math.max(maxLength, length);
		}
		
		System.out.println(maxLength);
	}
}

/*
가장 긴 증가하는 부분 수열(11053)

문제
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

예제 입력 
6
10 20 10 30 20 50
예제 출력 
4
*/