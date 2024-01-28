package codingtest.chapter03;

import java.util.Scanner;

public class CT68645 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num = 1;
		int a = 0;
		int b = 0;
		
		int[][] triangle = new int[n][n];
		
		while(triangle[a][b] == 0 && 0<=a && a<n && 0<=b && b<n) {
			triangle[a][b] = num;
			num++;
			
		}
		
		
		

	}

}
