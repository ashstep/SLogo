package commands;

import turtle.TurtleState;

public class SetXY extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(2, args);
		double x = state.getX() - args[0];
		double y = state.getY() - args[1];
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		setReturnVal(dist);
		return new TurtleState(args[0], args[1], state.getAngle(), state.isPenDown(), state.isVisible());
	}
}
