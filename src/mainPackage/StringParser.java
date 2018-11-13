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
		catch(Exception e) {
			System.out.println("fuck");
			System.out.println(e.toString()+"\n"+e.getMessage());
		}
	}
	//dfa maker
	public DFA export() {
		DFA wumpus = new DFA(0, null, null);
		try {
			int length = (buff.readLine().length()-1)/2;
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
				boolean accept = false;
				for(char d:accepting) {
					if(c==d) {
						accept = true;
					}
				}
				stateArray[index] = new State(c, accept);
				index++;
			}
			//transition functions
			String gurbl = buff.readLine();
			while(!(gurbl.equals(null))) {
				char[] gurb = gurbl.toCharArray();
				//hurmph burmph
				this.findState(gurb[1], stateArray).addTransition(Character.getNumericValue(gurb[3]), this.findState(gurb[7], stateArray));
				gurbl = buff.readLine();
			}
			//build the DFA
			wumpus = new DFA(length, stateArray, this.findState(start, stateArray));
			//bring him home
			buff.close();
			in.close();
		}
		catch(Exception e) {
			System.out.println("fuck");
			System.out.println(e.toString()+"\n"+e.getMessage());
			System.exit(1);
		}
		return wumpus;
	
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
