package queries;

import turtle.TurtleState;
import turtle.Command;

public abstract class TurtleQuery extends Command {
	
	public TurtleQuery(){
		setNumArgs(0);
	}
	
	/**
	 * Asks the turtle for a part of its state
	 * @return The desired part of the turtle's state
	 */
	public abstract double run(TurtleState state);
}
