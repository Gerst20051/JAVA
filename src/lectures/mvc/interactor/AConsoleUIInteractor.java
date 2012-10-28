package mvc.interactor;

import main_consoleinput.Console;
import mvc.main.Counter;

public class AConsoleUIInteractor implements CounterInteractor {
    public void edit(Counter counter) {
        while (true) {
            int nextInput = Console.readInt();
            if (nextInput == 0)
                return;
            counter.add(nextInput);
            System.out.println("Counter: " + counter.getValue());
        }
    }
}