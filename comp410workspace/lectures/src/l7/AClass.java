package l7;

public class AClass implements Comparable<AClass> {
	int index;
	String str;
	
	public AClass(int i, String s){
		index = i;
		str = s;
	}
	
	public int compareTo(AClass x){
		// This function MUST be defined -- error if not
		// Return -ve, zero, +ve if "this" is <, =, or > x
		return (this.index - x.index);
	}
}