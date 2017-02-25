package commands;
import turtle.TurtleState;

public interface Command {
	
	/**
	 * Takes in current <code>TurtleState</code> and finds new one
	 * @param state Current state of <code>Turtle</code>
	 * @return Next <code>TurtleState</code>
	 */
	public TurtleState run(TurtleState state);
	
	/**
	 * Returns the new x-coordinate of the <code>Turtle</code>
	 * @return The new x-coordinate of the <code>Turtle</code>
	 */
	public double getX();
	
	/**
	 * Returns the new y-coordinate of the <code>Turtle</code>
	 * @return The new y-coordinate of the <code>Turtle</code>+
	 */
	public double getY();
	
	/**
	 * Returns the new angle of the <code>Turtle</code>
	 * @return The new angle of the <code>Turtle</code>
	 */
	public double getAngle();
}
