package mvc.interactor;

import javax.swing.JOptionPane;
import main_consoleinput.Console;
import mvc.main.Counter;

public class AMixedUIInteractor implements CounterInteractor {
    public void edit(Counter counter) {
        while (true) {
            int nextInput = Console.readInt();
            if (nextInput == 0)
                return;
            counter.add(nextInput);
            JOptionPane.showMessageDialog(null,
                    "Counter: " + counter.getValue());
        }
    }
}