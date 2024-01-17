package graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 백준 1516
 * Doit 054 
 *
 */
public class BJ1516 {

	static int N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		Building[] B = new Building[N+1];
		
		// 데이터 초기화
		for(int i=1; i<=N; i++)
			B[i] = new Building(i);
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st  = new StringTokenizer(sc.nextLine());
			B[i].buildTime = Integer.parseInt(st.nextToken());
			B[i].minReqTime = B[i].buildTime; 
			while(true) {
				int required =  Integer.parseInt(st.nextToken());
				if( required == -1) {
			 		break;
				} else {
					B[required].next.add(i);
					B[i].indegree++;
				}				
			}
		}
		
		//위상정렬
		Queue<Building> queue = new LinkedList<Building>();
		
		for(int i=1; i<=N; i++) {
			if(B[i].indegree == 0)
				queue.add(B[i]);
		}
		
		while(!queue.isEmpty()) {
			Building now = queue.poll();
			for(int next:now.next) {
				B[next].indegree--;
				B[next].minReqTime = Math.max(now.minReqTime + B[next].buildTime, B[next].minReqTime);
				if(B[next].indegree==0)
					queue.add(B[next]);
			}
		}
				
		//for(int i=1; i<=N; i++)
		//	System.out.println(B[i]);
		
		for(int i=1; i<=N; i++)
			System.out.println(B[i].minReqTime);
	}
}

class Building {
	int number;
	int buildTime;
	int indegree;
	int minReqTime;
	ArrayList<Integer> next = new ArrayList<Integer>();
	
	public Building(int number) {
		super();
		this.number = number;
	}

	@Override
	public String toString() {
		return "Building [number=" + number + ", buildTime=" + buildTime + ", indegree=" + indegree + ", minReqTime="
				+ minReqTime + ", next=" + next + "]";
	}
}