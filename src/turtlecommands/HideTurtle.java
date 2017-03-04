package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class HideTurtle extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), false);
	}
	
	@Override
	public int getNumArgs(){
		return 0;
	}
}
