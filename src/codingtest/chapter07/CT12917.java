package codingtest.chapter07;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CT12917 {

	public static void main(String[] args) {
		String s = "Zbcdefg";
		
		CT12917 test = new CT12917();
		String answer = test.solution(s);
		System.out.println(answer);
	}
	
    public String solution(String s) {
        String answer = "";
        
        answer = s.chars()
        		  .boxed()
        		  .sorted((v1, v2)->v2-v1)
        		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        		  .toString();
        
        return answer;
    }
}