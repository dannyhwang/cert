package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BJ11057 {
	public static int N;
	public static int[] dp;
	
	public static void main(String[] args) {
		init();
		
		Arrays.fill(dp, 1);
		for(int i=2; i<=N; i++) {
			for(int j=0; j<=9; j++) {
				for(int k=j+1; k<=9; k++) {
					dp[j] += dp[k]%10007;
				}
			}
		}
		System.out.println(Arrays.stream(dp).sum()%10007);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[10];
		sc.close();
	}
}

/*
백준 오르막 수(11057)

문제
오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.

예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.

수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.

입력
첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.

출력
첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.

예제 입력 1 
1
예제 출력 1 
10
예제 입력 2 
2
예제 출력 2 
55
예제 입력 3 
3
예제 출력 3 
220
*/