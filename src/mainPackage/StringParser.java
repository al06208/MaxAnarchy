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
			System.out.println("DFA text file not found. (StringParser.StringParser)");
			e.printStackTrace();
		}
	}
	
	//create dfa
	public DFA create() {
		DFA dfa = new DFA(1, null, null);
		try {
			int length = (buff.readLine().length()-1)/2;
			
			System.out.println("length " + length);
			
			char[] stateLine = buff.readLine().toCharArray();
			//make an arraylist of characters representing state IDs
			
			System.out.println("attempting to make state id array");
			
			for(int i=1;i<stateLine.length;i+=2) {
			this.states.add(stateLine[i]);
			}
			
			System.out.println("Success");
			System.out.println("Attempting to get start id");
			
			//get the start state
			this.start = buff.readLine().toCharArray()[0];
			
			System.out.println("Success");
			System.out.println("Attempting to get accepting states");
			
			//get the accepting states
			stateLine = buff.readLine().toCharArray();
			for(int i=1;i<stateLine.length;i+=2) {
				this.accepting.add(stateLine[i]);
			}
			
			System.out.println("Success");
			System.out.println("Attempting to make state array");
			
			//begin making state array
			State[] stateArray = new State[states.size()];
			int index = 0;
			for(char c:states) {
				
				System.out.println("in: "+c);
				
				boolean accept = false;
				for(char d:accepting) {
					
					System.out.println("out: "+d);
					
					if(c==d) {
						
						System.out.println("accept");
						
						accept = true;
					}
				}
				stateArray[index] = new State(length, accept, c);
				
				System.out.println(stateArray[index].id+"\n");
				
				index++;
			}
			
			System.out.println("Success");
			System.out.println(stateArray[0].id);
			System.out.println("Attempting to bind transitions");
			
			//transition functions
			String gurbl; // = buff.readLine();
			while(buff.ready()) {
				gurbl = buff.readLine();
				char[] dubgurb = gurbl.toCharArray();
				//hurmph burmph
				
				System.out.println("Trying to get from");
				
				State from = this.findState(dubgurb[1], stateArray);
				
				System.out.println("Trying to get to");
				
				State to = this.findState(dubgurb[7], stateArray);
				
				System.out.println("Success");
				System.out.println(from.id);
				System.out.println(to.id);
				System.out.println(dubgurb[3]);
				System.out.println("Success?");
				
				from.addTransition(Character.getNumericValue(dubgurb[3]), to);
				
			}
			
			System.out.println();
			System.out.println("Success");
			System.out.println("Attempting to build DFA");
			
			//build the DFA
			dfa = new DFA(length, stateArray, this.findState(start, stateArray));
			
			System.out.println("Success");
			
			//bring him home
			buff.close();
			in.close();
		}
		catch(Exception e) {
			System.out.println("shit");
			System.out.println(e.toString()+"\n"+e.getMessage());
			e.printStackTrace(System.out);
			System.exit(1);
		}
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
