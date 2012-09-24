package recitation3;

public class MathClass {
	int n;
	Rectangle sumRectangle; 
	Rectangle factorialRectangle;
	
	public void setNumber(int newnumber) {
		n = newnumber;
	}
	
	public int getNumber() {
		return n;
	}
	
	public int getsum() {
		return (n * (n + 1)) / 2;
	}
	
	public int getfactorial() {
		return myfactorial(n);
	}
	
	public Rectangle getsumRectangle() {
		return sumRectangle = new CartesianRectangle(10,0,20,getsum());
	}
	
	public Rectangle getfactorialRectangle() {
		return factorialRectangle = new CartesianRectangle(40,0,20,getfactorial());
	}
	
	private int myfactorial(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return number * myfactorial(number - 1);
		}
	}
}