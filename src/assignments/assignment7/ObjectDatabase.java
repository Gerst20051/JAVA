package assignment7;

public interface ObjectDatabase {
	public int size();
	public void addElement(Object element);
	public Object elementAt(int index);
	public void removeElement(Object element);
	public boolean member(Object element);
	public void clear();
}