package bmicalculator;

public class ABMIAndOverweightCalculator {
	AnOverweightCalculator calc = new AnOverweightCalculator();
	String measureSystem = "metric";
	double height, weight;

	public double getHeight() {
		return height;
	}
	
	public void setHeight(double input) {
		height = input;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double input) {
		weight = input;
	}
	
	public void setMetric() {
		measureSystem = "metric";
	}
	
	public void setImperial() {
		measureSystem = "imperial";
	}
	
	public String getMeasureSystem() {
		return measureSystem;
	}
	
	public double getBMI() {
		double bmi = 0;
		if (measureSystem == "metric") {
			bmi = weight/(height*height);
		} else if (measureSystem == "imperial") {
			bmi = (weight/(height*height))*703;
		}
		return bmi;
	}
	
	public String getScale() {
		calc.setBMI(getBMI());
		return calc.bmiScale();
	}
	
	public boolean getOverweight() {
		calc.setBMI(getBMI());
		return calc.overweight();
	}
}