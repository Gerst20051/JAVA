package a11_commands;

import java.util.ArrayList;

public class ACommandListCompositeCommand implements Command {
	public final int MAX_SIZE = 1000000000;
	int size = 0;
	ArrayList<Command> commandList = new ArrayList<Command>();
	
	public Command elementAt(int index) {
		return commandList.get(index);
	}
	
	boolean isFull() { return size == MAX_SIZE; }
	
	public void addElement(Command element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			commandList.add(element);
			size++;
		}
	}
	
	public int size() { return size; }
	
	public void run() {
		for (int i = 0; i < size(); i++) {
			elementAt(i).run();
		}
	}
}