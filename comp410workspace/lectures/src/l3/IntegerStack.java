package l3;

public class IntegerStack {
	private int A[];
	private int maxSize;
	private int top;
	public  IntegerStack (int n){//Constructor
		A = new int[n];
		maxSize = n;
		top = 0;
	}
	public void push (int i){//Precond: not full
		A[top++] = i;
	}
	public int pop(){//Precond: not empty
		return A[--top];
	}
	public boolean isFull(){return (top == maxSize);}
	public boolean isEmpty(){return (top == 0);}
	public void print(){//For debugging --  not part of stack interface 
		for (int i=top-1; i>=0; i--)
			System.out.println("  " + A[i]);
	}
}