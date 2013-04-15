package mohammad;

/* Testing all methods. */

import java.io.*;
import java.lang.*;

public class test1 {
	public static void main(String[] arg) {
		Btree l = new Btree();
		l.add("020");
		l.add("005");
		l.add("010");
		l.add("002");
		l.add("003");
		l.add("006");
		l.add("019");
		l.add("001");
		l.add("008");
		l.add("004");
		l.add("033");
		l.add("040");
		System.out.println("=========== DUMP AFTER INSERTING 20, 05, 10, 02, 03, 06, 19, 01, 08, 04, 033, 040 ===========");
		l.dump();
		l.add("023");
		l.add("021");
		l.add("022");
		l.add("024");
		l.add("027");
		l.add("025");
		l.add("026");
		System.out.println("=========== DUMP AFTER INSERTING 21..27===========");
		l.dump();
		l.add("018");
		l.add("013");
		l.add("011");
		l.add("012");
		l.add("014");
		l.add("015");
		l.add("017");
		l.add("016");
		l.add("0045");
		if (!l.check()) {
			System.out.println("********Invalid Tree********");
			l.dump();
			System.exit(-1);
		}
		System.out.println("=========== DUMP AFTER INSERTING 11..18, 0045===========");
		l.dump();
		l.add("0202");
		if (!l.check()) {
			System.out.println("********Invalid Tree********");
			l.dump();
			System.exit(-1);
		}
		System.out.println("=========== DUMP AFTER INSERTING 0202===========");
		l.dump();
		l.add("0205");
		if (!l.check()) {
			System.out.println("********Invalid Tree********");
			l.dump();
			System.exit(-1);
		}
		System.out.println("=========== DUMP AFTER INSERTING 0205===========");
		l.dump();
		l.add("0203");
		if (!l.check()) {
			System.out.println("********Invalid Tree********");
			l.dump();
			System.exit(-1);
		}
		l.add("0204");
		if (!l.check()) {
			System.out.println("********Invalid Tree********");
			l.dump();
			System.exit(-1);
		}
		l.add("0201");
		if (!l.check()) {
			System.out.println("********Invalid Tree********");
			l.dump();
			System.exit(-1);
		}
		System.out.println("=========== DUMP AFTER INSERTING 0201-0202===========");
		l.dump();
	}
}