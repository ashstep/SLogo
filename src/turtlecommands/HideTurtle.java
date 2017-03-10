package turtlecommands;

import command.ArgumentNumberException;
import command.ZeroArgs;
import parser.Node;
import turtle.TurtleState;

public class HideTurtle extends ZeroArgs {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(0);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), false);
	}
	
	@Override
	public double findReturnVal(Node n){
		setReturnVal(0);
		return getReturnVal();
	}
}
