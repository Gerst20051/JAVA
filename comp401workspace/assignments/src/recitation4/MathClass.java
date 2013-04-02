package recitation4;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class MathClass implements NumberHolder, Sum, Factorial {
	Rectangle SumRect = new CartesianRectangle(20,0);
	Rectangle FacRect = new CartesianRectangle(60,0);
	int n;
	
	public MathClass(int input) {
		setNumber(input);
	}
	
	public void setNumber(int input) {
		n = input;
	}

	public int getNumber() {
		return n;
	}

	public int getFactorial() {
		if (2 > n) return 1;
		int factorial = 1, i;
		for (i = 2; i <= n; i++) {
			factorial *= i;
		}
		FacRect.setHeight(factorial);
		return factorial;
	}
	
	public int getSum() {
		int sum = 0, i;
		for (i = n; i > 0; i--) {
			sum += i;
		}
		SumRect.setHeight(sum);
		return sum;
	}
	
	public Rectangle getFacRect() {
		return FacRect;
	}
	
	public Rectangle getSumRect() {
		return SumRect;
	}
}