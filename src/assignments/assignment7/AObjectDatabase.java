package assignment7;

public class AObjectDatabase implements ObjectDatabase {
	public final int MAX_SIZE = 50;
	Object[] contents = new Object[MAX_SIZE];
	int size = 0;

	public int size() {
		return size;
	}

	public Object elementAt(int index) {
		return contents[index];
	}

	boolean isFull() {
		return size == MAX_SIZE;
	}

	public void addElement(Object element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			contents[size] = element;
			size++;
		}
	}

	public void removeElement(Object element) {
		contents[indexOf(element)] = contents[size - 1];
		size--;
	}

	void removeElement(int startIndex) {
		shiftUp(startIndex);
		size--;
	}
	
	public void modifyElement(int index, Object element) {
		contents[index] = element;
	}

	void shiftUp(int startIndex) {
		for (int index = startIndex; index + 1 < size; index++)
			contents[index] = contents[index + 1];
	}

	public int indexOf(Object element) {
		int index;
		for (index = 0; index < size && !element.equals(contents[index]); index++)
			;
		return index;
	}

	public boolean member(Object element) {
		return indexOf(element) < size;
	}

	public void clear() {
		size = 0;
	}
}