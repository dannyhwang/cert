package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ14502 {

	public static int N, M;
	public static int[][] lab;
	public static int[][] tempLab;
	public static int[] dx = {0,0,-1,1};
	public static int[] dy = {1,-1,0,0};
	public static int maxSafeArea = 0;
	
	public static void main(String[] args) {
		init();
		
		chooseThree(0);
		System.out.println(maxSafeArea);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		lab = new int[N][M];
		tempLab = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0 ; j<M; j++) {
				int a = sc.nextInt();
				lab[i][j] = a;
			}
		}
		sc.close();
	}
	
	// 벽을 3개 세우는 모든 경우의 수를 시도
	public static void chooseThree(int depth) {
		if(depth == 3) {
			bfs();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(lab[i][j] == 0) {
					lab[i][j] = 1;
					chooseThree(depth + 1);
					lab[i][j] = 0;
				}
			}
		}
	}
	
	// 바이러스 퍼트리기(BFS)
	public static void bfs() {
		for(int i=0; i<N; i++)
			System.arraycopy(lab[i], 0, tempLab[i], 0, M);
		
		Queue<int[]> queue = new LinkedList<>();
		
		// tempLab 배열에 현재 상태 복사
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(tempLab[i][j]==2)
					queue.add(new int[] {i,j});
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int y = current[0];
			int x = current[1];
			
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx >= 0 && ny >=0 && nx < M && ny < N) {
					if(tempLab[ny][nx] == 0) {
						tempLab[ny][nx] = 2;
						queue.add(new int[] {ny, nx});
					}
				}
			}
		}
		
		calculateSafeArea();
	}
	
	// 안전 영역 계산
	public static void calculateSafeArea() {
		int safeArea = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempLab[i][j] == 0) {
					safeArea++;
				}
			}
		}
		
		maxSafeArea = Math.max(maxSafeArea, safeArea);
	}
}

/*
백준 연구소(14502)

문제
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 

일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.

2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.

2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.

2 1 0 0 1 1 0
1 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 1 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
바이러스가 퍼진 뒤의 모습은 아래와 같아진다.

2 1 0 0 1 1 2
1 0 1 0 1 2 2
0 1 1 0 1 2 2
0 1 0 0 0 1 2
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.

연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.

빈 칸의 개수는 3개 이상이다.

출력
첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

예제 입력 1 
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
예제 출력 1 
27
예제 입력 2 
4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2
예제 출력 2 
9
*/