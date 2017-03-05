package visuals;

import turtle.TurtleState;

public interface ITurtleView {

	/**
	 * Updates the display of the turtle based on parameters found in the new turtleState.
	 * @param newTurtle
	 */
	public void updateTurtle(TurtleState newTurtle);
}
