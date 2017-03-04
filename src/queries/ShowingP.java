package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class ShowingP extends TurtleQuery {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		setReturnVal(state.isVisible() ? 1 : 0);
		return state;
	}
}
