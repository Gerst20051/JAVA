package l7;

public class TesterGeneric {
	public static void main(String[] args) {
		int n = 5;
		PQueueGeneric<AClass> Q = new PQueueGeneric<AClass>(n);
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