package turtlecommands;

import turtle.TurtleState;
import turtle.ArgumentNumberException;
import turtle.Command;

/**
 * Command to modify the state of a <code>Turtle</code>
 * @author Vishnu Gottiparthy
 *
 */
public abstract class TurtleCommand extends Command {
	
	private double returnVal;

	/**
	 * Takes in current <code>TurtleState</code> and finds new one
	 * @param state Current state of <code>Turtle</code>
	 * @return Next <code>TurtleState</code>
	 */
	public abstract TurtleState run(TurtleState state) throws ArgumentNumberException;
	
	/**
	 * Sets the return value of the command
	 * @param val Value to set as return value
	 */
}
