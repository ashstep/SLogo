package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class SetHeading extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs(args);
		setReturnVal(args[0] - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), args[0], state.isPenDown(), state.isVisible());
	}	
}
