package assignment9;

import util.annotations.StructurePattern;
@StructurePattern("Hashtable Pattern")

public class ATable {
	AStringDatabase keys;
	AObjectDatabase values;
	
	public ATable() {
		keys = new AStringDatabase();
		values = new AObjectDatabase();
	}
	
	public int size() {
		return keys.size();
	}
	
	public boolean isFull() {
		return keys.isFull();
	}
	
	public void put(String key, Object val) {
		if (key == null || val == null) return;
		if (keys.member(key)) {
			values.modifyElement(keys.indexOf(key), val);
		} else {
			keys.addElement(key);
			values.addElement(val);
		}
	}
	
	public Object get(String key) {
		if (keys.member(key)) {
			return values.elementAt(keys.indexOf(key));
		} else {
			return null;
		}
	}
	
	public void delete(String key) {
		if (keys.member(key)) {
			int index = keys.indexOf(key);
			keys.removeElement(index);
			values.removeElement(index);
		}
	}
	
	public int indexOf(String element) {
		return keys.indexOf(element);
	}
	
	public boolean member(String element) {
		return keys.member(element);
	}
	
	public void clear() {
		keys.clear();
		values.clear();
	}
	
	public static void main(String[] args) {
		ATable table = new ATable();
		table.put("line", new ALine(0, 0, 5, 10));
		table.put("line2", new ALine(1, 2, 50, 100));
		System.out.println(((ALine) table.get("line")).getWidth());
		System.out.println(((ALine) table.get("line2")).getHeight());
	}
}