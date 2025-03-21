package baekjoon.backtracking;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BJ15663 {
	public static int N, M;
	public static int[] answer, numbers;
	public static boolean[] isVisited;
	public static Set<String> answerSet;
	
	public static void main(String[] args) {
		init();
		bt(0);
		for(String ans:answerSet)
			System.out.println(ans);
	}
	
	public static void bt(int depth) {
		if(depth == M) {
			answerSet.add(Arrays.stream(answer)
					            .mapToObj(String::valueOf)
					            .collect(Collectors.joining(" ")));
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			answer[depth] = numbers[i];
			bt(depth+1);
			isVisited[i] = false;
		}
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		answer = new int[M];
		numbers = new int[N];
		for(int i=0; i<N; i++)
			numbers[i] = sc.nextInt();
		Arrays.sort(numbers);
		isVisited = new boolean[N];
		answerSet = new LinkedHashSet<>();
		sc.close();
	}
}

/*
백준 N과 M (9)(15663)

문제
N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

N개의 자연수 중에서 M개를 고른 수열
입력
첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제 입력 1 
3 1
4 4 2
예제 출력 1 
2
4
예제 입력 2 
4 2
9 7 9 1
예제 출력 2 
1 7
1 9
7 1
7 9
9 1
9 7
9 9
*/