package pq_as_heap_array;

public class PQueue {
	double[] A;
	int count;
	
	public PQueue(int size){
		A = new double[size+1];
		A[0] = Double.NEGATIVE_INFINITY;
		count = 0;
	}
	
	public boolean isFull(){
		return count == A.length-1;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	
	public void insert(double x){ // PRE: not isFull()
		count = count+1;
		int i = count;
		while (x < A[i/2]) {
			A[i] = A[i/2];
			i = i/2;
		}
		A[i] = x;
	}
	
	public double min(){
		return A[1];
	}
	
	public double deleteMin(){
		double tmp1 = A[1];
		A[1] = A[count];
		count = count-1;
		int i = 1;
		while (2*i <= count) {
			int m = 2*i;
			if ((2*i+1 <= count) && (A[2*i+1] <= A[2*i])) m = 2*i+1;
			if (A[m] >= A[i]) return tmp1;
			//A[m] < A[i]
			double tmp = A[m];
			A[m] = A[i];
			A[i] = tmp;
			i = m;
		}
		return tmp1;
	}
}