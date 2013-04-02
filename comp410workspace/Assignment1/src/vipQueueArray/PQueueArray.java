package vipQueueArray;

public class PQueueArray {
	private int[] A;
	private int count;
	
	public PQueueArray(int size) {
		A = new int[size+1];
		count = 0;
	}
	
	public boolean isFull() {
		return (count == A.length-1);
	}
	
	public boolean isEmpty() {
		return (count == 0);
	}
	
	public void insert(int x) {
		A[count++] = x;
	}
	
	public void insertVip(int x) {
		for (int i = count++; 0 < i; i--) {
			A[i] = A[i-1];
		}
		A[0] = x;
	}
	
	public int popFirst() {
		int first = A[0];
		for (int i = 0, j = count--; i < j; i++) {
			A[i] = A[i+1];
		}
		return first;
	}
}