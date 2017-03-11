package queries;

import command.ZeroArgs;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class YCor extends ZeroArgs {

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		setReturnVal(state.getY());
		return getReturnVal();
	}
}
