package l18;

public class BinaryNode {
	public int data, height;
	public BinaryNode left;
	public BinaryNode right;

	public BinaryNode(int d, BinaryNode l, BinaryNode r) {
		data = d;
		left = l;
		right = r;
	}

	private BinaryNode insert(BinaryNode t, int d){
		if (t == null) return new BinaryNode(d, null, null);
		if (d == t.data) { t.data = d; return t; } // just replace the data
		if (d < t.data) t.left = insert(t.left, d);
		else t.right = insert(t.right, d);
		// Now, must rebalance
		int leftHeight = (t.left == null) ? -1 : t.left.height;
		int rightHeight = (t.right == null) ? -1 : t.right.height;
		if (leftHeight > rightHeight + 1) { // LL or LR
			if (d < t.left.data) {
				// LL
			} else {
				// LR
			}
			if (leftHeight < rightHeight - 1) { // RR or RL
				if (d > t.right.data) {
					// RR
				} else {
					// RL
				}
			}	
		}
		// Update t’s height field
		leftHeight = (t.left == null) ? -1 : t.left.height;
		rightHeight = (t.right == null) ? -1 : t.right.height;
		t.height = 1 + Math.max(leftHeight, rightHeight);
		return t;
	}
}