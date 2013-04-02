package pq_as_heap_explicit;

import main.BinaryTreeNode;

public class PQueue {
	BinaryTreeNode root;
	int count;
	int size;
	
	public PQueue(int n){
		root = null;
		count = 0;
		size = n; 
	}
	
	public boolean isFull(){
		return (count == size);
	}
	
	public boolean isEmpty(){
		return (count == 0);
	}
	
	public void insert(double d){ // Precond: not full
		count = count+1;
		System.out.println("insert: " + d);
		root = insert(d, root, count);
	}
	
	private BinaryTreeNode insert(double d, BinaryTreeNode r, int n){
		if (n == 1) return new BinaryTreeNode(d, null, null);
		int l = (int) (Math.log(n) / Math.log(2)); // Floor{log_2(n)}
		if (n < (int) (Math.pow(2,l) + Math.pow(2,l-1))) {
			r.left = insert(d, r.left, (int) (n - Math.pow(2, l-1)));
			if (r.data > r.left.data) {
				double tmp = r.data;
				r.data = r.left.data;
				r.left.data = tmp;
			}
		} else {
			r.right = insert(d, r.right, (int) (n - Math.pow(2, l-1) - Math.pow(2, l-1)));
			if (r.data > r.right.data) {
				double tmp = r.data;
				r.data = r.right.data;
				r.right.data = tmp;
			}
		}
		return r;
	}
	
	public double min(){ // PRECOND: PQ not empty
		return root.data;
	}
	
	public double deleteMin(){
		double minItem = root.data;
		BinaryTreeNode tmp = lastNode(root);
		if (tmp.data == root.data) {
			//tmp = lastNode(root.left);
			//root = tmp;
			root = root.left;
			System.out.println("IS tmp.data == root.data");
		} else {
			System.out.println("NOT tmp.data == root.data");
			root = tmp;
		}
		count--;
		percolate(root);
		System.out.println("min: " + minItem);
		return minItem;
	}
	
	public BinaryTreeNode lastNode(BinaryTreeNode node){
		if (node.left == null && node.right == null) return node;
		if (node.right == null) return node; // return lastNode(node.left);
		return lastNode(node.right);
	}
	
	public void percolate(BinaryTreeNode node){
		BinaryTreeNode minNode = node, tmp;
		if (node == null) return; // order property has been fixed
		else System.out.println("node: " + node.data);
		if (node.left != null && node.right != null) {
			if (node.left.data < node.right.data) {
				minNode = node.left;
			} else {
				minNode = node.right;
			}
		} else {
			if (node.left != null) minNode = node.left;
			else if (node.right != null) minNode = node.right;
		}
		if (node.data > minNode.data) { // swap nodes if order property isn't correct
			tmp = minNode;
			minNode = node;
			node = tmp;
			percolate(minNode);
		}
	}
}