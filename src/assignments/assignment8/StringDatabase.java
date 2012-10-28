package assignment8;

public interface StringDatabase {
	public int size();
	public boolean isFull();
	public String elementAt(int index);
	public void addElement(String element);
	public void removeElement(String element);
	public void removeElement(int startIndex);
	public void modifyElement(int index, String element);
	public int indexOf(String element);
	public boolean member(String element);
	public void clear();
}