package mvc.toolkit;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import mvc.main.AnObservableCounter;
import mvc.main.CounterController;
import mvc.main.CounterObserver;
import mvc.main.ObservableCounter;

public class SingleFrameAWTButtonTextFieldComposer {
    public static void main (String args[]) {
        // compose AWT components
        JFrame frame = new JFrame("AWT Controller and View");
        JButton button = new JButton("Increment");
        JTextField textField = new JTextField();
        frame.setLayout(new GridLayout(2,1));
        frame.add(textField);
        frame.add(button);
        frame.setSize(200, 200);
        frame.setVisible(true);
        //compose model view and controller
        ObservableCounter model = new AnObservableCounter(); 
        CounterObserver view = new ACounterJTextFieldView(textField);
        model.addObserver(view);
        CounterController controller = new ACounterJButtonController(button);
        controller.setModel(model); 
    }
}