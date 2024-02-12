package codingtest.chapter07;

import java.util.Arrays;

public class CT42748 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
		
		CT42748 test = new CT42748();
		int[] ans = test.solution(array, commands);
		
		System.out.println(Arrays.toString(ans));
	}
	
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		
		for(int i=0; i<commands.length; i++) {
			int start = commands[i][0]-1;
			int end = commands[i][1];
			int position = commands[i][2]-1;
			
			int[] sub = Arrays.copyOfRange(array, start, end);
			Arrays.sort(sub);
			answer[i] = sub[position];
		}
		
		return answer;
	}
}