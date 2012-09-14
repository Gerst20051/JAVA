package testing;

public class Strings {
	public static String[] toLowercase(String[] input) {
		String[] output = new String[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = input[i].toLowerCase();
		}
		return output;
	}
	
	public static void main(String[] args) {
		String[] lowers = toLowercase(args);
		for (int i = 0; i < lowers.length; i++) {
			System.out.println(lowers[i]);
		}
	}
}