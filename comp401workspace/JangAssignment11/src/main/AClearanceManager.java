package main;

import util.annotations.ComponentWidth;
import util.annotations.Row;

public class AClearanceManager implements ClearanceManager{
//  boolean clearance;
    @Row(0)
    @ComponentWidth(100)
//  @Label("Proceed")
    public synchronized void proceed() {
//      clearance = true;
        notify();
    }
    
    public synchronized void waitForProceed() {
//      if (!clearance) {
            try {
                wait();
//              clearance = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
//      }
    }

}
