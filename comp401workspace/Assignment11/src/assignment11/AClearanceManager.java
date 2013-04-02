package assignment11;

import util.annotations.ComponentWidth;
import util.annotations.Row;

public class AClearanceManager implements ClearanceManager {
	@Row(0)
	@ComponentWidth(100)
	public synchronized void proceed() {
		notify();
	}
	
	public synchronized void waitForProceed() {
		try {
			wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}