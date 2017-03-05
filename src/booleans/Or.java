package booleans;

import java.util.List;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.TurtleState;

public class Or extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) != 0 || args.get(1) != 0 ? 1 : 0);
		return state;
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
