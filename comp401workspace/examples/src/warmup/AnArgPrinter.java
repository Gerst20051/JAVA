package warmup;

public class AnArgPrinter {
	public static void main (String[] args) {
		if (args.length == 1)
			System.out.println(args[0]);
		else
			System.out.println("Illegal no of arguments:" + args.length + ". Terminating program");
	}
}