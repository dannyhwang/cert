package baekjoon.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BJ11724 {

	public static int nodeCount, edgeCount;
	public static List<Short>[] edgeList;
	public static boolean[] isVisited;
	
	public static void main(String[] args) {
		init();
		int groupCount = 0;
		
		Queue<Short> queue = new LinkedList<>();
		for(short i=1; i<=nodeCount; i++) {
			if(!isVisited[i]) {
				queue.add(i);
				groupCount++;
			}
			
			while(!queue.isEmpty()) {
				short now = queue.poll();
				isVisited[now] = true;
				
				for(short next:edgeList[now]) {
					if(!isVisited[next]) {
						//아래 visit 처리를 하지 않았을 때는 메모리 초과로 실패함
						//즉, 메모리를 절약하기 위해 BFS queue 등록 시 방문처리 해야함
						isVisited[next] = true;
						queue.add(next);
					}
				}
			}
		}
		
		System.out.println(groupCount);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		nodeCount = sc.nextInt();
		edgeCount = sc.nextInt();
		edgeList = new ArrayList[nodeCount+1];
		for(int i=0; i<=nodeCount; i++) {
			edgeList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<edgeCount; i++) {
			short s = sc.nextShort();
			short e = sc.nextShort();
			edgeList[s].add(e);
			edgeList[e].add(s);
		}
		isVisited = new boolean[nodeCount+1];
		sc.close();
	}
}


/*
연결 요소의 개수(11724)

문제
방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

출력
첫째 줄에 연결 요소의 개수를 출력한다.

예제 입력
6 5
1 2
2 5
5 1
3 4
4 6
예제 출력
2
*/