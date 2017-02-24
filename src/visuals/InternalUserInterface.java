package visuals;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface InternalUserInterface {

	public void displayTurtleState(List turtleStates); // takes in List of
															// turtle States,
															// operates on its
															// own Scene

	public void drawLine(int x1, int x2, int y1, int y2); // draws a line
															// between (x1, x2)
															// and (y1, y2)
	

	/**
	 * takes a Color from the turtle's State and sets the background to
	 * that color call public method in Controller that returns either a new
	 * color or null
	 */
	public void setBackgroundColor();

	public void setLineColor();
	
	public void setLineSize();
	
	/**
	 * takes in an image and sets the Turtle's image in the Scene
	 */
	public void setTurtleImage(Image turtlePic);
	
	/**
	 * updates the text box with history commands in the UI
	 */
	public void updateHistory(String history);
	
	/**
	 * shows a pop-up window with a list of commands
	 */
	public void displayHelpPage();
	
	/**
	 * tells which language resource bundle to use
	 */
	public void setLanguage();
	
	
}
