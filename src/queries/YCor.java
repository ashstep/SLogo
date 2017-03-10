package queries;

import command.ArgumentNumberException;
import command.ZeroArgs;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class YCor extends ZeroArgs {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(state.getY());
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		setReturnVal(state.getY());
		return getReturnVal();
	}
}
