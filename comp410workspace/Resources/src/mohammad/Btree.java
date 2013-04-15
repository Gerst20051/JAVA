package mohammad;

public class Btree {
	Node root_ = null;

	Btree() {
	}

	// display structure of the btree
	void dump() {
		dis(root_, 1);
	}

	// return values concatenated as a string in order
	public String toString() {
		return "{ " + pr(root_) + " }";
	}

	// add an item to the Tree
	void add(Comparable k) {
		if (root_ == null) {
			root_ = new Node(k, null, null);
			return;
		}
		Node r = contains(root_, k); // Find the node to insert into, can't be
										// null
		if (r.isIn(k) != 0)
			return;// return if a duplicate.
		insert(r, k, null);// r must be a leaf node, add k/null s key/child to
							// it.
	}

	// added to check the key count in each node
	boolean checkCount(Node n) {
		if (n.count < Node.MINKEYS) {
			System.out.println("error with key count: " + n);
			return false;
		}
		if (n.child[0] != null)
			for (int i = 0; i <= n.count; i++)
				if (!checkCount(n.child[i]))
					return false;
		return true;
	}

	// Check to make sure the tree is valid in terms of the relative
	// position of keys. A useful method after an add/remove call to be sure
	// an error didn't take place. The method checks any key in the tree
	// to make sure it is larger than any other keys that fall to its left
	// (including in subtrees); it also checks to make sure the key is
	// smaller than any to its right.
	boolean check() {
		if (root_ == null)
			return true;
		if (root_.child[0] != null)
			for (int i = 0; i <= root_.count; i++)
				if (!checkCount(root_.child[i]))
					return false;

		// The keys in the node are checked to make sure they are in sequence.
		for (int i = 1; i < root_.count; i++)
			if (root_.key[i].compareTo(root_.key[i + 1]) > 0) {
				System.out.println("Keys are out of order in Node: " + root_);
				return false;
			}
		// Each key is checked against its left+right branches.
		for (int i = 1; i <= root_.count; i++) {
			if (!check(root_.child[i - 1], root_.key[i], false))
				return false;
			if (!check(root_.child[i], root_.key[i], true))
				return false;
		}
		return true;
	}

	// Check keys in t against val based on the greater boolean. If greater
	// is true, keys in t must be greater than val, otherwise they must be
	// smaller. val must also be checked against nodes at child[0]
	// or child[n] in t depending on greater being true or false.
	// The keys in t need to get check the same way that keys in root_
	// did in the method check.
	boolean check(Node t, Comparable val, boolean greater) {
		if (t == null)
			return true;

		// if greater == true then all keys in t must be larger than val;
		// if the first one is smaller, return false.
		if (greater && t.key[1].compareTo(val) < 0) {
			System.out.println(val
					+ "was not suppose to be greater than the 1st key in " + t);
			return false;
		}
		// if greater == false then all keys in t must be smaller than val;
		// if the last one is larger, return false.
		if (!greater && t.key[t.count].compareTo(val) > 0) {
			System.out
					.println(val
							+ "was not suppose to be smaller than the last key in "
							+ t);

			return false;
		}
		// if greater == true, all keys in t.child[count] must be smaller than
		// val.
		if (greater && !check(t.child[0], val, true))
			return false;

		// if greater == false, all keys in t.child[count] must be smaller than
		// val.
		if (!greater && !check(t.child[t.count], val, false))
			return false;

		// The keys in the node are checked to make sure they are in sequence.
		for (int i = 1; i < t.count; i++)
			if (t.key[i].compareTo(t.key[i + 1]) > 0) {
				System.out.println("Keys are out of order in Node: " + t);
				return false;
			}

		// Each key much be larger than the keys in its left+right branches.
		for (int i = 1; i <= t.count; i++) {
			if (!check(t.child[i - 1], t.key[i], false))
				return false;
			if (!check(t.child[i], t.key[i], true))
				return false;
		}
		return true;
	}

	// Remove an item from the tree
	void remove(Comparable k) {
		System.out.println("BTree removal Not implemented!!!");
	}

	void insert(Node r, Comparable k, Node right) {
		Node xright = r.insert(k, right);// use Node's insert method to add
											// k/right
		if (xright == null)
			return;// there was room, insert is complete
		Comparable xkey = xright.key[0];// the value that is promoted.
		if (r.parent == null) // r is the root node.
			root_ = new Node(xkey, root_, xright);
		else
			insert(r.parent, xkey, xright);// promote xkey/xright to the parent
											// node.
	}

	// Search for the given value as follows. If found return it, otherwise
	// return null.
	Comparable find(Comparable o) {
		Node tmp = contains(root_, o);
		if (tmp == null)
			return null;
		int i = tmp.isIn(o);
		if (i > 0)
			return tmp.key[i];
		else
			return null;
	}

	// Check if the list is empty
	boolean empty() {
		return root_ == null ? true : false;
	}

	// search the current node for either the key or the closest child
	// likely to contain the key; recurse on the child. This method returns
	// the node with the key found in it, if one is found; othewise, it returns
	// the node last visted.
	Node contains(Node r, Comparable k) {
		if (r == null)
			return null;
		else {
			int w = r.search(k);
			if (w != 0 && k.compareTo(r.key[w]) == 0)
				return r;
			else {
				Node f = contains(r.child[w], k);
				if (f == null)
					return r;
				else
					return f;
			}
		}
	}

	// print elements in order
	String pr(Node r) {
		String s = new String();
		if (r != null) {
			for (int i = 0; i < r.count; i++) {
				s = s.concat(pr(r.child[i]));
				s = s.concat(r.key[i + 1] + " ");
			}
			s = s.concat(pr(r.child[r.count]));
		}
		return s;
	}

	// Print out the structure of the tree
	void dis(Node r, int depth) {
		if (r != null) {
			for (int i = r.count; i >= 1; i--) {
				dis(r.child[i], depth + 4);
				for (int j = 1; j <= depth; j++)
					System.out.print(" ");
				System.out.println(r.key[i]);
			}
			dis(r.child[0], depth + 4);
		}
	}

	// Count # of levels in the tree
	int levels() {
		int l = 0;
		Node t = root_;
		while (t != null) {
			l++;
			t = t.child[0];
		}
		return l;
	}
}