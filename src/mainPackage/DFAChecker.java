package mainPackage;

import java.util.Scanner;

public class DFAChecker {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//don't move on until they give you a straight answer
		while(args[0].isEmpty()) {
			System.out.println("Please specify a valid file path:\n");
			args[0] = in.nextLine();
		}
		StringParser sp = new StringParser(args[0]);
		DFA brumby = sp.create();
		
		System.out.println("Enter string with length more than 1: ");
		

		String input = in.nextLine();
		in.close();
		
		if(brumby.checkString(input)) {
			System.out.println("String accepted.");
		}
		else {
			System.out.println("String rejected.");
		}
	}

}
