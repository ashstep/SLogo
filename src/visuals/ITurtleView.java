package visuals;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import turtle.TurtleState;

interface ITurtleView {

	/**
	 * Initialize the left size of the BorderPane (the Canvas) which displays the turtle movements
	 * @return TurtleView
	 */
	Canvas initializeGraphicContent();

	ImageView initializeTurtle(File myImageFile);

	/**
	 * Filler method just to test if the line is displayed properly
	 * @param x
	 * @param y
	 */
	void drawTurtlePath(double xPosition, double yPosition, boolean pen);

	/**
	 * sets the turtle to visible/invisible
	 */
	void turtleInvisCloak(ImageView turtle, boolean turtleInvis);

	
	/**
	 * Updates the display of the turtle based on parameters found in the new turtleState.
	 * @param newTurtle
	 */
	void updateTurtle(TurtleState newTurtle);

}