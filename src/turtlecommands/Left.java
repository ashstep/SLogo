package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Left extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(args[0]);
		double angle = state.getAngle() - args[0];
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}
}
