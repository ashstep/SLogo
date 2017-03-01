package queries;

import turtle.TurtleState;

public class PenDownP extends TurtleQuery {

	@Override
	public double run(TurtleState state) {
		return (state.isPenDown() ? 1 : 0);
	}

}
