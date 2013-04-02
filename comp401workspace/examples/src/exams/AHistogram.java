package exams;

public class AHistogram implements Histogram {
	int[] numbers;
	
	public AHistogram(int aMaxValue) {
		numbers = new int[aMaxValue];
	}
	
	public int getMaxValue() {
		int maxvalue = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > maxvalue) {
				maxvalue = numbers[i];
			}
		}
		return maxvalue;
	}
	
	public void addOccurence(int value) {
		numbers[value]++;
	}
	
	public int numOccurences(int value) {
		return numbers[value];
	}
}