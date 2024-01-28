package codingtest.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CT87377 {

	public static void main(String[] args) {
		
		//int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
		int[][] line = {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
		//int[][] line = {{1, -1, 0}, {2, -1, 0}};
		for(int i=0; i<line.length; i++)
			System.out.println(Arrays.toString(line[i]));
		
		CT87377 work = new CT87377();
		
		work.solution(line);
	}

    public String[] solution(int[][] line) {
    	
    	ArrayList<Point> al = new ArrayList<Point>();
    	
    	for(int i=0; i<line.length; i++) {
    		for(int j=i+1; j<line.length; j++) {
    			double a1 = line[i][0];
    			double b1 = line[i][1];
    			double c1 = line[i][2];
    			
    			double a2 = line[j][0];
    			double b2 = line[j][1];
    			double c2 = line[j][2];
    			
    			double x = (b1*c2 - b2*c1)/(a1*b2 - a2*b1);
    			double y = (a2*c1 - a1*c2)/(a1*b2 - a2*b1);
    			   			
    			if(x%1 == 0 && y%1 ==0)
    				al.add(new Point((long) x, (long) y));
    		}
    	}
    	
    	//long xMin = al.stream().map(Point::getX);
    	Stream<Long> xMax = al.stream().map(Point::getX);
    		
    	//xMin.forEach(num -> System.out.println("값 : "+num));
    	//System.out.println("MAX : " + xMax.max(Comparator.comparing(x -> x)).get());
    	
    	Stream<Long> xMin = al.stream().map(Point::getX);
    	//System.out.println("MIN : " + xMin.min(Comparator.comparing(x -> x)).get());
    	//System.out.println("MIN : " + xMin.max(null).get());
    	
    	Stream<Long> xPosition = al.stream().map(Point::getX).sorted();
    	List<Long> xList = xPosition.collect(Collectors.toList());
    	long x_min = xList.get(0);
    	long x_max = xList.get(xList.size()-1);
    	
    	Stream<Long> yPosition = al.stream().map(Point::getY).sorted();
    	List<Long> yList = yPosition.collect(Collectors.toList());
    	long y_min = yList.get(0);
    	long y_max = yList.get(yList.size()-1);
    	
    	
    	char[][] answerArray = new char[(int)(y_max-y_min+1)][(int)(x_max-x_min+1)];
    	
    	for(int i=0; i<answerArray.length; i++)
    		for(int j=0; j<answerArray[0].length; j++)
    			answerArray[i][j] = '.';
    	
    	for(Point p: al) {
    		answerArray[(int)((y_max-y_min)-(p.y-y_min))][(int)(p.x-x_min)] = '*';
    	}
    	
    	String[] result = new String[answerArray.length];

    	for(int i=0; i<answerArray.length; i++) {
   			result[i] = new String(answerArray[i]);
    		System.out.println(result[i]);
    	}
    	
        return result;
    }
}

class Point {
	long x;
	long y;
	public Point(long x, long y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public long getX() {
		return x;
	}
	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}
	public void setY(long y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}