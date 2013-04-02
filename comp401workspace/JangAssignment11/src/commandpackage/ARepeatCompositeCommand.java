package commandpackage;



public class ARepeatCompositeCommand implements Command {
	
	int usersize;
	Command command;
	
	public ARepeatCompositeCommand (int initInt, Command initCommand) {
		usersize = initInt;
		command = initCommand;
	}
	
//	ArrayList<Runnable> commandList = new ArrayList<Runnable>();
	
//	public Runnable elementAt (int index) {
//        return commandList.get(index);
//    }
	
    
//    public void addElement(Runnable element) {
//        	commandList.add(element);
//    }

	@Override
	public void run() {
		for(int i=0; i< usersize; i++) {
			command.run();
		}
	}
	
}
