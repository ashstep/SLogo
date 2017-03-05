package mathops;

import java.util.List;
import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class ATan extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.atan(args.get(0)));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
