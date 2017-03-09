package turtlecommands;

import turtle.ArgumentNumberException;
import java.util.List;

import turtle.TurtleState;

public class Left extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0));
		double angle = state.getAngle() - args.get(0);
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
	
}
