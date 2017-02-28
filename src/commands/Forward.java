package commands;
import turtle.TurtleState;

public class Forward extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(1, args);
		setReturnVal(args[0]);
		double x = state.getX() + Math.cos(state.getAngle())*args[0];
		double y = state.getY() + Math.sin(state.getAngle())*args[0];
		return new TurtleState(x, y, state.getAngle(), state.isPenDown(), state.isVisible());
	}
}
