package l14;

public class BinaryNode {
	BinaryNode root; // Initialized to null
	int count; // Initialized to 0
	int data;
	
	public void insert(int x){
		count = count + 1;
		root = insert(x, root, count);
	}

	private BinaryNode(int x, BinaryNode r, int n){
		if (n == 1) return new BinaryNode(x, null, null);
		int ell = (int) (Math.log(n)/ Math.log(2));
		if (n < (int) (Math.pow(2,ell) + Math.pow(2, ell-1))) { // In left subtree
			r.left = insert(x, r.left, n - (int) Math.pow(2, ell-1));
			if (r.data > r.left.data) {
				double tmp = r.data;
				r.data = r.left.data;
				r.left.data = tmp;
			}
		} else { // In right subtree
			r.left = insert(x, r.right, n - (int) Math.pow(2, ell));
			if (r.data > r.right.data) {
				double tmp = r.data;
				r.data = r.right.data;
				r.right.data = tmp;
			}
		}
		return r;
	}
	
	private BinaryNode insert(int x, BinaryNode root, int count) {
		return null;
	}
	
	public int min(){
		// PRE: not called on empty priority queue
		return root.data;
	}
	
	public void deleteMin(){
		// 1) Descend the tree to find the "last" node in the tree, and copy its value into the root
		// 2) Decrement count
		// 3) Starting at the root, recursively maintain the order property
		// 4) (May, but do not need to, actually delete the node — the current value of count tells us how much of the tree is "valid")
		
	}
}