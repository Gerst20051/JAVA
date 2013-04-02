package console;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
	static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
	public static int readInt() {
		try {
			return Integer.parseInt(inputStream.readLine());
		} catch (Exception e) {
			System.out.println(e);
			return 0; 
		}
	}
	public static String readString() {
		try {
			return inputStream.readLine();
		} catch (Exception e) {
			System.out.println(e);
			return ""; 
		}
	}
}