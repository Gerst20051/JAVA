package recitation2;

public class MathClass implements NumberHolder, Factorial, Sum {
	int n;

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
		return factorial;
	}
	
	public int getSum() {
		int sum = 0, i;
		for (i = n; i > 0; i--) {
			sum += i;
		}
		return sum;
	}
}