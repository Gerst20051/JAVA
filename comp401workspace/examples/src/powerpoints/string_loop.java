package powerpoints;

import console.Console;

public class string_loop {
    public static void main (String args[]) {
		System.out.println("Number of Strings:");
		int numElements = Console.readInt(); // reads the next line as integer
		System.out.println("Please enter " + numElements + " strings");
		String[] strings = new String[numElements]; // dynamic array
		for (int elementNum = 0;  elementNum < numElements; elementNum++)
			strings[elementNum] = Console.readString();       
		
		/* This loop uses the array input
		** in the previous loop*/
		for ( int elementNum = 0; elementNum < strings.length; elementNum++)
		 	System.out.println(strings[elementNum]);
		
		String s =  strings[0]; // unsafe
		for (int i=0; i<s.length(); i++)
			System.out.println(s.charAt(i));
    }
}