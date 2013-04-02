package l7;

public class HeapSort {
	public static void main(String[] args) {
		double X[] = new double[10];
		PQueue Q = new PQueue(X.length);
		for (int i=0; i < X.length; i++) Q.insert(X[i]);
		for (int i=0; i < X.length; i++) {
			X[i] = Q.min();
			Q.deleteMin();
		}
	}
}