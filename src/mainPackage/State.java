package mainPackage;

public class State {
	int langlength;
	State[] transitionFns;
	char id;
	boolean accepting;
	
	public State(int length, boolean accept) {
		this.langlength = length;
		this.accepting = accept;
	}
	
	public void addTransition(int index, State s) {
		this.transitionFns[index] = s;	
	}
	public State getTransition(int index) {
		return this.transitionFns[index];
	}

}
