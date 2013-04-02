package pq_as_sorted_array;

public class PQueue {
	double[] A;
	int count;
	
	public PQueue(int size){
		A = new double[size];
		count = 0;
	}
	
	public boolean isFull(){
		return count == A.length;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	
	public void insert(double x){ // PRE: not isFull()
		int i, j;
		for (i = 0; i < count && A[i] > x; i++);
		for (j = count; j > i; j--) A[j] = A[j-1];
		A[j] = x;
		count = count+1;			
	}
	
	public double min(){
		return A[count-1];
	}
	
	public double deleteMin(){
		count = count-1;
		return A[count];
	}	
}