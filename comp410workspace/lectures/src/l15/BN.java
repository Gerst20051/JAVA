package l15;

public class BN {
	int data; // The actual object stored...
	BN left, right;
	
	public BN(int x, BN l, BN r){ // Constructor
		data = x;
		left = l;
		right = r;
	}
}