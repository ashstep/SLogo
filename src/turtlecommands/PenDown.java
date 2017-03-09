package turtlecommands;

import parser.Node;
import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class PenDown extends Command {
	
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

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(1);
		return getReturnVal();
	}
}