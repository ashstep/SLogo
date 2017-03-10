package turtlecommands;

import java.util.List;

import command.ArgumentNumberException;
import turtle.TurtleState;

public class Forward extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0));
		double x = state.getX() + Math.cos(state.getAngle()*Math.PI/180)*args.get(0);
		double y = state.getY() + Math.sin(state.getAngle()*Math.PI/180)*args.get(0);
		return new TurtleState(x, y, state.getAngle(), state.isPenDown(), state.isVisible());
	}
}