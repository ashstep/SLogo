package mathops;

import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;
import java.util.List;

public class Sin extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.sin(args.get(0)));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
