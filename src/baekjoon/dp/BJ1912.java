package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1912 {
	public static int N;
	public static int[] numbers;
	public static int[] dp;
	
	public static void main(String[] args) throws IOException {
		init();
		for(int i=1; i<=N; i++) {
			dp[i] = Math.max(dp[i-1]+numbers[i], dp[i]);
		}
		
		int answer = Arrays.stream(dp).max().getAsInt();
		
		if(answer==0) { // 주어진 수가 모두 음수인 경우 처리
			Arrays.sort(numbers);
			System.out.println(numbers[N-1]);
		}
		else
			System.out.println(answer);
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		dp = new int[N+1];
		Arrays.fill(dp, 0);
		br.close();
	}
}


/*
백준 연속합(1912)

문제
n개의 정수로 이루어진 임의의 수열이 주어진다. 
우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 
단, 수는 한 개 이상 선택해야 한다.

예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 
여기서 정답은 12+21인 33이 정답이 된다.

입력
첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 
수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

출력
첫째 줄에 답을 출력한다.

예제 입력 1 
10
10 -4 3 1 5 6 -35 12 21 -1
예제 출력 1 
33
예제 입력 2 
10
2 1 -4 3 4 -4 6 5 -5 1
예제 출력 2 
14
예제 입력 3 
5
-1 -2 -3 -4 -5
예제 출력 3 
-1
*/