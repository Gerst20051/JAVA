package main;

public interface Dictionary {
	// insert a key-value pair into the dictionary
	public void insert(int k, int data);
	
	// search for an entry with key k and return whether integer key exists
	public boolean find(int k);
	
	// delete an entry with key k
	public void remove(int k);
	
	// number of elements stored
	public int count();
}