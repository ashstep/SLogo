package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class YCor extends TurtleQuery {

	@Override
	public double run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		return state.getY();
	}
}
