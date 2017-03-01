package queries;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class PenDownP extends TurtleQuery {

	@Override
	public double run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		return (state.isPenDown() ? 1 : 0);
	}

}
