package commands;
import turtle.TurtleState;

public class Towards extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(2, args);
		double x = args[0] - state.getX();
		double y = args[1] - state.getY();
		double angle = Math.atan(y/x);
		setReturnVal(angle - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}

}
