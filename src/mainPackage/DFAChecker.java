package mainPackage;

import java.util.Scanner;

public class DFAChecker {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String path;
		
		if(args.length==0) {
			System.out.println("Please specify a valid file path:\n");
			path = in.nextLine();
		}
		else {
			path=args[0];
		}
		
		StringParser sp = new StringParser(path);
		DFA dfa = sp.create();
		
		System.out.println("Enter string with length more than 1: ");
		

		String input = in.nextLine();
		in.close();
		
		if(dfa.checkString(input)) {
			System.out.println("String accepted.");
		}
		else {
			System.out.println("String rejected.");
		}
	}

}
