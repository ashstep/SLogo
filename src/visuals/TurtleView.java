package visuals;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import turtle.TurtleState;

public class TurtleView implements ITurtleView{
	
	private StackPane stack;
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
				
		//myTurtle = new ImageView(myTurtleImage);
		String imagepath = myImageFile.toURI().toString();
		myTurtle = new ImageView(new Image(imagepath));
		System.out.println("stack is" + stack);
		System.out.println("myTurtle is" + myTurtle);
		
		return myTurtle;
	}
	
	/* (non-Javadoc)
	 * @see visuals.IITurtleView#drawTurtlePath(double, double, boolean)
	 */
	@Override
	public void drawTurtlePath(double xPosition, double yPosition, boolean pen){
		myTurtleDrawer.moveTo(xPosition,yPosition);
		if(pen) {
			myTurtleDrawer.lineTo(xPosition, yPosition);		
			myTurtleDrawer.stroke();
		}
	}
	
	/* (non-Javadoc)
	 * @see visuals.IITurtleView#turtleInvisCloak(javafx.scene.image.ImageView, boolean)
	 */
	@Override
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
		System.out.println("initial turtleXPos:" + turtleXPos);
		System.out.println("initial turtleYPos:" + turtleYPos);

		turtleAngle = newTurtle.getAngle();
		
		//setting our turtle info
		myTurtle.setX(turtleXPos);
		myTurtle.setY(turtleYPos);
		
		//Bug fixed here - needs more work on the subtraction though
		myTurtle.setTranslateX(turtleXPos);
		myTurtle.setTranslateY(turtleYPos);
		
		myTurtle.setRotate(turtleAngle); //how does setRotate work? absolute or relative angles?

		//System.out.println(r.getBoundsInParent());
		//stack.getChildren().remove(r);
		//stack.getChildren().removeAll(r, TurtleView);

		/*
		stack.getChildren().add(updated);
		System.out.println("got here");
		System.out.println(r.getBoundsInParent());
		System.out.println(updated.getBoundsInParent());
		System.out.println("got here2");*/

		//originally here:
		/*r.setX(turtleXPos);
		r.setY(turtleYPos);
		stack.getChildren().add(r);*/
		//.getChildren().addAll(r, TurtleView);

		//System.out.println(r.getBoundsInParent());
		//BP.setLeft(stack);
		
		drawTurtlePath(turtleXPos, turtleYPos, newTurtle.isPenDown());
		
		//stack.getChildren().add(myTurtle);
		
		System.out.println("final turtleXPos:" + turtleXPos);
		System.out.println("final turtleYPos:" + turtleYPos);
		
		System.out.println("end of updateTurtle ");

	}
}
