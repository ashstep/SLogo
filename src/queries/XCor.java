package queries;

import turtle.TurtleState;

public class XCor extends TurtleQuery {

	@Override
	public double run(TurtleState state) {
		return state.getX();
	}
}
