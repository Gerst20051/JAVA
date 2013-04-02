package main;

import java.util.Random;
import javax.swing.JOptionPane;
//import pq_as_heap_explicit.PQueue;
//import pq_as_sorted_array.PQueue;
import pq_as_heap_array.PQueue;

public class TestProg {
	public static void main(String[] args){
		String S = JOptionPane.showInputDialog("Input the PQ size:");
		int N = Integer.parseInt(S);
		long time1 = System.nanoTime();
		PQueue P = new PQueue(N);
		Random r = new Random();
		while (!P.isFull()) P.insert(r.nextDouble());
		while (!P.isEmpty()) P.deleteMin();
		long time2 = System.nanoTime();
		System.out.println((time2 - time1) / 1000000);
	}
}