package mvc.monolithic;

import javax.swing.JOptionPane;
import main_consoleinput.Console;

public class MonolithicMixedUI {
    public static void main(String[] args) {
        int counter = 0;
        while (true) {
            JOptionPane.showMessageDialog(null, "Counter: " + counter);
            int nextInput = Console.readInt();
            if (nextInput == 0) break;
            counter += nextInput;
        }
    }
}