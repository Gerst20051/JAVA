package warmup;

public class AnUpperCasePrinter {
	public static void main(String[] args){
		if (args.length != 1) {
			System.out.println("Illegal number of arguments:" + args.length + ". Terminating program.");
			System.exit(-1);
		}
		System.out.println("Upper Case Letters:");
		int index = 0;
		while (index < args[0].length()) {
			char nextLetter = args[0].charAt(index);
			if (nextLetter >= 'A' && nextLetter <= 'Z')
				System.out.print(nextLetter);
			index++;
		}
		System.out.println();
	}
}