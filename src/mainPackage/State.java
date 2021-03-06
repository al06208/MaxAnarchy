package mainPackage;

public class State {
	int langlength;
	State[] transitionFns;
	char id;
	boolean accepting;
	
	public State(int length, boolean accept, char id) {
		this.langlength = length;
		this.transitionFns = new State[length];
		this.id = id;
		this.accepting = accept;
	}
	
	public void addTransition(int index, State s) {
		this.transitionFns[index] = s;	
	}
	
	public State getTransition(int index) {
		return this.transitionFns[index];
	}

}
