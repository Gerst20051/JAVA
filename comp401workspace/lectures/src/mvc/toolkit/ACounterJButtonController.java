package mvc.toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import mvc.main.CounterController;
import mvc.main.ObservableCounter;

public class ACounterJButtonController implements CounterController, ActionListener {
    JButton button;
    ObservableCounter counter;
    public ACounterJButtonController(JButton theButton) {
        button = theButton;
        button.addActionListener(this);
    }
    public void setModel(ObservableCounter theCounter) {
        counter = theCounter;
    }   
    public void processInput() {

    }
    //ActionLisetener method
    public void actionPerformed(ActionEvent arg0) {
        counter.add(1);
    }
}