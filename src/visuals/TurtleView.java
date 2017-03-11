package visuals;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import processing.Controller;
import turtle.TurtleState;

/**
 * 
 * @author Harry Liu and Christian Martindale
 * The Canvas on which a SLogo turtle moves and displays
 * its path. Part of a View.
 *
 */
public class TurtleView implements ITurtleView{
	
	private Canvas TurtleView;
	private ImageView myTurtle;
	private GraphicsContext myTurtleDrawer;
	
	private double turtleXPos;
	private double turtleYPos;
	private double turtleAngle;
	
	public static int WIDTH;
	public static int HEIGHT;

	/** 
	 * @see visuals.IITurtleView#initializeGraphicContent()
	 */
	@Override
	public Canvas initializeGraphicContent(int width, int height) {
		WIDTH = width/2;
		HEIGHT = height;
		TurtleView = new Canvas(WIDTH, HEIGHT);
		myTurtleDrawer = TurtleView.getGraphicsContext2D();
		return TurtleView;
	}
	
	/** 
	 * @see visuals.IITurtleView#initializeTurtle(java.io.File)
	 */
	@Override
	public ImageView initializeTurtle(File myImageFile){
		
		String imagepath = myImageFile.toURI().toString();
		myTurtle = new ImageView(new Image(imagepath));

		turtleXPos = WIDTH/2;
		turtleYPos = HEIGHT/2;
		Tooltip.install(myTurtle, new Tooltip("X-Pos = " + turtleXPos + " Y-Pos = " + turtleYPos));
		turtleAngle = 0;
		
		myTurtle.setX(turtleXPos);
		myTurtle.setY(turtleYPos);
		
		updateTurtle(Controller.getTurtleState());
		
		return myTurtle;
	}
	
	/** 
	 * @see visuals.IITurtleView#drawTurtlePath(double, double, boolean)
	 */
	@Override
	public void drawTurtlePath(double xPosition, double yPosition, boolean pen){
		if(pen) {
			myTurtleDrawer.lineTo(xPosition, yPosition);	
			myTurtleDrawer.stroke();
		}
		myTurtleDrawer.moveTo(xPosition,yPosition);
	}
	
	/**
	 * @see visuals.IITurtleView#turtleInvisCloak(javafx.scene.image.ImageView, boolean)
	 */
	public void turtleInvisCloak(ImageView turtle, boolean turtleInvis){
		if(turtleInvis){
			turtle.setVisible(true);
		} else{
			turtle.setVisible(false);
		}
	}
	
	/** 
	 * @see visuals.IITurtleView#updateTurtle(turtle.TurtleState)
	 */
	@Override
	public void updateTurtle(TurtleState newTurtle){		
		turtleInvisCloak(myTurtle, newTurtle.isVisible());
		
		turtleXPos = newTurtle.getX() + WIDTH/2;
		turtleYPos = HEIGHT/2 - newTurtle.getY() + myTurtle.getBoundsInLocal().getHeight();
		turtleAngle = newTurtle.getAngle();
		
		//setting our turtle info
		myTurtle.setX(turtleXPos);
		myTurtle.setY(turtleYPos);
		
		myTurtle.setTranslateX(turtleXPos - WIDTH/2);
		myTurtle.setTranslateY(turtleYPos - HEIGHT/2);
		myTurtle.setRotate(turtleAngle); 
		
		Tooltip.install(myTurtle, new Tooltip("X-Pos = " + turtleXPos + " Y-Pos = " + turtleYPos));
			
		drawTurtlePath(turtleXPos, turtleYPos, newTurtle.isPenDown());
	}
}