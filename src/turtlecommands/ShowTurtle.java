package turtlecommands;

import command.ArgumentNumberException;
import command.ZeroArgs;
import parser.Node;
import turtle.TurtleState;

public class ShowTurtle extends ZeroArgs {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), true);
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(1);
		return getReturnVal();
	}
}
