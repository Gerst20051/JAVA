package bmicalculator;

public class ABMIAndOverweightCalculator {
double height;
double weight;

public double getHeight() {
return height;
}
public void setHeight(double newHeight) {
height = newHeight;
}
public double getWeight() {
return weight;
}
public void setWeight(double newWeight) {
weight = newWeight;
}

public double getBmi() {
return weight/(height*height);
}

public boolean isOverweight() {
AnOverweightCalculator overweightCalc = 
new AnOverweightCalculator();

overweightCalc.setBmi(getBmi());
return overweightCalc.isOverweight();

}

}