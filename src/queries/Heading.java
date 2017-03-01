package queries;

import turtle.TurtleState;

public class Heading extends TurtleQuery {

	@Override
	public double run(TurtleState state) {
		return state.getAngle();
	}
}
