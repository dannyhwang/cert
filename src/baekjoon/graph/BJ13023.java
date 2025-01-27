package baekjoon.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ13023 {

	public static int N, M;
	public static List<Integer>[] edgeList;
	public static boolean[] isVisited;
	public static boolean isExist;
	
	public static void main(String[] args) {
		init();
		
		for(int i=0; i<N; i++) {
			dfs(i, 1);
			if(isExist)
				break;
		}
		
		System.out.println(isExist ? "1":"0");
	}
	
	public static void dfs(int now, int depth) {
		isVisited[now] = true;
		if(depth == 5) {
			isExist = true;
			return;
		}
		
		for(int next:edgeList[now]) {
			if(!isVisited[next]) {
				dfs(next, depth+1);
			}
		}
		isVisited[now] = false; // ☆☆☆
		return;
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		edgeList = new ArrayList[N];
		for(int i=0; i<N; i++) {
			edgeList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			edgeList[s].add(e);
			edgeList[e].add(s);
		}
		
		isVisited = new boolean[N];
		sc.close();
	}
}

/*
백준 ABCDE(13023)

문제
BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번부터 N-1번으로 번호가 매겨져 있고, 일부 사람들은 친구이다.

오늘은 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.

A는 B와 친구다.
B는 C와 친구다.
C는 D와 친구다.
D는 E와 친구다.
위와 같은 친구 관계가 존재하는지 안하는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 사람의 수 N (5 ≤ N ≤ 2000)과 친구 관계의 수 M (1 ≤ M ≤ 2000)이 주어진다.

둘째 줄부터 M개의 줄에는 정수 a와 b가 주어지며, a와 b가 친구라는 뜻이다. (0 ≤ a, b ≤ N-1, a ≠ b) 같은 친구 관계가 두 번 이상 주어지는 경우는 없다.

출력
문제의 조건에 맞는 A, B, C, D, E가 존재하면 1을 없으면 0을 출력한다.

예제 입력 1 
5 4
0 1
1 2
2 3
3 4
예제 출력 1 
1
*/