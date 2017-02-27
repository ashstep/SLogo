package commands;
import turtle.TurtleState;

public interface TurtleCommand {
	
	/**
	 * Takes in current <code>TurtleState</code> and finds new one
	 * @param state Current state of <code>Turtle</code>
	 * @return Next <code>TurtleState</code>
	 */
	public TurtleState run(TurtleState state, double... args);
}
