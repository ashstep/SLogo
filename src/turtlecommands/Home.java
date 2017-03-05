package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class Home extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		return new TurtleState(0, 0, 0, false, true);
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
