package baekjoon.string;

import java.util.Scanner;

public class BJ11720 {

	public static int N;
	public static String numbers;
	
	public static void main(String[] args) {
		init();
		
		int number = numbers.chars() // 문자열의 각 문자를 IntStream으로 변환
				     	    .map(c->c-'0') // 각 문자를 정수로 변환합니다. '0'의 아스키 값을 빼는 방식으로 변환합니다.
				            .sum(); // 합 계산
		
		System.out.println(number);
		
		/*
		[다른 방법] numbers.chars()
		                .map(Character::getNumericValue)
		                .sum();
		*/
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = sc.next();
		sc.close();
	}
}

/*
숫자의 합(11720)

문제
N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 숫자의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄에 숫자 N개가 공백없이 주어진다.

예제 입력
5
54321
-----
15
*/