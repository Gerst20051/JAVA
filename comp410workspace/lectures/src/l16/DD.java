package l16;

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
		if (n == null) return new BN(k, null, null);
		if (k <= n.data) n.left = insert(k, n.left);
		else n.right = insert(k, n.right);
		return n;
	}
	
	public int findMax(){ // PRE: not empty
		return findMax(root).data;
	}
	
	private BN findMax(BN n){
		if (n.right == null) return n;
		return findMax(n.right);
	}
	
	public int findMaxLoop(){ // PRE: not empty
		BN tmp = root;
		while (tmp.right != null) tmp = tmp.right;
		return tmp.data;
	}
	
	public int findMin(){
		return findMin(root);
	}
	
	private int findMin(BN n){
		if (n.left == null) return n.data;
		return findMin(n.left);
	}
	
	public int findMinLoop(){
		BN tmp = root;
		while (tmp.left != null) tmp = tmp.left;
		return tmp.data;
	}
	
	public void remove(int k){
		root = remove(k,root);
	}

	private BN remove(int k, BN n){
		if (n == null) return null;
		if (k < n.data) {
			n.left = remove(k, n.left);
			return n;
		}
		if (k > n.data) {
			n.right = remove(k, n.right);
			return n;
		}
		// k == n.key
		if ((n.left == null) || (n.right == null)) { // Zero or one child[ren]
			if (n.left == null)
				return n.right;
			else
				return n.left;
		}
		// 2 children
		int tmp = findMin(n.right);
		n.data = tmp;
		n.right = remove(tmp, n.right);
		// Could've done the above three lines as follows instead
		// int tmp = findMax(n.left);
		// n.data = tmp;
		// n.right = remove(tmp, n.right);
		return n;
	}
	
	public void inOrder(){
		inOrder(root);
	}
	
	private void inOrder(BN n){
		if (n == null) return;
		inOrder(n.left);
		visit(n); // E.g., print out n.data
		inOrder(n.right);
	}
	
	// preOrder: visit first
	// postOrder: visit last
	
	// levelOrder

	private void visit(BN n) {
		System.out.println(n.data);
	}
}