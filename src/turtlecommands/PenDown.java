package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenDown extends TurtleCommand {
	
	public PenDown(double...args){
		super(args);
		setNumArgs(0);
	}
	
	@Override
	public TurtleState run(TurtleState state, double... args) throws ArgumentNumberException {
		checkArgs();
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), true, state.isVisible());
	}
}
