package queries;

import parser.Node;
import processing.Controller;
import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class YCor extends TurtleQuery {

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
