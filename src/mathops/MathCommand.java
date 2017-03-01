package mathops;

import turtle.Command;
import turtle.ArgumentNumberException;

/**
 * Math operations for the <code>Turtle</code> to perform
 * @author Vishnu Gottiparthy
 *
 */
public abstract class MathCommand extends Command {
	
	public MathCommand(){
		setNumArgs(1);
	}
	
	/**
	 * Runs a math operation for a math command
	 * @return Result of calculation
	 */
	public abstract double calculate(double... args) throws ArgumentNumberException;
}
