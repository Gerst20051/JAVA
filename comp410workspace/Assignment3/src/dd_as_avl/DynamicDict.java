package dd_as_avl;

import main.Dictionary;
import main.Node;
import main.Tree;

public class DynamicDict extends Tree implements Dictionary {
	public DynamicDict(){}
	
	@Override
	public void insert(int k, int n){
		if (root == null) {
			root = new Node(k, n);
		} else {
			root = insert(root, new Node(k, n));
		}
		
		if (checkAVL(root) == 0) {
			// System.out.println(checkAVL(root));
		} else if (checkAVL(root) == 1) {
			// System.out.println(checkAVL(root));
			root = rotateLeft(root);
		} else {
			// System.out.println(checkAVL(root));
			root = rotateRight(root);
		}
	}
	
	public Node rotateRight(){
		return rotateRight(root);
	}
	
	public Node rotateRight(Node n){
		Node q = root;
		Node p = q.left;
		Node c = q.right;
		Node a = p.left;
		Node b = p.right;
		
		q = new Node(q.key, q.data, b, c);
		p = new Node(p.key, p.data, a, q);
		
		return p;
	}
	
	public Node rotateLeft(){
		return rotateLeft(root);
	}
	
	public Node rotateLeft(Node n){
		Node q = root;
		Node p = q.right;
		Node c = q.left;
		Node a = p.left;
		Node b = p.right;
		
		q = new Node(q.key, q.data, c, a);
		p = new Node(p.key, p.data, q, b);
		
		return p;
	}
	
	public int checkAVL(){
		return checkAVL(root);
	}
	
	public int checkAVL(Node n){
		if (depth(n.right) - depth(n.left) >= 2) {
			return 1;
		} else if (depth(n.left) - depth(n.right) >= 2) {
			return -1;
		} else {
			return 0;
		}
	}
}