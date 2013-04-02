package vipQueueLL;

public class PQueueLL {
	private class Node {
		int d;
		Node next;
		public Node(int i, Node n) {
			d = i;
			next = n;
		}
	}
	private Node front;
	private Node back;
	private Node tmp;
	private int size;
	private int count;
	
	public PQueueLL(int initSize) {
		front = null;
		back = null;
		size = initSize;
		count = 0;
	}
	
	public boolean isFull() {
		return (count == size);
	}
	
	public boolean isEmpty() {
		return (front == null);
	}
	
	public int getCount() {
		return count;
	}
	
	public void addItem(int i) { // Precond: not full
		if (count == 0) {
			back = new Node(i, null);
			front = back;
		} else {
			tmp = new Node(i, null);
			back.next = tmp;
			back = tmp;
		}
		count++;
	}
	
	public void addItemVip(int i) { // Precond: not full
		// if (!isFull());
		// if (count < size)
		front = new Node(i, front);	
		count++; 
	}
	
	public int removeItem(int position) { // Precond: not empty
		// if (!isEmpty());
		count--;
		int tmp = front.d;
		front = front.next;
		return tmp;
	}
	
	public int removeFirstItem() {
		return removeItem(0);
	}
	
	public int removeLastItem() {
		return removeItem(getCount());
	}
}