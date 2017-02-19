package processing;

import javafx.scene.paint.Color;

public interface ExternalBackendAPI {
		
	/**
	 * Gets the size of the line if changed
	 * @return
	 */
	public double getLineSize();
	
	/**
	 * Gets the new line color if changed
	 * @return
	 */
	public Color getLineColor();
	
	/**
	 * Gets the new background color if changed
	 * @return
	 */
	public Color getBackgroundColor();
	
	/**
	 * Gets the next turtle state if changed
	 * @return
	 */
	public State getTurtleStates();
	
	/**
	 * Gets an error message if thrown
	 * @return
	 */
	public String getErrorMessage();
	
	/**
	 * Tells the command parser what to process
	 * @param command
	 */
	public void processCommand(String command);
}
