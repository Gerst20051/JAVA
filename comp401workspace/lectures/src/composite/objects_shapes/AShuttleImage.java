package composite.objects_shapes;

import bus.uigen.ObjectEditor;

public class AShuttleImage implements ShuttleImage {
    int x, y;
    public static final String SHUTTLE_IMAGE_FILE_NAME = "shuttle2.jpg";
    public static final int SHUTTLE_IMAGE_HEIGHT = 25;    
    public int getX() {return x;}
    public void setX(int newX) {x = newX;}
    public int getY() { return y; }
    public void setY(int newY) {y = newY;}
    public String getImageFileName() {return SHUTTLE_IMAGE_FILE_NAME;}  
    public int getHeight() {
      return SHUTTLE_IMAGE_HEIGHT;
    }
    
    public static void main (String args[]) {
     ShuttleImage shuttle = new AShuttleImage();
     ObjectEditor.edit(shuttle);
     shuttle.setX(100);
     shuttle.setY(100);
    }
}