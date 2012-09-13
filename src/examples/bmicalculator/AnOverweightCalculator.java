package bmicalculator;

public class AnOverweightCalculator {

double bmi;

public double getBmi() {
return bmi;
}

public void setBmi(double newBmi) {
bmi = newBmi;

}
public boolean isOverweight() {
if (bmi > 25)
return true;
else
return false;
}
}