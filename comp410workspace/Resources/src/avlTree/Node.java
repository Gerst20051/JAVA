package avlTree;

public class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public Node() {
	}

	public Node(int data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public static String getAsString(Node n) {
		if (n != null) {
			return n.toString();
		} else {
			return "N";
		}
	}

	public String toString()
	{
		return "(" + getAsString(left) + " " + data + " " + getAsString(right);
	}
}