package turtlecommands;

import turtle.ArgumentNumberException;
import java.util.List;
import turtle.TurtleState;
import turtle.Command;

public class SetHeading extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), args.get(0), state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 1;
	}	
}
