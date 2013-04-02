package commandpackage;

import java.util.ArrayList;
import java.util.List;

import javax.activation.CommandObject;

public class ACommandListCompositeCommand implements Command{

	public final int MAX_SIZE = 1000000000; 
	int size = 0;
	//ArrayList
	ArrayList<Command> commandList = new ArrayList<Command>();
	
	public Command elementAt (int index) {
        return commandList.get(index);
    }
	
    boolean isFull() {return size == MAX_SIZE;}

    public void addElement(Command element) {
        if (isFull())
            System.out.println("Adding item to a full history");
        else {
        	commandList.add(element);
            size++;
        }
    } 
	public int size() { return size; }
	
	@Override
	public void run() {
		//simply invoke the run method of each element in order
		for(int i=0; i< size(); i++) {
			elementAt(i).run();
		}
	}
	
}
