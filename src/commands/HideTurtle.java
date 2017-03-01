package commands;

import turtle.TurtleState;

public class HideTurtle extends TurtleCommand {
	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(1, args);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), false);
	}
}
