package commands;

import turtle.TurtleState;

public class Back extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(1, args);
		setReturnVal(args[0]);
		return new TurtleState(state.getX() - Math.cos(state.getAngle())*args[0], 
				state.getY() - Math.sin(state.getAngle())*args[0], 
				state.getAngle(), state.isPenDown(), state.isVisible());
	}
}
