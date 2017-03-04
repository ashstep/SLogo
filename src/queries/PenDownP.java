package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenDownP extends TurtleQuery {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		setReturnVal(state.isPenDown() ? 1 : 0);
		return state;
	}
}
