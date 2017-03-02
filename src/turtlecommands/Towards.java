package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Towards extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		double x = args[0] - state.getX();
		double y = args[1] - state.getY();
		double angle = Math.atan(y/x);
		setReturnVal(angle - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
