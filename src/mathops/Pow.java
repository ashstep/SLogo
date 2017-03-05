package mathops;

import java.util.List;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.TurtleState;

public class Pow extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.pow(args.get(0), args.get(1)));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 2;
	}

}
