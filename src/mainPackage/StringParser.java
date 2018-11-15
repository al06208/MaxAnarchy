package mainPackage;

import java.io.*;
import java.util.ArrayList;

public class StringParser {
	private FileReader in;
	private BufferedReader buff;
	private char start;
	int length;
	ArrayList<Character> states = new ArrayList<Character>();
	ArrayList<Character> accepting = new ArrayList<Character>();
	
	//constructor
	public StringParser(String file) {
		try {
			this.in = new FileReader(file);
			this.buff = new BufferedReader(in);
		}
		catch(FileNotFoundException e) {
			System.out.println("DFA text file not found. (StringParser.StringParser)");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	//create dfa
	public DFA create() {
		DFA dfa = new DFA(1, null, null);
		try {
			int length = (buff.readLine().length()-1)/2;
			
			//because we only accept sequential integer languages, 
			//the length of the language determines its content
			char[] stateLine = buff.readLine().toCharArray();
			
			//make an arraylist of characters representing state IDs
			for(int i=1;i<stateLine.length;i+=2) {
				this.states.add(stateLine[i]);
			}
			
			//get the start state
			this.start = buff.readLine().toCharArray()[0];

			//get the accepting states
			stateLine = buff.readLine().toCharArray();
			for(int i=1;i<stateLine.length;i+=2) {
				this.accepting.add(stateLine[i]);
			}

			//begin making state array
			State[] stateArray = new State[states.size()];
			int index = 0;
			for(char c:states) {
				
				//make a state for each given ID
				boolean accept = false;
				
				//check each new state's ID against all the accepting IDs
				for(char d:accepting) {
					if(c==d) {
						accept = true;
					}
				}
				stateArray[index] = new State(length, accept, c);
				
				//increment the index variable
				index++;
			}

			//transition functions
			String thisLine; 
			while(buff.ready()) {
				
				//convert the next line into a char array
				thisLine = buff.readLine();
				char[] lineArray = thisLine.toCharArray();

				//using specific indices determined by the input file's syntax,
				//find the IDs of the start and end states, and the terminal to transition
				//between them
				State from = this.findState(lineArray[1], stateArray);
				State to = this.findState(lineArray[7], stateArray);
				
				//add the transition from the start to the end state
				from.addTransition(Character.getNumericValue(lineArray[3]), to);
				
			}

			//build the DFA
			dfa = new DFA(length, stateArray, this.findState(start, stateArray));

			//avoid memory leaks
			buff.close();
			in.close();
		}
		catch(Exception e) {
			System.out.println("Weird Exception Encountered: Check Your DFA! (StringParser.Create)");
			e.printStackTrace(System.out);
			System.exit(1);
		}
		//bring him home
		return dfa;
	
	}
	
	public State findState(char id, State[] arr) {
		for(State s:arr) {
			if(s.id==id) {
				return s;
			}
		}
		return null;
	}
	
}
