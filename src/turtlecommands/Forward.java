package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Forward extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(args[0]);
		double x = state.getX() + Math.cos(state.getAngle())*args[0];
		double y = state.getY() + Math.sin(state.getAngle())*args[0];
		return new TurtleState(x, y, state.getAngle(), state.isPenDown(), state.isVisible());
	}
}
