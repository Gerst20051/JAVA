package exams;

public class ADateUser {
	static int[] numbers = new int[4];
	
	public static void add(int value) {
		if (value < numbers.length) {
			numbers[value]++;
			System.out.println(value+": "+numbers[value]);
		} else {
			System.out.println(value+" is out of range");
		}
	}
	
	public static void printMonthCounts(FullDate[] dates) {
		Histogram months = new AHistogram(12);
		for (int i = 0; i < dates.length; i++) {
			months.addOccurence(dates[i].getMonth());
		}
		for (int i = 0; i < 12; i++) {
			if (0 < months.numOccurences(i)) {
				System.out.println(i+"-->"+months.numOccurences(i));
			}
		}
	}
	
	public static void main (String[] args) {
		/*
		SimpleDate simpleDate1 = new ASimpleDate(10, 2011);
		SimpleDate simpleDate2 = new ASimpleDate (10, 2011);
		FullDate fullDate1 = new AFullDate(20, 10, 2011 );
		FullDate fullDate2 = new AFullDate(20, 10, 2011);
		System.out.println(fullDate1.getMonth());
		System.out.println(fullDate1.getYear());
		System.out.println(simpleDate1.equals(simpleDate2));
		System.out.println(fullDate1.equals((Object) fullDate2));
		System.out.println(fullDate1.equals(fullDate2));
		System.out.println(fullDate1.equals(null));
		*/
		
		/*
		add(1);
		add(2);
		add(2);
		add(3);
		add(0);
		add(0);
		add(0);
		add(0);
		add(1);
		add(2);
		add(4);
		add(5);
		*/
		
		FullDate[] dates = {
				new AFullDate(4, 7, 1776),
				new AFullDate(15, 8, 1947),
				new AFullDate(21, 7, 1969),
				new AFullDate(1, 4, 1642)
		};
		printMonthCounts(dates);
	}
}