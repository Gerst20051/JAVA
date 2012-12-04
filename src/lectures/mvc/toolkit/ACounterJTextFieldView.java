package mvc.toolkit;

import javax.swing.JTextField;
import mvc.main.CounterObserver;
import mvc.main.ObservableCounter;

public class ACounterJTextFieldView implements CounterObserver {
    JTextField textField;
    
    public ACounterJTextFieldView(JTextField theTextField) {
        textField = theTextField;
    }
    
    public void update(ObservableCounter counter) {
        textField.setText("" + counter.getValue());
    }
}