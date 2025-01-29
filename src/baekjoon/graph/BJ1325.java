package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1325 {
	
	public static int N, M;
	public static List<Integer>[] edgeList;
	public static boolean[] isVisited;
	public static int[] visitCount;
	
	public static void main(String[] args) throws IOException {
		init();
		
		for(int i=1; i<=N; i++) {
			isVisited = new boolean[N+1];
			
			int count = 0;
			Queue<Integer> queue = new LinkedList<>();
			queue.add(i);
			count++;
			
			while(!queue.isEmpty()) {
				int now = queue.poll();
				isVisited[now] = true;
				
				for(int next:edgeList[now]) {
					if(!isVisited[next]) {
						isVisited[next] = true;
						queue.add(next);
						count++;
					}
				}
			}
			
			visitCount[i] = count;
		}
		
		int maxValue = Arrays.stream(visitCount).max().getAsInt();
		
		for(int i=1; i<=N; i++) {
			if(visitCount[i] == maxValue)
				System.out.print(i + " ");
		}
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edgeList = new ArrayList[N+1];
		for(int i=0; i<=N; i++)
			edgeList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edgeList[b].add(a);
		}
		isVisited = new boolean[N+1];
		visitCount = new int[N+1];
	}
}

/**
 * public static List<List<Integer>> edgeList; 로 사용했을 때는 시간 초과 하였음
 * public static List<Integer>[] edgeList; 로 수정하고 통과 하였음(즉, 배열이 더 빠름)
 * 
 * BFS 부분 while문 내에서 isVisited[next] = true; 처리를 하지 않았을 때 시간 초과 하였음
 */

/*
백준 효율적인 해킹(1325)

문제
해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다. 
김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.
이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.
이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다. 
둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.

출력
첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.

예제 입력 1 
5 4
3 1
3 2
4 3
5 3
예제 출력 1 
1 2

*/