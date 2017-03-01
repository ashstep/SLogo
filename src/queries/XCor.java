package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class XCor extends TurtleQuery {

	@Override
	public double run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		return state.getX();
	}
}
