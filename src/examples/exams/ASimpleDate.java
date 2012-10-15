package exams;

public class ASimpleDate implements SimpleDate {
	int month;
	int year;
	
	public ASimpleDate() {}
	public ASimpleDate(int aMonth, int aYear) {
		month = aMonth;
		year = aYear;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public boolean equals(SimpleDate otherDate) {
		System.out.print("ASimpleDate equals called.");
		return month == otherDate.getMonth() && year == otherDate.getYear();
	}
}