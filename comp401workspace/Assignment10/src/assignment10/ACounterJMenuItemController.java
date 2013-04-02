package assignment10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public class ACounterJMenuItemController implements CounterController, ActionListener {
    JMenuItem menuItem;
    ObservableCounter counter;
    public ACounterJMenuItemController(JMenuItem theMenuItem) {
        menuItem = theMenuItem;
        menuItem.addActionListener(this);
    }
    public void setModel (ObservableCounter theCounter) {
        counter = theCounter;
    }   
    public void processInput() {

    }
    public void actionPerformed(ActionEvent arg0) {
        counter.add(1);
    }
}