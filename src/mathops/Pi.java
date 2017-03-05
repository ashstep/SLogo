package mathops;

import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.TurtleState;

public class Pi extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(Math.PI);
		return state;
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
