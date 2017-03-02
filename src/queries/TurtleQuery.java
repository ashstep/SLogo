package queries;

import turtle.TurtleState;
import turtle.ArgumentNumberException;
import turtle.Command;

public abstract class TurtleQuery extends Command {
	
	/**
	 * Asks the turtle for a part of its state
	 * @return The desired part of the turtle's state
	 */
	public abstract double run(TurtleState state) throws ArgumentNumberException;
	
	@Override
	public int getNumArgs(){
		return 0;
	}
}
