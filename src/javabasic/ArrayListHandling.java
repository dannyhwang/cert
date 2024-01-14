package javabasic;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListHandling {

	public static void main(String[] args) {
		// Class
		ArrayList<Student> al = new ArrayList<Student>();
		
		al.add(new Student("danny",1));
		al.add(new Student("jane",2));
		al.add(new Student("nate",3));
		
		// Print ArrayList
		for(Student s:al)
			System.out.println(s);
		
		al.sort(null);

		// Print ArrayList
		for(Student s:al)
			System.out.println(s);
		
		al.sort(Comparator.reverseOrder());

		// Print ArrayList
		for(Student s:al)
			System.out.println(s);
		
		// Integer
		ArrayList<Integer> al2 = new ArrayList<Integer>();
		
		al2.add(5);
		al2.add(2);
		al2.add(7);
		
		System.out.println(al2);
		
		al2.sort(null);
		
		System.out.println(al2);
		
		al2.sort(Comparator.reverseOrder());
		
		System.out.println(al2);		
	}
}

class Student implements Comparable<Student> {
	String name;
	int id;

	// Alt + Shift + S -> o
	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	// Alt + Shift + S -> s
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + "]";
	}

	@Override
	public int compareTo(Student o) {
		return this.id - o.id;
	}
}
