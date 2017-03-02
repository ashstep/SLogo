package visuals;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import turtle.TurtleState;

/**
 * This is the class which controls the display of the GUI. It puts together all
 * the components that make up the screen.
 * 
 * @author Harry Liu, Christian Martindale
 **/

public class View implements ExternalUserInterface {

	private BorderPane BP;
	private ScrollPane SP;
	private Scene theScene;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private final int SPACING = 10;
	private InputView inputView;
	private Button help;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	private Canvas TurtleView;
	private GraphicsContext gc;
	private String helpUrl = 
			"http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php";
	private GraphicsContext myTurtleDrawer;
	private Image myTurtleImage;
	private ImageView myTurtle;
	private double turtleXPos;
	private double turtleYPos;
	private double turtleAngle;
	private ResourceBundle myResourceBundle;
	
	public View(Button submit, ResourceBundle myResourceBundle) {

		inputView = new InputView();
		this.myResourceBundle = myResourceBundle;
		
		BP = new BorderPane();
		SP = new ScrollPane();
		SP.setContent(initializeRightMenu(submit));
		SP.setHbarPolicy(ScrollBarPolicy.NEVER);
		SP.setFitToWidth(true);

		inputView.setBackground(backgroundColorChooser, BP);
		BP.setRight(SP);
		TurtleView = initializeGraphicContent();
		inputView.setStroke(strokeColorChooser, gc);
		BP.setLeft(TurtleView);

		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
	}
	
	/**
	 * Initialize the right side which has all the controls for the GUI
	 * @return
	 */
	private VBox initializeRightMenu(Button submit) {
		VBox RightMenu = new VBox(SPACING);
		
		help = new Button(myResourceBundle.getString("HelpPrompt"));
		help.setOnAction(e->{
			displayHelpPage();
		});
		Label backgroundLabel = new Label(myResourceBundle.getString("BackgroundColorPrompt"));
		Label lineColorLabel = new Label (myResourceBundle.getString("LineColorPrompt"));
		Label helpLabel = new Label(myResourceBundle.getString("HelpButtonPrompt"));

		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();	
		
		RightMenu.getChildren().addAll(inputView.initializeTextArea(submit, myResourceBundle), backgroundLabel, backgroundColorChooser, lineColorLabel, strokeColorChooser, helpLabel, help);
		return RightMenu;
	}
	
	/**
	 * Initialize the left size of the BorderPane (the Canvas) which displays the turtle movements
	 * @return TurtleView
	 */
	private Canvas initializeGraphicContent() {
		TurtleView = new Canvas (WIDTH*0.5, HEIGHT);
		myTurtleDrawer = TurtleView.getGraphicsContext2D();
		turtleXPos = WIDTH/4;
		turtleYPos = HEIGHT/2;
		turtleAngle = 0;
		
		myTurtle = new ImageView(myTurtleImage);
		return TurtleView;
	}
	
	/**
	 * Filler method just to test if the line is displayed properly
	 * @param x
	 * @param y
	 */
	private void drawTurtlePath(double xPosition, double yPosition, boolean pen){
		myTurtleDrawer.moveTo(xPosition,yPosition);
		if(pen) {
			myTurtleDrawer.lineTo(xPosition, yPosition);		
			myTurtleDrawer.stroke();
		}
	}
	
	/**
	 * Opens up the help page in a web browser. If there are errors, display error message
	 */
	private void displayHelpPage(){
		try{
			Desktop.getDesktop().browse(new URI(helpUrl));
		}
		catch (IOException e){
			System.out.println(myResourceBundle.getString("NotificationError"));
		}
		catch (URISyntaxException e){
			System.out.println(myResourceBundle.getString("NotificationError"));
		}
	}

	public void updateTurtle(TurtleState newTurtle){
		turtleXPos = newTurtle.getX();
		turtleYPos = newTurtle.getY();
		turtleAngle = newTurtle.getAngle();
		myTurtle.setX(turtleXPos);
		myTurtle.setY(turtleYPos);
		myTurtle.setRotate(turtleAngle); //how does setRotate work? absolute or relative angles?
		
		drawTurtlePath(turtleXPos, turtleYPos, newTurtle.isPenDown());
		
	}
	
	/**
	 * Gets the <code>Scene</code> for use in the <code>Stage</code>
	 * @return The <code>Scene</code> for the <code>View</code>
	 */
	public Scene getScene(){
		return theScene;
	}
	
	/**
	 * Gets the command string from the <code>InputView</code>
	 * @return The command string
	 */
	public String getCommandString(){
		return inputView.getCommandString();
	}
	
	@Override
	public void newTurtleState() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * shows an error message popup
	 * @param whatHappened the message that will be displayed in the error popup
	 */
	@Override
	public void createErrorMessage(String whatHappened) {
		Alert alert = new Alert(AlertType.ERROR, "Error: "+ whatHappened);
		alert.showAndWait();
	}

}