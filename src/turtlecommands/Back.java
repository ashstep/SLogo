package turtlecommands;

import turtle.ArgumentNumberException;
import java.util.List;
import turtle.TurtleState;

public class Back extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0));
		return new TurtleState(state.getX() - Math.cos(state.getAngle())*args.get(0), 
				state.getY() - Math.sin(state.getAngle())*args.get(0), 
				state.getAngle(), state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
