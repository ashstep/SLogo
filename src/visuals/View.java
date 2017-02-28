package visuals;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	private Stage theStage;
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private int SPACING = 10;
	private InputView inputView;
	private Button help;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	private Canvas TurtleView;
	private GraphicsContext gc;
	private String helpUrl = "http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php";

	public View(Stage myStage) {

		inputView = new InputView(WIDTH, HEIGHT);

		theStage = myStage;
		BP = new BorderPane();
		SP = new ScrollPane();
		SP.setContent(initializeRightMenu());
		SP.setHbarPolicy(ScrollBarPolicy.NEVER);
		SP.setFitToWidth(true);

		inputView.setBackground(backgroundColorChooser, BP);
		BP.setRight(SP);
		TurtleView = initializeGraphicContent();
		inputView.setStroke(strokeColorChooser, gc);
		moveline(200,200);
		BP.setLeft(TurtleView);

		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
		theStage.setScene(theScene);
		theStage.show();
	}

	/**
	 * Initialize the right side which has all the controls for the GUI
	 * @return
	 */
	private VBox initializeRightMenu() {
		VBox RightMenu = new VBox(SPACING);
		
		help = new Button("Help!");
		help.setOnAction(e->{
			displayHelpPage();
		});
		Label backgroundLabel = new Label("Change the Background Color!"); //Change to ResourceBundle Later
		Label lineColorLabel = new Label ("Change the color of the line");

		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();	
		
		RightMenu.getChildren().addAll(inputView.initializeTextArea(), backgroundLabel, backgroundColorChooser, lineColorLabel, strokeColorChooser, help);
		return RightMenu;
	}
	
	/**
	 * Initialize the left size of the BorderPane (the Canvas) which displays the turtle movements
	 * @return TurtleView
	 */
	private Canvas initializeGraphicContent() {
		TurtleView = new Canvas (WIDTH*0.5, HEIGHT);
		gc = TurtleView.getGraphicsContext2D();
		return TurtleView;
	}
	
	/**
	 * Filler method just to test if the line is displayed properly
	 * NOT ACTUALLY GOING TO BE USED THIS WAY.
	 * @param x
	 * @param y
	 */
	private void moveline(double x, double y){
		gc.moveTo(50, 50);
		gc.lineTo(x, y);
		gc.stroke();
	}
	
	private void displayHelpPage(){
		try{
			Desktop.getDesktop().browse(new URI(helpUrl));
		}
		catch (IOException e){
			System.out.println("This is a filler notification for an error");
		}
		catch (URISyntaxException e){
			System.out.println("This is a filler notification for an error");
		}
	}
	
	@Override
	public void passString(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newTurtleState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createErrorMessage(String whatHappened) {
		// TODO Auto-generated method stub

	}

}