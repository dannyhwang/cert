package tree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 백준 1991 
 * Doit 070
 */
public class BJ1991 {
	
	static Map<String, BNode> map;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		map = new HashMap<String, BNode>();
		
		for(int i=0; i<N; i++) {
			String name = Character.toString((char)(i + 'A'));
			map.put(name, new BNode(name));
		}
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			String parent = st.nextToken();
			String leftNode = st.nextToken();
			String rightNode =  st.nextToken();
			
			if(!leftNode.equals("."))
				map.get(parent).leftChild = map.get(leftNode);
			if(!rightNode.equals("."))
				map.get(parent).rightChild = map.get(rightNode);
		}
		
		preorder("A");
		System.out.println();
		inorder("A");
		System.out.println();
		postorder("A");
		System.out.println();
	}

	public static void preorder(String startName) {		
		BNode startNode = map.get(startName);
		System.out.print(startNode.name);
		if(startNode.leftChild != null)
			preorder(startNode.leftChild.name);
		if(startNode.rightChild != null)
			preorder(startNode.rightChild.name);
	}

	public static void inorder(String startName) {		
		BNode startNode = map.get(startName);
		if(startNode.leftChild != null)
			inorder(startNode.leftChild.name);
		System.out.print(startNode.name);
		if(startNode.rightChild != null)
			inorder(startNode.rightChild.name);
	}
	
	public static void postorder(String startName) {		
		BNode startNode = map.get(startName);
		if(startNode.leftChild != null)
			postorder(startNode.leftChild.name);
		if(startNode.rightChild != null)
			postorder(startNode.rightChild.name);
		System.out.print(startNode.name);
	}
}

class BNode {
	String name;
	BNode leftChild;
	BNode rightChild;

	public BNode(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "BNode [name=" + name + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
	}
}