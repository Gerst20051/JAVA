package assignment9;

public interface ObjectDatabase {
	public int size();
	public boolean isFull();
	public Object elementAt(int index);
	public void addElement(Object element);
	public void removeElement(Object element);
	public void removeElement(int startIndex);
	public void modifyElement(int index, Object element);
	public int indexOf(Object element);
	public boolean member(Object element);
	public void clear();
}