package l15;

public class DD {
	private BN root;
	
	public DD(){
		root = null;
	}
	
	public boolean isEmpty(){
		return (root == null);
	}
	
	public boolean find(int k){
		return find(k, root);
	}
	
	private boolean find(int k, BN n){
		if (n == null) return false;
		if (k == n.data) return true;
		if (k < n.data) return find(k, n.left);
		return find(k, n.right);
	}
	
	public void insert(int k){
		root = insert(k, root);
	}
	
	private BN insert(int k, BN n){
		if (n == null) return new BN(k,null,null);
		if (k <= n.data) n.left = insert(k, n.left);
		else n.right = insert(k, n.right);
		return n;
	}
	
	public int findMax(){ // Precond: n not null
		return findMax(root).data; // The private method returns a BN rather than its data
	}
	
	private BN findMax(BN n){ // Returns BN, not data, 'cause needed for "remove"
		if (n.right == null) return n;
		return findMax(n.right);
		// Non-recursive version is easy to write...
	}
	
	public int findMin(){
		// Analogous to above
		return 0;
	}
	
	public void remove(){
		// Three cases
		// 1) remove a leaf
		// 2) remove a node with one child
		// 3) remove a node with two children
	}
}