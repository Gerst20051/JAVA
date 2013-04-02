package l13;

public class BinaryNode {
	int data;
	// Other data fields (such as count, height, balance, etc.) here
	BinaryNode left;
	BinaryNode right;
	int height;
	int count;
	int balance;
	
	public BinaryNode(int x, BinaryNode l, BinaryNode r) { // Constructor
		// Ignoring other data fields here...
		data = x; left = l; right = r;
	}
	
	public void BinaryNode2(int x, BinaryNode l, BinaryNode r) { // Updated Constructor
		data = x; left = l; right = r;
		// Can modify constructor to update height, count
		int leftHeight = (left == null) ? -1 : left.height;
		int rightHeight = (right == null) ? -1 : right.height;
		height = 1 + Math.max(leftHeight, rightHeight);
		// Similarly for count
	}
	
	public void buildTree(){
		// A tree is just a reference to a BinaryNode – the root node
		BinaryNode T1; // Declares a tree T1
		
		BinaryNode t1 = new BinaryNode(2, null, null);
		BinaryNode t2 = new BinaryNode(3, null, null);
		BinaryNode t3 = new BinaryNode(1, t1, t2);
		
		// The following is equivalent:
		t3 = new BinaryNode(1,
				new BinaryNode(2, null, null),
				new BinaryNode(3, null, null)
		);
	}
	
	public int count(BinaryNode t){
		if (t == null) return 0;
		return 1 + count(t.left) + count(t.right);
	}
	
	public int height(BinaryNode t){
		if (t == null) return 0;
		return 1 + Math.max(height(t.left), height(t.right));
	}
	
	public BinaryNode makeCopy(BinaryNode t){
		if (t == null) return null;
		return new BinaryNode(t.data, makeCopy(t.left), makeCopy(t.right));
	}
	
	public BinaryNode makeCopy(){
		// If we wanted it to be a method within BinaryNode
		BinaryNode tmp1 = (this.left == null) ? null : this.left.makeCopy();
		BinaryNode tmp2 = (this.right == null) ? null : this.right.makeCopy();
		return new BinaryNode(this.data, tmp1, tmp2);
	}
	
	public int countFull(BinaryNode N){
		if (N == null) return 0;
		int retVal = countFull(N.left) + countFull(N.right);
		if ((N.left != null) && (N.left != null)) retVal += 1;
		return retVal;
	}
	
	public BinaryNode makeCopyMinusLeafs(){
		// Copy a tree minus all leaf nodes
		return new BinaryNode(0, null, null);
	}
	
	public void printNodesLevelOrder(){
		// Print out the nodes in level order
	}
}