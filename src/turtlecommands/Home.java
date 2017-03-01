package turtlecommands;

import turtle.TurtleState;

public class Home extends TurtleCommand {
	
	public Home(){
		setNumArgs(0);
	}
	
	@Override
	public TurtleState run(TurtleState state, double... args){
		return new TurtleState(0, 0, 0, false, true);
	}
}
