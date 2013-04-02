package l7;

public class Tester {
	public static void main(String[] args) {
		int n = 5;
		PQueue Q = new PQueue(n);
		AClass x = new AClass(3, "zero");
		Q.insert(x);
		x = new AClass(2, "two");
		Q.insert(x);
		x = new AClass(1, "one");
		Q.insert(x);
		while (!Q.isEmpty()){
			Q.deleteMin().print();
		}
	}
}