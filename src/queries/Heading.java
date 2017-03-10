package queries;

import command.ArgumentNumberException;
import command.ZeroArgs;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class Heading extends ZeroArgs {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(state.getAngle());
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		setReturnVal(state.getAngle());
		return getReturnVal();
	}
}
