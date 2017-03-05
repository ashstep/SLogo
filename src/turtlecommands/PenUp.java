package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class PenUp extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(0);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), false, state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}