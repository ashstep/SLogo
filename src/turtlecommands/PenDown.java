package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenDown extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(1);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), true, state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}