package commands;

import turtle.TurtleState;

public class Left extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(1, args);
		setReturnVal(args[0]);
		double angle = state.getAngle() - args[0];
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}
}
