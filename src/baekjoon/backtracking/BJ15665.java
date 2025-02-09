package baekjoon.backtracking;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BJ15665 {
	public static int N, M;
	public static int[] answer, numbers;
	public static Set<String> answerSet;
	
	public static void main(String[] args) {
		init();
		bt(0);
		for(String ans:answerSet)
			System.out.println(ans);
	}
	
	public static void bt(int height) {
		if(height == M) {
			answerSet.add(Arrays.stream(answer).mapToObj(String::valueOf)
					            .collect(Collectors.joining(" ")));
			return;
		}
		
		for(int i=0; i<N; i++) {
			answer[height] = numbers[i];
			bt(height+1);
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
		answerSet = new LinkedHashSet<>();
		sc.close();
	}
}
