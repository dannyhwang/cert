package javabasic;

import java.util.Arrays;

public class ArraysTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a  = {2,0,1,6,9};
		
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		
		System.out.println(Arrays.binarySearch(a, 2));
		System.out.println(Arrays.binarySearch(a, 0));
		System.out.println(Arrays.binarySearch(a, 10));
	}

}
