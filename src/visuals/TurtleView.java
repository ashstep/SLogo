package visuals;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import turtle.TurtleState;

public class TurtleView implements ITurtleView{
	
	private Canvas TurtleView;
	private ImageView myTurtle;
	private GraphicsContext myTurtleDrawer;
	
	private double turtleXPos;
	private double turtleYPos;
	private double turtleAngle;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	/* (non-Javadoc)
	 * @see visuals.IITurtleView#initializeGraphicContent()
	 */
	@Override
	public Canvas initializeGraphicContent() {
		TurtleView = new Canvas (WIDTH*0.5, HEIGHT);
		myTurtleDrawer = TurtleView.getGraphicsContext2D();

		return TurtleView;
	}
	
	/* (non-Javadoc)
	 * @see visuals.IITurtleView#initializeTurtle(java.io.File)
	 */
	@Override
	public ImageView initializeTurtle(File myImageFile){
		turtleXPos = WIDTH/4;
		turtleYPos = HEIGHT/2;
		turtleAngle = 0;
				
		String imagepath = myImageFile.toURI().toString();
		myTurtle = new ImageView(new Image(imagepath));
		
		System.out.println("initial turtleXPos:" + turtleXPos);
		System.out.println("initial turtleYPos:" + turtleYPos);
		
		return myTurtle;
	}
	
	/* (non-Javadoc)
	 * @see visuals.IITurtleView#drawTurtlePath(double, double, boolean)
	 */
	@Override
	public void drawTurtlePath(double xPosition, double yPosition, boolean pen){		
		if(pen) {
			myTurtleDrawer.lineTo(xPosition, yPosition);	
			System.out.println("The Pen Is Drawn To"+ "X"+ xPosition + "Y"+ yPosition);
			myTurtleDrawer.stroke();
		}		
		myTurtleDrawer.moveTo(xPosition,yPosition);
	}
	
	/* (non-Javadoc)
	 * @see visuals.IITurtleView#turtleInvisCloak(javafx.scene.image.ImageView, boolean)
	 */
	public void turtleInvisCloak(ImageView turtle, boolean turtleInvis){
		if(turtleInvis){
			turtle.setVisible(true);
		} else{
			turtle.setVisible(false);
		}
	}
	
	/* (non-Javadoc)
	 * @see visuals.IITurtleView#updateTurtle(turtle.TurtleState)
	 */
	@Override
	public void updateTurtle(TurtleState newTurtle){
		System.out.println("updateTurtle called");
		
		//stack.getChildren().remove(myTurtle);
		
		turtleInvisCloak(myTurtle, newTurtle.isVisible());
		
		turtleXPos = newTurtle.getX();
		turtleYPos = newTurtle.getY();

		turtleAngle = newTurtle.getAngle();
		
		//setting our turtle info
		myTurtle.setX(turtleXPos);
		myTurtle.setY(turtleYPos);
		
		//Bug fixed here - needs more work on the subtraction though
		myTurtle.setTranslateX(turtleXPos - 200);
		myTurtle.setTranslateY(turtleYPos - 400);
		
		myTurtle.setRotate(turtleAngle); //how does setRotate work? absolute or relative angles?

		
		
		drawTurtlePath(turtleXPos, turtleYPos, newTurtle.isPenDown());
		System.out.println("The status of the pen is: " + newTurtle.isPenDown() );
		
		//stack.getChildren().add(myTurtle);
		
		System.out.println("final turtleXPos:" + turtleXPos);
		System.out.println("final turtleYPos:" + turtleYPos);
		
		System.out.println("end of updateTurtle ");

	}
}