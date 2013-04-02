package l7;

public class PQueueGeneric<AnyClass> extends Comparable<AnyClass> {
	private AnyClass[] A;
	private int currentSize;
	
	public PQueueGeneric(int n){
		A = (AnyClass[]) new Comparable[n+1];
		currentSize = 0;
	}
	
	public void insert(AnyClass d){
		currentSize = currentSize + 1;
		int i = currentSize;
		while ((i > 1) && (d.compareTo(A[i/2]) < 0)){
			A[i] = A[i/2];
			i = i/2;
		}
		A[i] = d;
	}
	
	public AnyClass min(){
		return A[1];
	}
	
	public AnyClass deleteMin(){
		A[1] = A[currentSize];
		currentSize = currentSize - 1;
		int i = 1;
		while (2*i <= currentSize){
			int m = 2*i;
			if ((2*i + 1 <= currentSize) && (A[2*i+1] <= A[2*i])) m = 2*i+1;
			if (A[m] >= A[i]) return;
			double tmp = A[m];
			A[m] = A[i];
			A[i] = tmp;
			i = m;
		}
	}
	
	public boolean isFull(){
		return (currentSize == A.length);
	}
	
	public boolean isEmpty(){
		return (currentSize == 0);
	}
}