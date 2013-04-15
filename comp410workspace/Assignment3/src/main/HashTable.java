package main;

public class HashTable {
	private static class HashEntry {
		public int key;
		public int data;
		public boolean isTombstone = false;
		
		public HashEntry(int k, int d){
			key = k;
			data = d;
		}

		public void makeTombstone(){
			isTombstone = true;
		}
	}
	
	private final int[] primes = { 53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157,
		98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843,
		50331653, 100663319, 201326611, 402653189, 805306457, 1610612741 };
	private int primeIdx = 0;
	private HashEntry[] table;
	private int numEntries = 0;
	private int numProbes = 0;
	
	public HashTable(){
		table = new HashEntry[primes[primeIdx]];
	}
	
	private void rehash(){
		numEntries = 0;
		HashEntry[] oldTable = table;
		try {
			table = new HashEntry[primes[++primeIdx]];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Too many entries in hash table. Exiting.");
			System.exit(1);
		}
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null && !oldTable[i].isTombstone) {
				insert(oldTable[i].key, oldTable[i].data);
			}
		}
	}
	
	public void insert(int key, int data){
		int size = primes[primeIdx];
		int i;
		numProbes = 0;
		if (numEntries >= 0.5 * size) {
			rehash();
			size = primes[primeIdx];
		}
		for (i = 0; i < size; i++) {
			int index = probe(key, i, size);
			if (table[index] == null || table[index].isTombstone) {
				table[index] = new HashEntry(key, data);
				++numEntries;
				numProbes = i;
				return;
			}
		}
		numProbes = i - 1;
	}
	
	private int probe(int key, int i, int size){
		return (hash(key) + i) % size;
	}
	
	public int getNumProbes(){
		return numProbes;
	}
	
	public boolean find(int key){
		int size = primes[primeIdx];
		for (int i = 0; i < size; i++) {
			int index = probe(key, i, size);
			if (table[index] == null) {
				return false;
			} else if (table[index].key == key && !table[index].isTombstone) {
				return true;
			}
		}
		return false;
	}
	
	public HashEntry findKey(int key){
		int size = primes[primeIdx];
		for (int i = 0; i < size; i++) {
			int index = probe(key, i, size);
			if (table[index] == null) {
				return null;
			} else if (table[index].key == key && !table[index].isTombstone) {
				return table[index];
			}
		}
		return null;
	}
	
	public void remove(int key){
		int size = primes[primeIdx];
		for (int i = 0; i < size; i++) {
			int index = probe(key, i, size);
			if (table[index] == null) {
			} else if (table[index].key == key && !table[index].isTombstone) {
				table[index].makeTombstone();
				--numEntries;
			}
		}
	}
	
	private int hash(int x){
	    return x % 10;
	}
	
	public int count(){
		return numEntries;
	}
	
	public void debug(){
		String result = "Format of display is\n";
		result += "Slot number: data record\n\n";
		result += "Current table size:\t\t" + table.length + "\n";
		result += "Number of elements in table:\t" + numEntries;
		System.out.println(result);
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].isTombstone) {
				result = "\n" + i + ": " + "[" + table[i].key + ", ";
				result += "(" + table[i].data + ")";
				result += "]";
				System.out.println(result);
			}
		}
	}
}