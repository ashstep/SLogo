package visuals;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import turtle.TurtleState;

interface ITurtleView {

	/**
	 * Initialize the left size of the BorderPane (the Canvas) which displays the turtle movements
	 * @return TurtleView a Canvas for the turtle to move and draw on
	 */
	Canvas initializeGraphicContent(int width, int height);

	/**
	 * 
	 * @param myImageFile the image selected by the user (Turtle)
	 * @param width the width of the turtleView
	 * @param height the height of the turtleView
	 * @return an ImageView representing the turtle
	 */
	ImageView initializeTurtle(File myImageFile, int width, int height);

	/**
	 * Draws the turtle's path as it moves on the Canvas
	 * @param x the x-coordinate the turtle moves to
	 * @param y the y-coordinate the turtle moves to
	 */
	void drawTurtlePath(double xPosition, double yPosition, boolean pen);

	/**
	 * Sets the turtle to visible/invisible on the Canvas
	 */
	void turtleInvisCloak(ImageView turtle, boolean turtleInvis);

	
	/**
	 * Updates the display of the turtle based on parameters found in the new turtleState.
	 * @param newTurtle the new TurtleState created from parsing a command
	 */
	void updateTurtle(TurtleState newTurtle);

}