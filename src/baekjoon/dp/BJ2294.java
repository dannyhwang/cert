package baekjoon.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BJ2294 {
	public static int N, K;
	public static Set<Integer> numberSet;
	public static int[] answer;
	
	public static void main(String[] args) {
		init();
		
		for(int number:numberSet) {
			for(int i=1; i<=K; i++) {
				if(i>=number) {
					answer[i] = Math.min(answer[i], answer[i-number]+1);
				}
			}
			//System.out.println(Arrays.toString(answer));
		}
		
		if(answer[K] == 10000001)
			System.out.println("-1");
		else
			System.out.println(answer[K]);
	}
	
	public static void init() {
		 Scanner sc = new Scanner(System.in);
		 N = sc.nextInt();
		 K = sc.nextInt();
		 numberSet = new HashSet<>();
		 for(int i=0; i<N; i++)
			 numberSet.add(sc.nextInt());
		 answer = new int[K+1];
		 Arrays.fill(answer, 10000001);
		 answer[0] = 0;
		 sc.close();
	}
}


/*
백준 동전2(2294)

문제
n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 
그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.

입력
첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.

출력
첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.

예제 입력 1 
3 15
1
5
12
예제 출력 1 
3
-----
18 52
25
15 
17 
14
8 
30 
27 
11 
20 
6 
13 
13 
8 
9 
19 
15 
29 
30
--
2
*/