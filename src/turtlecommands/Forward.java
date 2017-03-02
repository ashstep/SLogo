package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Forward extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		setReturnVal(args[0]);
		double x = state.getX() + Math.cos(state.getAngle())*args[0];
		double y = state.getY() + Math.sin(state.getAngle())*args[0];
		return new TurtleState(x, y, state.getAngle(), state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}