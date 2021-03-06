package mainPackage;

public class DFA {
	//private int langLength;
	//private State[] states;
	private State start;
	
public DFA(int length, State[] states, State start) {
	//this.langLength = length;
	//this.states = states;
	this.start=start;
}
	public boolean checkString(String string) {
		char[] chars = string.toCharArray();
		State current = start;
		int next;
		
		for(int i=0; i < chars.length; i++) {
			next = Character.getNumericValue(chars[i]);
			current = current.getTransition(next);
		}
		
		if(current.accepting) {
			return true;
		}
		else {
			return false;
		}
	}
}
