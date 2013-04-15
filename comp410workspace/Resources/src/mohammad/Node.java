package mohammad;

class Node {
	static final int M = 5; // Order of this BTree, should be 3 or higher
	static final int MAXKEYS = M - 1;
	static final int MINKEYS = M / 2;

	Node parent = null; // Link to the parent

	int count = 0; // number of keys in the node

	// Keys; the 0th is ONLY used for key promotion when spliting.
	Comparable[] key = new Comparable[M];

	// branches to children
	Node[] child = new Node[M];

	// long [] recNo = new long[M]; Rec # of data record for each key, not used
	// here

	// construct a node with one key and two branches; only used for root node
	// construction.
	Node(Comparable k, Node l, Node r) {
		key[1] = k;
		child[0] = l;
		child[1] = r;
		parent = null;
		if (l != null) {
			l.parent = r.parent = this;
		}
		count++;
	}

	// construct an empty node.
	Node() {
	}

	// return key values concatenated as a string
	public String toString() {
		String s = new String("[ ");
		for (int i = 1; i <= count; i++)
			s = s.concat(key[i] + " ");
		s = s.concat(" WITH COUNT= " + count + " AND PARENT= " + parent + " ]");
		return s;
	}

	// Search for a key. If found return its position; if not,
	// return the position of child where to continue search.
	int search(Comparable k) {
		if (k.compareTo(key[1]) < 0)
			return 0;
		else {
			int w = count;
			while ((k.compareTo(key[w]) < 0) && (w > 1))
				w--;
			return w;
		}
	}

	// Search a node for a key; if found return its position; if not,
	// return 0
	int isIn(Comparable k) {
		if (k.compareTo(key[1]) < 0)
			return 0;
		else {
			int w = count;
			while (w >= 1 && k.compareTo(key[w]) != 0)
				w--;
			return w;
		}
	}

	// If the k/right fit, add them to the node and return null. Otherwise,
	// Split the node into 2 (i.e. create a 2nd node and move half of the
	// key/children into it. Place k and right in the correct half. key[0]
	// of the newly constructed node will hold the key to be promoted to
	// the previous level.
	Node insert(Comparable k, Node right) {
		Node yright = null;
		int p = search(k);
		if (count < MAXKEYS) {// If there is room insert k/right in the correct
								// pos
			count++;
			for (int i = count; i > p + 1; i--) {
				key[i] = key[i - 1];
				child[i] = child[i - 1];
			}
			key[p + 1] = k;
			child[p + 1] = right;
			if (right != null)
				right.parent = this;
		} else {// Make another node and handle the 3 cases.
			yright = new Node();
			yright.parent = parent;
			// Decide splitting point and where should the new value end up
			if (p < MINKEYS) {
				// put k/right in current node, move half of the
				// key/child pairs into the new node
				// the key in key[0] of the new node is to be promoted.
				for (int i = MINKEYS, j = 0; i <= count; i++, j++) {
					yright.key[j] = key[i];
					yright.child[j] = child[i];
					if (yright.child[j] != null)
						yright.child[j].parent = yright;
				}
				count = MINKEYS - 1;
				insert(k, right);
				yright.count = MAXKEYS - MINKEYS;
			} else if (p == MINKEYS) {
				// k is what needs to be promoted, so it ends of key[0] of the
				// new node.
				for (int i = MINKEYS + 1, j = 1; i <= count; i++, j++) {
					yright.key[j] = key[i];
					yright.child[j] = child[i];
					if (yright.child[j] != null)
						yright.child[j].parent = yright;
				}
				yright.key[0] = k;
				yright.child[0] = right;
				if (right != null)
					right.parent = yright;
				count = MINKEYS;
				yright.count = MAXKEYS - MINKEYS;
			} else {// k ends up in the 2nd node.
				for (int i = MINKEYS + 1, j = 0; i <= count; i++, j++) {
					yright.key[j] = key[i];
					yright.child[j] = child[i];
					if (yright.child[j] != null)
						yright.child[j].parent = yright;
				}
				count = MINKEYS;
				yright.count = MAXKEYS - MINKEYS - 1;
				yright.insert(k, right);
			}
		}
		return yright;
	}
}