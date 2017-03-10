package visuals;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import processing.AlertDisplayer;
import processing.Controller;
import turtle.TurtleState;

/**
 * This is the class which controls the display of the GUI. It puts together all
 * the components that make up the screen.
 * 
 * @author Harry Liu, Christian Martindale
 **/

public class View extends AlertDisplayer {

	private BorderPane BP;
	private StackPane SP;
	private Scene theScene;
	private ResourceBundle myResourceBundle;
	private IInputView inputView;
	private ITurtleView turtleView;
	private IHistory myHistory;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private final int SPACING = 10;

	private Canvas turtleCanvas;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	private TextField penWidthBox;

	public View(File myImageFile, Button submit, Button clear, ResourceBundle myResourceBundle) {

		inputView = new InputView();
		turtleView = new TurtleView();
		myHistory = new History();
		
		this.myResourceBundle = myResourceBundle;
		turtleCanvas = turtleView.initializeGraphicContent(WIDTH, HEIGHT);
		
		SP = createStackPane(myImageFile);
		BP = createBorderPane(submit, clear);
		inputView.setBackground(backgroundColorChooser, BP);
		inputView.setStroke(strokeColorChooser, turtleCanvas.getGraphicsContext2D());

		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
	}
	
	/**
	 * Creates the <code>StackPane</code> that holds the turtle's image and its <code>Canvas</code>
	 * @param myImageFile Turtle image
	 * @return The <code>StackPane</code> that holds the turtle's image and its <code>Canvas</code>
	 */
	private StackPane createStackPane(File myImageFile){
		
		StackPane pane = new StackPane();
		pane.getChildren().addAll(turtleCanvas, turtleView.initializeTurtle(myImageFile));
		return pane;
	}
	
	/**
	 * Creates a <code>BorderPane</code> containing the submit and clear buttons
	 * @param submit Submit button
	 * @param clear Clear button
	 * @return A <code>BorderPane</code> containing the submit and clear buttons
	 */
	private BorderPane createBorderPane(Button submit, Button clear){
		
		BorderPane pane = new BorderPane();
		pane.setLeft(SP);
		pane.setRight(initializeControlTabs(submit, clear));
		pane.setTop(createMenu());	
		return pane;
	}
	
	/**
	 * Create the Tab-menu and set the content for the tabs
	 * @param submit
	 * @return Menu
	 */
	private TabPane initializeControlTabs(Button submit, Button clear){
		
		VBox rightMenu = initializeRightMenu(submit, clear);
		TurtleTabs menu = new TurtleTabs(submit, clear, myResourceBundle, rightMenu, (History)myHistory);
		return menu.getMyTabs();
	}

	/**
	 * Initialize the right side which has all the controls for the GUI
	 * @return a VBox with the Menu controls
	 */
	private VBox initializeRightMenu(Button submit, Button clear) {
		VBox RightMenu = new VBox(SPACING);

		Label backgroundLabel = new Label(myResourceBundle.getString("BackgroundColorPrompt"));
		Label lineColorLabel = new Label (myResourceBundle.getString("LineColorPrompt"));

		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();	

		penWidthBox = inputView.initializePenWidthController(myResourceBundle.getString("PenWidthPrompt"));
		penWidthBox.setOnKeyPressed(e ->{
			if(e.getCode() == KeyCode.ENTER){
				try{
					turtleCanvas.getGraphicsContext2D().setLineWidth(Double.parseDouble(penWidthBox.getText()));
				}
				catch(Exception fail){
					createErrorMessage(myResourceBundle.getString("ValidDoublePrompt"));
				}
			}
		});

		RightMenu.getChildren().addAll(inputView.initializeTextArea(submit, myResourceBundle), clear, penWidthBox, backgroundLabel, 
				backgroundColorChooser, lineColorLabel, strokeColorChooser);

		return RightMenu;
	}


	/**
	 * Clears the TurtleView screen (left side of the GUI)
	 * 
	 */
	public void clearScreen(){
		turtleCanvas.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
		turtleCanvas.getGraphicsContext2D().beginPath();

	}

	/**
	 * Create Menu located at the top of the BorderPane. Contains options for opening a new window, closing the program,
	 * and accessing the help page.
	 * @return a MenuBar with control buttons
	 */
	private MenuBar createMenu(){
		TurtleMenu menu = new TurtleMenu(myResourceBundle);
		MenuBar turtleMenuBar = menu.getMenu();
		return turtleMenuBar;
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

	public void updateTurtle(TurtleState newTurtle){
		turtleView.updateTurtle(newTurtle);
	}
	
	public History getMyHistory(){
		return (History)myHistory;
	}
	
	public Canvas getTurtleCanvas(){
		return turtleCanvas;
	}
}