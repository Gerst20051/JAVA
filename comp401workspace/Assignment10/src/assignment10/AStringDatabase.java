package assignment10;

import util.annotations.StructurePattern;
@StructurePattern("Vector Pattern")

public class AStringDatabase implements StringDatabase {
	public final int MAX_SIZE = 50;
	String[] contents = new String[MAX_SIZE];
	int size = 0;

	public int size() {
		return size;
	}

	public boolean isFull() {
		return size() == MAX_SIZE;
	}
	
	public String elementAt(int index) {
		return contents[index];
	}

	public void addElement(String element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			contents[size++] = element;
		}
	}

	public void removeElement(String element) {
		contents[indexOf(element)] = contents[--size];
	}

	public void removeElement(int startIndex) {
		shiftUp(startIndex);
		size--;
	}
	
	public void modifyElement(int index, String element) {
		contents[index] = element;
	}

	private void shiftUp(int startIndex) {
		for (int index = startIndex; index + 1 < size(); index++)
			contents[index] = contents[index + 1];
	}

	public int indexOf(String element) {
		int index;
		for (index = 0; index < size() && !element.equals(contents[index]); index++)
			;
		if (!element.equals(contents[index])) {
			return -1;
		}
		return index;
	}

	public boolean member(String element) {
		int index = indexOf(element);
		return (-1 < index && index < size());
	}

	public void clear() {
		contents = new String[MAX_SIZE];
		size = 0;
	}
}