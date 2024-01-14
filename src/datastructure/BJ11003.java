package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 백준 11003
 * Doit 010 
 */
public class BJ11003 {

	static int N, L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		Deque<Node> deque = new LinkedList<Node>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			Node now = new Node(i, Integer.parseInt(st.nextToken()));
			
			while(!deque.isEmpty() && deque.getLast().value > now.value) {
				deque.removeLast();
			}
		
			deque.addLast(now);

			if(deque.getFirst().index < i-L+1) {
				deque.removeFirst();
			}
			
			bw.write(deque.getFirst().value + " ");
		}
		bw.flush();
		bw.close();
	}
}

class Node {
	int index;
	int value;

	public Node(int index, int value) {
		super();
		this.index = index;
		this.value = value;
	}
}