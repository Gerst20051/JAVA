package l3;

public class IntegerStack2 {	
	private class Node{
		int d;
		Node next;
		public Node(int i, Node n){//Constructor for Node
			d = i;
			next = n;
		}	
	}
	private Node top;
	private int maxSize;
	private int currentSize;
	public IntegerStack2 (int n){//Constructor
		top = null;
		maxSize = n;
		currentSize = 0;
	}
	public void push (int i){//Precond: not full
		if (currentSize < maxSize) 
			top = new Node(i,top);
		currentSize += 1;
	}
	public int pop(){//Precond: not empty
		int tmp = top.d;
		top = top.next;
		currentSize -= 1;
		return tmp;
	}
	public boolean isFull(){return (currentSize == maxSize);}
	public boolean isEmpty(){return (currentSize == 0);}
	public void print(){
		for (Node tmp = top; tmp != null; tmp = tmp.next)
			System.out.println("  " + tmp.d);
	}
}