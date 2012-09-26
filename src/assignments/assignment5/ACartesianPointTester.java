package assignment5;

public class ACartesianPointTester {
	public void test (int theX, int theY, double theCorrectRadius, double theCorrectAngle) {
		Point point = new ACartesianPoint(theX, theY);
		//Point point1 = new ACartesianPoint(50, 50); 
		//Point point2 = new APolarPoint(70.5, Math.PI/4);
		double computedRadius = point.getRadius();
		double computedAngle = point.getAngle();
		System.out.println("------------");
		System.out.println("X:" + theX);
		System.out.println("Y:" + theY);
		System.out.println("Expected Radius:" + theCorrectRadius);
		System.out.println("Computed Radius:" + computedRadius);
		System.out.println("Radius Error:" + (theCorrectRadius - computedRadius));
		System.out.println("Expected Angle:" + theCorrectAngle);
		System.out.println("Computed Angle:" + computedAngle);
		System.out.println("Angle Error:" + (theCorrectAngle - computedAngle));
		System.out.println("------------");
	}
	
	public void test () {
		test(10, 0, 10, 0); // 0 degree angle
		test(0, 10, 10, Math.PI / 2); // 90 degree angle
	}
}