package bmicalculator;

public class AnOverweightCalculator {
	double bmi;
	
	public double getBMI() {
		return bmi;
	}
	
	public void setBMI(double input) {
		bmi = input;
	}
	
	public boolean overweight() {
		return (getBMI() >= 25);
	}
	
	public String bmiScale() {
		double bmi = getBMI();
		if (bmi < 18)
			return "underweight";
		else if (18 <= bmi && bmi < 18.6)
			return "thin for your height";
		else if (18.6 <= bmi && bmi < 25)
			return "healthy weight";
		else if (25 <= bmi && bmi < 30)
			return "overweight for your height";
		else if (30 <= bmi)
			return "obese";
		else return "error";
	}
}