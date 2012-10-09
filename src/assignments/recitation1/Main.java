package recitation2;

import java.util.Scanner;

public class Main {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		MathClass[] mathArray = new MathClass[n];
		NumberHolder[] numArray = new NumberHolder[n];
		Factorial[] factorialArray = new Factorial[n];
		Sum[] sumArray = new Sum[n];
		
		for(int i = 0; i < n; i++) {
			mathArray[i] = new MathClass();
			mathArray[i].setNumber(i);
		}
		
		for(int j = 0; j < n; j++) {
			numArray[j] = mathArray[j];
			factorialArray[j] = mathArray[j];
			sumArray[j] = mathArray[j];
		}
		
		for(int k = 0; k < n; k++) {
			System.out.println("Number: " + numArray[k].getNumber());
			System.out.println("Factorial: " + factorialArray[k].getFactorial());
			System.out.println("Sum: " + sumArray[k].getSum());
		}
	}
}