package baekjoon.lis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ14002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0;i<N; i++)
			arr[i] = sc.nextInt();
		sc.close();
		
		// dp 배열과 이전 인덱스를 저장할 prev 배열 초기화
		int[] dp = new int[N];
		int[] prev = new int[N];
		int maxLength = 0;
		int endIndex = 0;
		
		// LIS 계산
		for(int i=0; i<N; i++) {
			dp[i] = 1; // 자기 자신만 포함한 길이는 최소 1
			prev[i] = -1;  // 이전 요소가 없음을 -1로 표시
			
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j] && dp[i] < dp[j]+1) {
					dp[i] = dp[j] + 1;
					prev[i] = j; // 이전 인덱스 저장
				}
			}
			
			// 최대 길이와 끝 인덱스 갱신
			if(dp[i] > maxLength) {
				maxLength = dp[i];
				endIndex = i;
			}
		}
		
		// 결과 수열 추적
		List<Integer> answer = new ArrayList<>();
		while(endIndex != -1) {
			answer.add(0, arr[endIndex]);
			endIndex = prev[endIndex];
		}
		
		// 결과 출력
		System.out.println(maxLength);
		for(int num:answer)
			System.out.print(num + " ");
	}
}

/*
가장 긴 증가하는 부분 수열 4(14002)

문제
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

둘째 줄에는 가장 긴 증가하는 부분 수열을 출력한다. 그러한 수열이 여러가지인 경우 아무거나 출력한다.

예제 입력 1 
6
10 20 10 30 20 50
예제 출력 1 
4
10 20 30 50
*/
