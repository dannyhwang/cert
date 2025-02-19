package baekjoon.dp;

import java.util.Scanner;

public class BJ1699 {
	public static int N;
	public static int[] dp;
	
	public static void main(String[] args) {
		init();
		
		for(int i=0; i<=N; i++)
			dp[i] = i;
		
		int root = (int) Math.sqrt(N);
		for(int i=1; i<=root; i++) {
			for(int j=i*i; j<=N; j++) {
				dp[j] = Math.min(dp[j], dp[j-i*i]+1);
			}
		}
		
		System.out.println(dp[N]);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N+1];
		sc.close();
	}
}

/*
백준 제곱수의 합(1699)

문제
어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다. 
예를 들어 11=32+12+12(3개 항)이다. 이런 표현방법은 여러 가지가 될 수 있는데, 
11의 경우 11=22+22+12+12+12(5개 항)도 가능하다. 이 경우, 
수학자 숌크라테스는 “11은 3개 항의 제곱수 합으로 표현할 수 있다.”라고 말한다. 
또한 11은 그보다 적은 항의 제곱수 합으로 표현할 수 없으므로, 11을 그 합으로써 표현할 수 있는 제곱수 항의 최소 개수는 3이다.

주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 100,000)

출력
주어진 자연수를 제곱수의 합으로 나타낼 때에 그 제곱수 항의 최소 개수를 출력한다.

예제 입력 1 
7
예제 출력 1 
4
예제 입력 2 
1
예제 출력 2 
1
예제 입력 3 
4
예제 출력 3 
1
예제 입력 4 
11
예제 출력 4 
3
*/