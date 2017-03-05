package turtlecommands;

import turtle.ArgumentNumberException;
import java.util.List;
import turtle.TurtleState;
import turtle.Command;

public class Towards extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		double x = args.get(0) - state.getX();
		double y = args.get(1) - state.getY();
		double angle = Math.atan(y/x);
		setReturnVal(angle - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
