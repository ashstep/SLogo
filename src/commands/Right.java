package commands;

import turtle.TurtleState;

public class Right extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(1, args);
		setReturnVal(args[0]);
		return new TurtleState(state.getX(), state.getY(), state.getAngle() + args[0]);
	}
}
