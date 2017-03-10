package turtlecommands;
import command.ArgumentNumberException;
import turtle.TurtleState;

public class Constant extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(1);
		return new TurtleState(state.getX(), state.getY(), 
				state.getAngle(), true, state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
