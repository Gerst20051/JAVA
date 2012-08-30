package powerpoints;

import console.Console;

public class FactorialPrinter {
	
	public FactorialPrinter(String[] args) {
		main(args);
	}
	static int f (int n) {
		int product = 1;
		while (n > 0) {
			product *= n;
			n -= 1;
		}
		return product;
	 }

	public static void main (String[] args) {
		while (true) {   // loop condition never false
			int n = Console.readInt();
			if (n < 0)
				break;
			System.out.println("factorial = " + f(n));
		}
	}
}