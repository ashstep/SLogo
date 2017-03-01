package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenUp extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs(args);
		setReturnVal(0);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), false, state.isVisible());
	}
}
