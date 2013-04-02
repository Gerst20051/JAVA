package assignment8;

import javax.swing.JTextField;

public class ACounterJTextFieldView implements CounterObserver {
    JTextField textField;
    
    public ACounterJTextFieldView(JTextField theTextField) {
        textField = theTextField;
    }
    
    public void update(ObservableCounter counter) {
        textField.setText("" + counter.getValue());
    }
}