package queries;

import turtle.TurtleState;

public class ShowingP extends TurtleQuery {

	@Override
	public double run(TurtleState state) {
		return (state.isVisible() ? 1 : 0);
	}
}
