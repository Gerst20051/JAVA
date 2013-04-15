package avlTree;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception
	{
		Tree tl = new Tree();
		
		int k = 0; // k for arguments
		int i, j; // i for insert j for search
		if (k == 0)
		{
			try {
				BufferedReader insert = new BufferedReader(new FileReader(args[0]));
				String thisLine;
				while ((thisLine = insert.readLine()) != null)
				{
					i = Integer.parseInt(thisLine);
					// System.out.println(i);
					tl.insert(i);
				}
				k = 1;
			}
			catch (IOException e) {
				System.err.println("Error: " + e);
			}
		}
		if (k==1) {
			try {
				String thisLine2;
				BufferedReader search = new BufferedReader(new FileReader(args[1]));
				BufferedWriter out = new BufferedWriter(new FileWriter(args[2]));
				while ((thisLine2 = search.readLine()) != null)
				{
					j = Integer.parseInt(thisLine2);
					if (tl.search(j))
					{
						out.write(""+j);
						out.write("\n");
					}
				}
				out.close();
			} catch (IOException e) {
				System.err.println("Error: " + e);
			}
		}

		System.out.println("Tree looks like: " + tl);
		System.out.println("Tree depth: " + tl.depth());
	}
}