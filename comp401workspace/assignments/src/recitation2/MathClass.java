package recitation2;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class MathClass {
	int n;
	Rectangle factorial;
	Rectangle sum;
	
	public void setNumber(int input) {
		n = input;
	}
	
	public int getNumber() {
		return n;
	}

	public int getFactorial() {
		return calcFactorial(n);
	}
	
	private int calcFactorial(int n) {
		if (2 > n) return 1;
		int factorial = 1, i;
		for (i = 2; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}
	
	public int getSum() {
		int sum = 0, i;
		for (i = n; i > 0; i--) {
			sum += i;
		}
		return sum;
	}
	
	public Rectangle getsumRectangle() {
		return sum = new CartesianRectangle(10,0,20,getSum());
	}
	
	public Rectangle getfactorialRectangle() {
		return factorial = new CartesianRectangle(40,0,20,getFactorial());
	}
}