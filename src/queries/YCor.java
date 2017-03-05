package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class YCor extends TurtleQuery {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(state.getY());
		return state;
	}
}
