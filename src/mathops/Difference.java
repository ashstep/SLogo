package mathops;

import java.util.List;
import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class Difference extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) - args.get(1));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
