package a11_commands;

public class ARepeatCompositeCommand implements Command {
	int reps;
	Command command;
	
	public ARepeatCompositeCommand(int initReps, Command initCommand) {
		reps = initReps;
		command = initCommand;
	}
	
	public void run() {
		for (int i = 0; i < reps; i++) {
			command.run();
		}
	}
}