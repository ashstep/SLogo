package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Heading extends TurtleQuery {

	@Override
	public double run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		return state.getAngle();
	}
}
