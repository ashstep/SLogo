package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenDown extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs(args);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), true, state.isVisible());
	}
}
