package l13;

public class ExplicitHeap {
	// PRIORITY QUEUES IMPLEMENTED AS EXPLICIT HEAPS
	// This is how we would insert into binary trees in a manner that maintains the shape property.
	BinaryNode root; // Initialized to null
	int count; // Initialized to 0
	
	public void insert(int x){
		count = count + 1;
		root = insert(x, root, count);
	}

	private BinaryNode BinaryNode(int x, BinaryNode r, int n){
		if (n == 1) return new BinaryNode(x, null, null);
		int ell = (int) (Math.log(n)/ Math.log(2));
		if (n < (int) (Math.pow(2,ell) + Math.pow(2, ell-1)))
			r.left = insert(x, r.left, n - (int) Math.pow(2, ell-1));
		else
			r.left = insert(x, r.right, n - (int) Math.pow(2, ell));
		return r;
	}
	
	private BinaryNode insert(int x, BinaryNode root, int count) {
		return null;
	}
}