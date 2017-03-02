package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenUp extends TurtleCommand {
	
	public PenUp(double...args){
		super(args);
		setNumArgs(0);
	}
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(0);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), false, state.isVisible());
	}
}
