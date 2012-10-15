package exams;

public class AFullDate extends ASimpleDate implements FullDate {
	int day;
	
	public AFullDate (int aDay, int aMonth, int aYear) {
		day = aDay;
		month = aMonth;
		year = aYear;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public boolean equals(Object otherObject) {
		System.out.print("AFullDate equals called.");
		return super.equals((FullDate) otherObject) && day == ((FullDate) otherObject).getDay();
	}
}