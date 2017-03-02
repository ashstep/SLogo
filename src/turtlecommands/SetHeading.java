package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class SetHeading extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		setReturnVal(args[0] - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), args[0], state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 1;
	}	
}
