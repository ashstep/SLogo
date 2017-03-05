package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Heading extends TurtleQuery {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(state.getAngle());
		return state;
	}
}
