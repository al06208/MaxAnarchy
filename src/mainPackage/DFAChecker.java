package mainPackage;

import java.util.Scanner;

public class DFAChecker {

	public static void main(String[] args) {
		StringParser sp = new StringParser(args[0]);
		DFA brumby = sp.create();
		
		System.out.println("Enter string with length more than 1: ");
		
		Scanner grumb = new Scanner(System.in);
		String input = grumb.nextLine();
		grumb.close();
		
		if(brumby.checkString(input)) {
			System.out.println("String accepted.");
		}
		else {
			System.out.println("String rejected.");
		}
	}

}
