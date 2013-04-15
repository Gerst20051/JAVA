package l22;

public class MyArrayClass {
	int count;
	int[] A;
	int[] B;
	Double[] C;

	public MyArrayClass(int N) {
		count = 0;
		A = new int[N];
		B = new int[N];
		C = new Double[N];
	}

	private boolean isValid(int i) {
		return ((B[i] < count) && (A[B[i]] == i));
	}

	public void assign(int i, Double s) {
		if (isValid(i)) {
			C[i] = s;
			return;
		}
		A[count] = i;
		B[i] = count;
		count = count + 1;
		C[i] = s;
	}

	public Double retrieve(int i) {
		if (isValid(i))
			return C[i];
		return (0.0);
	}
}