package turtlecommands;

import turtle.TurtleState;

public class Home extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state){
		return new TurtleState(0, 0, 0, false, true);
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
