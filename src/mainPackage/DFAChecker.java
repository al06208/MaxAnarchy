package mainPackage;

import java.util.Scanner;

public class DFAChecker {

	public static void main(String[] args) {
		StringParser sp = new StringParser(args[0]);
		DFA brumby = sp.export();
		Scanner grumb = new Scanner(System.in);
		String input = grumb.next();
		grumb.close();
		if(brumby.checkString(input)) System.out.println("String accepted.");
		else System.out.println("String rejected.");
	}

}
