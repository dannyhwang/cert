package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 1931
 * Doit 035
 */
public class BJ1931 {

	static int N;
	static ArrayList<Meeting> al = new ArrayList<Meeting>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			al.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));	
		}
		
		al.sort(null);
		
		int lastTime = 0;
		int count = 0;
		
		for(Meeting m:al) {
			if(m.startTime >= lastTime) {
				count++;
				lastTime = m.endTime;
			}
		}
		System.out.println(count);
	}
}

class Meeting implements Comparable<Meeting> {
	int startTime;
	int endTime;
	
	public Meeting(int startTime, int endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Meeting o) {
		if(this.endTime != o.endTime)
			return this.endTime - o.endTime;
		else
			return this.startTime - o.startTime;
	}
}