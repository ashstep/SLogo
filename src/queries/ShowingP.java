package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class ShowingP extends TurtleQuery {

	@Override
	public double run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		return (state.isVisible() ? 1 : 0);
	}
}
