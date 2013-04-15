package main;

import main.Node;

public class Tree {
	public Node root;
	
	public Tree(){
		this(null);
	}
	
	public Tree(Node root){
		this.root = root;
	}
	
	public void insert(int k, int n){
		if (root == null) {
			root = new Node(k, n);
		} else {
			root = insert(root, new Node(k, n));
		}
		
		// System.out.println(n);
	}
	
	public Node insert(Node n, Node m) {
		if (n == null) {
			return m;
		}
		if (m.getData() > n.getData()) { // insert right
			return new Node(n.getKey(), n.getData(), n.getLeft(), insert(n.getRight(), m));
		} else { // insert left
			return new Node(n.getKey(), n.getData(), insert(n.getLeft(), m), n.getRight());
		}
	}
	
	public boolean find(int k){
		Node clone = root;
		while (clone != null) {
			if (clone.getKey() == k) {
				return true;
			} else if (clone.getKey() < k) {
				clone = clone.getRight();
			} else {
				clone = clone.getLeft();
			}
		}
		return false;
	}
	
	public boolean findData(int n){
		Node clone = root;
		while (clone != null) {
			if (clone.getData() == n) {
				return true;
			} else if (clone.getData() < n) {
				clone = clone.getRight();
			} else {
				clone = clone.getLeft();
			}
		}
		return false;
	}
	
	public Node findKey(int k){
		Node clone = root;
		while (clone != null) {
			if (clone.getKey() == k) {
				return clone;
			} else if (clone.getKey() < k) {
				clone = clone.getRight();
			} else {
				clone = clone.getLeft();
			}
		}
		return null;
	}
	
	public void remove(int k){
		root = remove(root, k);
	}
	
	public Node remove(Node n, int k){
		if (n == null) {
			return null;
		}
		if (k < n.getData()) {
			n.setLeft(remove(n.getLeft(), k));
			return n;
		}
		if (k > n.getData()) {
			n.setRight(remove(n.getRight(), k));
			return n;
		}
		if (n.left == null || n.right == null) {
			if (n.left == null) {
				return n.right;
			} else {
				return n.left;
			}
		}
		int tmp = minimum(n.getRight());
		n.data = tmp;
		n.right = remove(n.right, tmp);
		return n;
	}
	
	public int count(){
		return count(root);
	}
	
	public int count(Node n){
		if (n == null) {
			return 0;
		} else if (n.getLeft() == null && n.getRight() == null) {
			return 1;
		} else {
			return 1 + count(n.getLeft()) + count(n.getRight());
		}
	}
	
	public int depth(){
		return depth(root);
	}
	
	public int depth(Node n){
		if (n == null) {
			return 0;
		} else {
			return Math.max(depth(n.getLeft()), depth(n.getRight())) + 1;
		}
	}
	
	public int minimum(){
		return minimum(root);
	}
	
	public int minimum(Node n){
		Node current, last = null;
		current = n;
		while (current != null) {
			last = current;
			current = current.getLeft();
		}
		return last.getData();
	}
	
	public Node findMin(){
		return findMin(root);
	}
	
	public Node findMin(Node n){
		while (n.getLeft() != null) {
			n = n.getLeft();
		}
		return n;
	}
	
	public int maximum(){
		return maximum(root);
	}
	
	public int maximum(Node n){
		Node current, last = null;
		current = root;
		while (current != null) {
			last = current;
			current = current.getRight();
		}
		return last.getData();
	}
	
	public Node findMax(){
		return findMax(root);
	}
	
	public Node findMax(Node n){
		while (n.getRight() != null) {
			n = n.getRight();
		}
		return n;
	}
	
	public String toString(){
		return root.toString();
	}
}