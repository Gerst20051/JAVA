package exams;

public interface Histogram {
	public void addOccurence(int value);
	public int numOccurences(int value);
	public int getMaxValue();
}