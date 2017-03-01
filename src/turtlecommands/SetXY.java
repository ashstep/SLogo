package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class SetXY extends TurtleCommand {

	public SetXY(){
		setNumArgs(2);
	}
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs(args);
		double x = state.getX() - args[0];
		double y = state.getY() - args[1];
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		setReturnVal(dist);
		return new TurtleState(args[0], args[1], state.getAngle(), state.isPenDown(), state.isVisible());
	}
}
