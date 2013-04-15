package avlTree;

class Tree {
	Node root;
	
	public Tree()
	{
	}
	
	public Tree(Node root)
	{
		this.root = root;
	}
	
	public Node rotateRight()
	{
		return rotateRight(root);
	}
	
	public Node rotateRight(Node n)
	{
		Node q = root;
		Node p = q.left;
		Node c = q.right;
		Node a = p.left;
		Node b = p.right;
		
		q = new Node(q.data, b, c);
		p = new Node(p.data, a, q);
		
		return p;
	}
	
	public Node rotateLeft()
	{
		return rotateLeft(root);
	}
	
	public Node rotateLeft(Node n)
	{
		Node q = root;
		Node p = q.right;
		Node c = q.left;
		Node a = p.left;
		Node b = p.right;
		
		q = new Node(q.data,c,a);
		p = new Node(p.data,q,b);
		
		return p;
	}
	
	public Node insert(int n)
	{
		if(root == null)
		{
			root = new Node(n);
		}
		else {
			root = insert(root, new Node(n));
		}
		
		//System.out.println(n);
		if(checkAVL(root) == 0){
			//System.out.println(checkAVL(root));
			return root;
		}
		else if(checkAVL(root) == 1){
			//System.out.println(checkAVL(root));
			root = rotateLeft(root);
			return root;
		}
		else {
			//System.out.println(checkAVL(root));
			root = rotateRight(root);
			return root;
		}
	}
	
	public Node insert(Node n, Node m)
	{
		if(n == null)
		{
			return m;
		}
		if (m.data > n.data) // insert right
		{
			return new Node(n.data, n.left, insert(n.right, m));
		}
		else // insert left
		{
			return new Node(n.data, insert(n.left, m), n.right);
		}
	}
	
	public boolean search(int n)
	{
		Node clone = root;
		while (clone != null)
		{
			if (clone.data == n)
			{
				// System.out.println(clone.data);
				return true;
			}
			else if (clone.data < n)
			{
				clone = clone.right;
			} else {
				clone = clone.left;
			}
		}
		return false; // throw new Exception("The value doesnÕt exist");
	}
	
	public int depth()
	{
		return depth(root);
	}
	
	public int depth(Node n)
	{
		if (n == null)
		{
			return 0;
		} else {
			return Math.max(depth(n.left),depth(n.right)) + 1;
		}
	}
	
	public int checkAVL()
	{
		return checkAVL(root);
	}
	
	public int checkAVL(Node n)
	{
		if (depth(n.right) - depth(n.left) >= 2)
		{
			return 1;
		}
		else if (depth(n.left) - depth(n.right) >= 2)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	
	}
	
	public int minimum(Node n){
		Node current, last = null;
		current = n;
		while (current != null){
			last = current;
			current = current.left;
		}
		return last.data;
	}
	
	public int minimum()
	{
		return minimum(root);
	}
	
	public int maximum(Node n){
		Node current, last = null;
		current = root;
		while (current != null){
			last = current;
			current = current.right;
		}
		return last.data;
	}
	
	public int maximum()
	{
		return maximum(root);
	}
	
	public String toString()
	{
		return root.toString();
	}
}