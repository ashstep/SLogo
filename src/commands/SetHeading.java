package commands;
import turtle.TurtleState;

public class SetHeading extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		checkArgs(1, args);
		setReturnVal(args[0] - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), args[0], state.isPenDown(), state.isVisible());
	}	
}
