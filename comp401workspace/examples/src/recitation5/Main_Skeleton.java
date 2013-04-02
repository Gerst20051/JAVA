package recitation5;

import util.misc.ThreadSupport;
import bus.uigen.ObjectEditor;

public class Main_Skeleton {
	public static void main(String[] args) {
		MathClass_Skeleton m1 = new MathClass_Skeleton();
		MathClass_Skeleton m2 = new MathClass_Skeleton();
		ObjectEditor.edit(m1);
		ObjectEditor.edit(m2);
		m1.setNumber(2);
		m2.setNumber(5);
		ThreadSupport.sleep(2000);
		m1.setNumber(5);
		m2.setNumber(7);
		ThreadSupport.sleep(2000);
		m1.setNumber(1);
		m2.setNumber(1);
	}
}