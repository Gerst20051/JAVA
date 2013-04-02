package l3;

//import edu.unc.cs.IntegerStackArray.IntegerStack;
//import edu.unc.cs.IntegerStackLL.IntegerStack;
//import edu.unc.cs.IntegerStackArrayList.IntegerStack;
import java.util.Random;

public class Tester {
	public static void main(String[] args) {
		int n = (args.length == 0) ? 10 : Integer.parseInt(args[0]);
		Random r = new Random();
		IntegerStack S = new IntegerStack(n);
		for (int i=0; i< n+1; i++) 
			if (!S.isFull()) S.push(r.nextInt(100));
		S.print();
		System.out.println(S.isFull());
		while (!S.isEmpty()) System.out.println("  "+ S.pop());
	}
}