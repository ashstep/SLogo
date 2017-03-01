package commands;

import turtle.TurtleState;

public class Home extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args){
		return new TurtleState(0, 0, 0, false, true);
	}
}
