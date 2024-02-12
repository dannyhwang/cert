package codingtest.chapter07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CT68644 {

	public static void main(String[] args) {
		int[] numbers = {2,1,3,4,1};
		
		CT68644 test = new CT68644();
		int[] answer = test.solution(numbers);
		System.out.println(Arrays.toString(answer));
	}

    public int[] solution(int[] numbers) {
        int[] answer = {};
        Set<Integer> set = new HashSet<>();
        
        for(int i=0; i<numbers.length; i++) {
        	for(int j=0; j<numbers.length; j++) {
        		if(i != j) {
        			set.add(numbers[i]+numbers[j]);
        		}
        	}
        }
        
        answer = set.stream().mapToInt(Integer::intValue).sorted().toArray();
        
        return answer;
    }
}