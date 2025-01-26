package baekjoon.backtracking;

import java.util.Scanner;

public class BJ15649 {
	
	public static int N, M;
	public static int[] numbers; // 선택 가능한 숫자의 유형이 다양한 경우에도 처리하기 위해 배열로 선언
	public static int[] ans; // 정답 수열을 저장할 배열
	public static boolean[] isVisited; // 방문 여부를 체크할 배열
	
	public static void main(String[] args) {
		init();
		permutation(0);
	}
	
	public static void permutation(int height) {
		// 종료 조건 : 수열의 길이가 M에 도달한 경우
		if(height == M) {
			for(int num:ans)
				System.out.print(num + " ");
			System.out.println();
			return;
		}
		
		// 1~N 까지의 숫자를 순회
		for(int i=0; i<N; i++) {
			if(!isVisited[i]) { // 아직 방문하지 않은 숫자인 경우
				isVisited[i] = true; // 방문처리
				ans[height] = numbers[i]; // 현재 숫자를 수열에 추가
				permutation(height + 1); // 다음 단계로 이동
				isVisited[i] = false; // 백트래킹 : 방문 해제
			}
		}
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[N];
		for(int i=0; i<N; i++)
			numbers[i] = i+1;
		ans = new int[M];
		sc.close();
		isVisited = new boolean[N];
	}
}

/*
N과 M(1)(15649)

문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제 입력 1 
3 1
예제 출력 1 
1
2
3
예제 입력 2 
4 2
예제 출력 2 
1 2
1 3
1 4
2 1
2 3
2 4
3 1
3 2
3 4
4 1
4 2
4 3

*/