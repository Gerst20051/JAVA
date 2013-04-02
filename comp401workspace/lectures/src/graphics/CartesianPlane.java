package graphics;

public interface CartesianPlane {
	public int getAxesLength();
	public void setAxesLength(int anAxesLength);
	public Line getXAxis();
	public Line getYAxis();
	public StringShape getXLabel();
	public StringShape getYLabel();
}