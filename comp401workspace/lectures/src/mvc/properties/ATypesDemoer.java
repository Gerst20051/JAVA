package mvc.properties;

import composite.objects_shapes.ALoan;
import composite.objects_shapes.ALoanPair;
import composite.objects_shapes.APlottedShuttle;
import composite.objects_shapes.CartesianPlane;
import composite.objects_shapes.ClassifiedObjectHolder;
import composite.objects_shapes.Loan;
import composite.objects_shapes.LoanPair;
import composite.objects_shapes.PlottedShuttle;
import graphics.ACartesianPoint;
import graphics.Line;
import graphics.Point;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class ATypesDemoer implements ObjectDemoer {
    int amount;
    Loan loan;
    Point point;
    Line line;
    CartesianPlane cartesianPlane;
    CartesianPlane fancyCartesianPlane;
    LoanPair loanPair;
    PlottedShuttle shuttleLocation;
    ClassifiedObjectHolder objectHolder;    
    public ATypesDemoer(ClassifiedObjectHolder theObjectHolder) {
        objectHolder = theObjectHolder;
        loanPair = new ALoanPair(new ALoan(25000), new ALoan(225000));
        loan = loanPair.getCarLoan();
        amount = loan.getPrincipal();
        shuttleLocation = new APlottedShuttle(50, 100);
        cartesianPlane = shuttleLocation.getCartesianPlane();
        line = cartesianPlane.getXAxis();
        point = new ACartesianPoint(line.getX(), line.getY());
        toInteger();
        
    }
    public Object getObjectHolder() {
        return objectHolder;
    }   
    public void toNull() {
        objectHolder.setKind("Null");
        objectHolder.setObject(null);       
    }
        
    public void toInteger() {
        objectHolder.setKind("Primitive");
        objectHolder.setObject(amount);
        
    }   
    public void toPoint() {
        objectHolder.setKind("Object, Atomic Shape");
        objectHolder.setObject(point);
        
    }   
    public void toLine() {
        objectHolder.setKind("Composite Object, Atomic Shape");
        objectHolder.setObject(line);       
    }
    public void toCartesianPlane() {
        objectHolder.setKind("Composite Object, Composite Shape");
        objectHolder.setObject(cartesianPlane);     
    }
    public void toFancyCartesianPlane() {
        objectHolder.setKind("Composite Object, Composite Shape");
        objectHolder.setObject(fancyCartesianPlane);        
    }
    
    public void toPlottedShuttle() {
        objectHolder.setKind("Composite Object, Composite Shape");
        objectHolder.setObject(shuttleLocation);        
    }
    
    public void toLoan() {
        objectHolder.setKind("Object");
        objectHolder.setObject(loan);
    }   
    public void toLoanPair() {
        objectHolder.setKind("Composite Object");
        objectHolder.setObject(loanPair);
    }
        
    public static void main (String[] args) {
//      ObjectEditor.edit(new ATypesDemoer(new AnObjectHolder()));
        OEFrame oeFrame = ObjectEditor.edit(new ATypesDemoer(new AnObservableClassifiedObjectHolder()));
//      oeFrame.hideMainPanel();
        oeFrame.showTreePanel();
        oeFrame.showDrawPanel();
        oeFrame.setSize(600, 800);
    }
}