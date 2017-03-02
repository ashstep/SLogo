package mathops;

import turtle.Command;
import turtle.ArgumentNumberException;

/**
 * Math operations for the <code>Turtle</code> to perform
 * @author Vishnu Gottiparthy
 *
 */
public abstract class MathCommand extends Command {
	
	/**
	 * Runs a math operation for a math command
	 * @return Result of calculation
	 */
	public abstract double calculate() throws ArgumentNumberException;
}
