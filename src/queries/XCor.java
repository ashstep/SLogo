package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class XCor extends TurtleQuery {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(state.getX());
		return state;
	}
}
