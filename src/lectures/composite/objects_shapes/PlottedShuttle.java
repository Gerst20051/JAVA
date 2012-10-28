package composite.objects_shapes;

public interface PlottedShuttle {
    public CartesianPlane getCartesianPlane();
    public ShuttleImage getShuttleImage();
    public int getShuttleX();
    public void setShuttleX(int newVal);
    public int getShuttleY();
    public void setShuttleY(int newVal);
}