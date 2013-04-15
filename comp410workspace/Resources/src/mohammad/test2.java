package mohammad;

/* Testing all methods. */

import java.io.*;
import java.lang.*;

public class test2 {
	// generate a 6 digit random number between low and high as a string. Note,
	// for example, 45 will return as "000045", and 450 as "000450".
	// Expect this method to do strange things if you give it low and high that
	// are NOT in 0 to 999999 range or high is NOT larger than low.
	public static String rand(int low, int high) {
		int r = low + (int) (Math.random() * (float) (high - low + 1));
		if (r < 10)
			return "00000" + String.valueOf(r);
		if (r < 100)
			return "0000" + String.valueOf(r);
		if (r < 1000)
			return "000" + String.valueOf(r);
		if (r < 10000)
			return "00" + String.valueOf(r);
		if (r < 100000)
			return "0" + String.valueOf(r);
		return String.valueOf(r);
	}

	public static void main(String[] arg) {
		Btree l = new Btree();
		// add 100 random numbers.
		System.out
				.println("\nAdding 100 randomly generated elements with key in the range 10-110");
		for (int i = 0; i <= 99; i++)
			l.add(new elementType(rand(10, 110), rand(1000, 500000)));
		l.dump();
		// perform 100 random operations (add, remove,find, and update)
		System.out
				.println("\nPerform 10 random operations (add, remove,find, and update)");
		for (int i = 0; i <= 9; i++) {
			elementType s = new elementType(rand(10, 110), null);// random
																	// string
																	// for
																	// remove or
																	// find.
			// Random element for add
			elementType e = new elementType(rand(10, 110), rand(1000, 500000));
			switch ((int) (Math.random() * 4.0)) {// random value between 0 and
													// 3
			case 0: // add
				System.out.println("\nadd: " + e);
				l.add(e);
				break;
			case 1: // remove
				System.out.println("\nRemove: " + s.key_);
				l.remove(s);
				break;
			case 3: // find
				System.out.println("\nFind: " + s.key_);
				e = (elementType) l.find(s);
				if (e != null)
					System.out.println("\tThe element  " + e + " was found");
				else
					System.out
							.println("\tThe key " + s.key_ + " was not found");
				break;
			}
		}
		l.dump();
	}
}

class elementType implements Comparable {
	String key_;
	String othervalue_;

	elementType(String k, String o) {
		key_ = k;
		othervalue_ = o;
	}

	public boolean equals(Object c) {
		if (c.getClass().getName().equals("elementType"))
			return key_.equals(((elementType) c).key_);
		else {
			System.err.println("equals: Failed to cast as elementType");
			System.err.println("The object class name was:"
					+ c.getClass().getName());
			System.exit(-1);
		}
		return false;
	}

	public int compareTo(Object c) {
		if (c.getClass().getName().equals("elementType"))
			return key_.compareTo(((elementType) c).key_);
		else {
			System.err
					.println("compareTo: Failed to cast as  element as elementType");
			System.err.println("The object class name was:"
					+ c.getClass().getName());
			System.exit(-1);
		}
		return 0;
	}

	public String toString() {
		return "key: " + key_ + "other: " + othervalue_;
	}
}
