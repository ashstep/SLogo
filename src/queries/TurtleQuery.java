package queries;
import turtle.TurtleState;

public abstract class TurtleQuery {
	
	/**
	 * Asks the turtle for a part of its state
	 * @return The desired part of the turtle's state
	 */
	public abstract double run(TurtleState state);
}
