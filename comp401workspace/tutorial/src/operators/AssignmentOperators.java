package operators;

public class AssignmentOperators {
	   public AssignmentOperators( ) {
		   
//		   Assigning Primitive Values
		   int j, k;
		   j = 10;       // j gets the value 10.
		   j = 5;        // j gets the value 5. Previous value is overwritten.
		   k = j;        // k gets the value 5.
		   System.out.println("j is : "+j);
		   System.out.println("k is : "+k);
		   
//		   Assigning References
		   Integer i1 = new Integer("1");
		   Integer i2 = new Integer("2");
		   System.out.println("i1 is : "+i1);
		   System.out.println("i2 is : "+i2);
		   i1 = i2;
		   System.out.println("i1 is : "+i1);
		   System.out.println("i2 is : "+i2);
		   
//		   Multiple Assignments
		   k = j = 10;       // (k = (j = 10))
		   System.out.println("j is : "+j);
		   System.out.println("k is : "+k);

	   }
	   public static void main(String args[]){
		   new AssignmentOperators();
	   }
	}