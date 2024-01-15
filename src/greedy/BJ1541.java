package greedy;

import java.util.Scanner;

/**
 * 백준 1541
 * Doit 036
 */
public class BJ1541 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String formular = sc.next();
		
		String[] splitByMinus = formular.split("-");
		
		int answer = 0;
		
		for(int i=0; i<splitByMinus.length; i++) {
			String[] splitByPlus = splitByMinus[i].split("[+]");
			for(int j=0; j<splitByPlus.length; j++) {
				if(i==0)
					answer += Integer.parseInt(splitByPlus[j]);
				else
					answer -= Integer.parseInt(splitByPlus[j]);
			}
		}
		System.out.println(answer);
	}
}