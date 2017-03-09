package processing;

import javafx.scene.paint.Color;
import turtle.TurtleState;

public interface ExternalBackendAPI {
		
	/**
	 * @return the current width of the line
	 */
	public double getLineSize();
	
	/**
	 * @return the current color of the line
	 */
	public Color getLineColor();
	
	/**
	 * @return the current color of the background of turtleView
	 */
	public Color getBackgroundColor();
	
	/**
	 * 
	 * @return the current State of the Turtle
	 */
	public TurtleState getTurtleStates();
	
	/**
	 * Gets an error message String if thrown
	 * @return an error message String detailing what went wrong
	 */
	public String getErrorMessage();
	
	/**
	 * Tells the command parser what command to parse
	 * @param command the user input String representing a command
	 */
	public void processCommand(String command);
}
