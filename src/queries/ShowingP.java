package queries;

import parser.Node;
import processing.Controller;
import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class ShowingP extends TurtleQuery {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		setReturnVal(state.isVisible() ? 1 : 0);
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		setReturnVal(state.isVisible() ? 1 : 0);
		return getReturnVal();
	}
}
