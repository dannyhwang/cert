package baekjoon.lis;

import java.util.Scanner;

public class BJ11055 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int n = sc.nextInt(); // 수열의 크기
        int[] arr = new int[n]; // 수열 A
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // dp 배열 초기화: dp[i]는 arr[i]를 포함하는 증가하는 부분 수열의 최대 합
        int[] dp = new int[n];
        System.arraycopy(arr, 0, dp, 0, n); // 초기값은 각 원소 자체의 값
        
        // 동적 프로그래밍
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) { // 증가하는 조건
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }

        // dp 배열에서 최대값 찾기
        int maxSum = 0;
        for (int value : dp) {
            maxSum = Math.max(maxSum, value);
        }

        // 결과 출력
        System.out.println(maxSum);
    }
}

/*
(참고) https://tosuccess.tistory.com/169
"System" 클래스에는 배열을 복사하는 "arraycopy" 메소드가 존재한다. 
자바의 배열의 값을 복사할 경우 사용하는데 사용 방법은 다음과 같다.

System.arraycopy(src, srcPos, dest, destPos, length);
src - 원본 배열
srcPos - 원본 배열의 복사 시작 위치
dest - 복사할 배열
destPost - 복사할 배열의 복사 시작 위치
length - 복사할 요소의 개수
*/

/*
가장 큰 증가하는 부분 수열(11055)

문제
수열 A가 주어졌을 때, 그 수열의 증가하는 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 합이 가장 큰 증가하는 부분 수열은 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 이고, 합은 113이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 합이 가장 큰 증가하는 부분 수열의 합을 출력한다.

예제 입력 1 
10
1 100 2 50 60 3 5 6 7 8
예제 출력 1 
113
*/
