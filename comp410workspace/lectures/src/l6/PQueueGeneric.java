package l6;

public class PQueueGeneric<AnyClass> extends Comparable<AnyClass> {
	private AnyClass[] A;
	private int currentSize;
	public PQueueGeneric(int n){ //CONSTRUCTOR
		//A = new AnyClass[n+1];
		//The (commented out) statement above is illegal, since
		//Java does not allow instantiation of generic arrays
		A = (AnyClass[]) new Comparable[n+1]; //A hacked workaround
		//"Comparable" is a superclass; we’re then casting to AnyClass
		currentSize = 0;
	}
	public void insert(AnyClass d){//Precond: not full
		currentSize = currentSize + 1;
		int i = currentSize;
		while ((i > 1) && (d.compareTo(A[i/2]) < 0)){
			A[i] = A[i/2];
			i = i/2;
		}
		A[i] = d;
	}
	public AnyClass min(){//Precond: not empty
		return A[1];
	}
	public AnyClass deleteMin(){//Precond: not empty
		
	}
	public boolean isFull(){return (currentSize == A.length);}
	public boolean isEmpty(){return (currentSize == 0);}
}