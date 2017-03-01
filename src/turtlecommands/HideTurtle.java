package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class HideTurtle extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs();
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), false);
	}
}
