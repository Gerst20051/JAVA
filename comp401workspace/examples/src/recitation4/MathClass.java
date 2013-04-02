package recitation4;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class MathClass implements NumberHolder, Sum, Factorial {
	int n;

	Rectangle SumRec = new CartesianRectangle(20,30);
	Rectangle Fac = new CartesianRectangle(60,30);

	public MathClass(int input) {
		setNumber(input);
	}
	
	public void setNumber(int newNumber) {
		n = newNumber;
	}

	public int getNumber() {
		return n;
	}

	public int getSum() {
		int sum = 0;
		for (int i=1;i<=n;i++) {
			sum=sum+i;
		}
		SumRec.setHeight(sum);
		return sum;
	}

	public int getFactorial() {
		int fac = 1;
		for (int i=1;i<=n;i++){
			fac=fac*i;
		}
		Fac.setHeight(fac/4);
		return fac;
	}
	
	public Rectangle getSumRec() {
		return SumRec;
	}
	
	public Rectangle getTopFac() {
		return Fac;
	}
}