package baekjoon.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ12015 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        int N = Integer.parseInt(br.readLine());
        String numberStr = br.readLine();
        StringTokenizer st = new StringTokenizer(numberStr);
        
        int[] arr = new int[N];
        for(int i=0; i<N; i++)
        	arr[i] = Integer.parseInt(st.nextToken());
        
        // LIS 배열을 유지할 리스트
        List<Integer> lis = new ArrayList<>();
        
        for(int num:arr) {
        	if(lis.isEmpty() || lis.get(lis.size()-1) < num) {
        		// 현재 숫자가 LIS의 마지막 숫자보다 크다면 추가
                lis.add(num);
        	} else {
        		// 그렇지 않으면 LIS에서 num이 들어갈 위치를 이진 탐색으로 찾음
        		int idx = Collections.binarySearch(lis, num);
        		if(idx<0)
        			idx = -(idx+1); // 삽입할 위치
        		lis.set(idx, num); // 해당 위치를 현재 숫자로 갱신
        	}
        }
        
        // 
        System.out.println(lis.size());
	}
}

/*
가장 긴 증가하는 부분 수열 2(12015)

문제
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

예제 입력 1 
6
10 20 10 30 20 50
예제 출력 1 
4
*/
