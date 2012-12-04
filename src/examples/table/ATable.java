package table;

import java.util.ArrayList;

public class ATable implements TableInterface {
	
	ArrayList<String> keylist = new ArrayList<String>();
	ArrayList<Object> vallist = new ArrayList<Object>();
	
	public void put(String key, Object val){
		
		if (keylist.indexOf(key) < 0) {
			keylist.add(key);
			vallist.add(val);
		}
		else {
			vallist.set(keylist.indexOf(key), val);
		}
		
	}
	public Object get(String key){
		
		if (keylist.indexOf(key) <0) {
			return null;
		}
		else {
			return vallist.get(keylist.indexOf(key));
		}
	}
	
}
