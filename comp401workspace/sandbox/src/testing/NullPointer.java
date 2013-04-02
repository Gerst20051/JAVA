package testing;

public class NullPointer {
	static int i;
	static int j;
	static String a;
	static String b;
	static Object x;
	static Object y;
	static NullPointer n;
	static NullPointer m;
	
	public static void main(String[] args) {
		System.out.println(i);
		System.out.println(a);
		System.out.println(x);
		System.out.println(n);
		//i = j;
		//a = b;
		//x = y;
		n = m;
		/*
		int i,j;
		i=j; // the local variable j may not have been initialized
		*/
	}
}