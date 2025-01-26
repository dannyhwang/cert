package baekjoon.priorityqueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ1655_bs {
	public static int N;
	public static int[] numbers;
	public static List<Integer> numberList;
	
	public static void main(String[] args) throws IOException {
        // 입력을 빠르게 처리하기 위한 BufferedReader와 출력용 BufferedWriter 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        N = Integer.parseInt(br.readLine());
        // ArrayList로 binary search 방법으로 구현한 풀이
        numberList = new ArrayList<>();
        
		for(int i=0; i<N; i++) {
			int number = Integer.parseInt(br.readLine());
			
			int idx = Collections.binarySearch(numberList, number);
			if(idx<0) idx = -(idx+1);
			numberList.add(idx, number);
			int midIdx = (numberList.size()-1)/2;
			bw.write(numberList.get(midIdx) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}


/*
문제
백준이는 동생에게 "가운데를 말해요" 게임을 가르쳐주고 있다. 백준이가 정수를 하나씩 외칠때마다 동생은 지금까지 백준이가 말한 수 중에서 중간값을 말해야 한다. 만약, 그동안 백준이가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.

예를 들어 백준이가 동생에게 1, 5, 2, 10, -99, 7, 5를 순서대로 외쳤다고 하면, 동생은 1, 1, 2, 2, 2, 2, 5를 차례대로 말해야 한다. 백준이가 외치는 수가 주어졌을 때, 동생이 말해야 하는 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 백준이가 외치는 정수의 개수 N이 주어진다. N은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수이다. 그 다음 N줄에 걸쳐서 백준이가 외치는 정수가 차례대로 주어진다. 정수는 -10,000보다 크거나 같고, 10,000보다 작거나 같다.

출력
한 줄에 하나씩 N줄에 걸쳐 백준이의 동생이 말해야 하는 수를 순서대로 출력한다.

예제 입력
7
1
5
2
10
-99
7
5
예제 출력
1
1
2
2
2
2
5
*/
