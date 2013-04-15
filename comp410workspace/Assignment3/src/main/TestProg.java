package main;

import java.util.Random;
import javax.swing.JOptionPane;
import dd_as_bst.DynamicDict;
//import dd_as_avl.DynamicDict;
//import dd_as_hashTable;

public class TestProg {
	public static void main(String[] args){
		String S = JOptionPane.showInputDialog("How many inserts? - ");
		int N = Integer.parseInt(S);
		long time1 = System.nanoTime();
		DynamicDict D = new DynamicDict();
		Random r = new Random();
		// These are for RANDOMLY-GENERATED data
		// for (int i=0; i < N; i++) D.insert(r.nextInt(2 * N), r.nextInt(2 * N));
		// for (int i=0; i < N/2; i++) D.remove(r.nextInt(2 * N));
		// These are for SORTED data
		for (int i=0; i < N; i++) D.insert(i, i);
		for (int i=0; i < N/2; i++) D.remove(r.nextInt(N));
		System.out.printf("Tree count is %d\n", D.count());
		long time2 = System.nanoTime();
		System.out.println((time2 - time1) / 1000000);
	}
}