package commandpackage;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ARoundedTextField extends Panel implements TextComponentInterface, KeyListener{
	int eventId;
    StringBuffer stringBuffer;
    List<ActionListener> actionListeners = new ArrayList();
    public static final int X_OFFSET = 10;
    public static final int Y_OFFSET = 20;
    public static final int ROUND_RECTANGLE_ARC_WIDTH = 20;
    public static final int ROUND_RECTANGLE_ARC_HEIGHT = 20;
    public ARoundedTextField() {
        stringBuffer = new StringBuffer("");
        this.addKeyListener(this);
        this.setFocusable(true);        
    }
    public void addActionListener(ActionListener actionListener) {
        if (actionListeners.contains(actionListener)) return;
        actionListeners.add(actionListener);
    }
    public void notifyActionListeners() {
        for (int index = 0; index <actionListeners.size(); index++) {
            actionListeners.get(index).actionPerformed(new ActionEvent(this, eventId, stringBuffer.toString()));
        }
        eventId++;  
    }
    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == '\n') {
            notifyActionListeners();
            return;
        }
        stringBuffer.append(keyEvent.getKeyChar()); 
        repaint();  
    }
    public void paint(Graphics g) {
        super.paint(g); 
        g.drawRoundRect(0, 0, getWidth(), getHeight(), ROUND_RECTANGLE_ARC_WIDTH, ROUND_RECTANGLE_ARC_WIDTH);
        g.drawString(stringBuffer.toString(), X_OFFSET, Y_OFFSET);      
    }
    public String getText() {
        return stringBuffer.toString();
    }   
    public void setText(String newVal) {        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(newVal);
        repaint();
    }
    public void keyPressed(KeyEvent arg0) {         
    }
    public void keyReleased(KeyEvent arg0) {
        
    }
    public static void main (String[] args) {
        Frame frame = new Frame();
        frame.setLayout(new GridLayout(2, 1));
        ARoundedTextField field1 = new ARoundedTextField();
        ARoundedTextField field2 = new ARoundedTextField();
        field1.setText("hello");
        field2.setText("ni hao");
        frame.add(field1);
        frame.add(field2);
        frame.setSize(200, 100);
        frame.setVisible(true);     
    }
}
