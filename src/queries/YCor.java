package queries;

import turtle.TurtleState;

public class YCor extends TurtleQuery {

	@Override
	public double run(TurtleState state) {
		return state.getY();
	}
}
