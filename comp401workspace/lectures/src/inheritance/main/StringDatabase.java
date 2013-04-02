package inheritance.main;

import arrays.collections_implementation.StringHistory;

public interface StringDatabase extends StringHistory {
	public void removeElement(String element);
	public void clear();
	public boolean member(String element);
}