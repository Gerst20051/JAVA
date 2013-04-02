package powerpoints;

import console.Console;

public class FactorialPrinter2 {
	public FactorialPrinter2() {
		testMethod();
	}
	
	static int f (int n) {
		int product = 1;
		while (n > 0) {
			product *= n;
			n -= 1;
		}
		return product;
	 }

	public static void testMethod() {
		while (true) {   // loop condition never false
			int n = Console.readInt();
			if (n < 0)
				break;
			System.out.println("factorial = " + f(n));
		}
	}
}