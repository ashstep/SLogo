package visuals;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
import processing.Controller;
import turtle.TurtleState;
import processing.ErrorDisplayer;

/**
 * This is the class which controls the display of the GUI. It puts together all
 * the components that make up the screen.
 * 
 * @author Harry Liu, Christian Martindale
 **/

public class View extends ErrorDisplayer {

	private BorderPane BP;
	private StackPane SP;
	private Scene theScene;
	private ResourceBundle myResourceBundle;
	private Button clearScreen;
	private IInputView inputView;
	private ITurtleView turtleView;
	private History myHistory;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int CANVAS_WIDTH = 400;
	public static final int CANVAS_HEIGHT = 300;
	private final int SPACING = 10;

	private Canvas turtleCanvas;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	private TextField penWidthBox;

	public View(File myImageFile, Button submit, ResourceBundle myResourceBundle) {

		inputView = new InputView();
		turtleView = new TurtleView();
		SP = new StackPane();
		BP = new BorderPane();
		this.myResourceBundle = myResourceBundle;

		turtleCanvas = turtleView.initializeGraphicContent();
		SP.getChildren().addAll(turtleCanvas, turtleView.initializeTurtle(myImageFile));

		BP.setLeft(SP);
		BP.setRight(initializeControlTabs(submit));
		BP.setTop(createMenu());	
		inputView.setBackground(backgroundColorChooser, BP);
		inputView.setStroke(strokeColorChooser, turtleCanvas.getGraphicsContext2D());

		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
	}

	/**
	 * Initialize the right side which has all the controls for the GUI
	 * @return
	 */
	private VBox initializeRightMenu(Button submit) {
		VBox RightMenu = new VBox(SPACING);

		Label backgroundLabel = new Label(myResourceBundle.getString("BackgroundColorPrompt"));
		Label lineColorLabel = new Label (myResourceBundle.getString("LineColorPrompt"));

		clearScreen = new Button (myResourceBundle.getString("Clear"));
		clearScreen.setOnAction(e->{
			inputView.clearScreen(turtleCanvas, WIDTH, HEIGHT);;
		});

		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();	

		penWidthBox = inputView.initializePenWidthController(myResourceBundle.getString("PenWidthPrompt"));
		penWidthBox.setOnKeyPressed(e ->{
			if(e.getCode() == KeyCode.ENTER){
				try{
					turtleCanvas.getGraphicsContext2D().setLineWidth(Double.parseDouble(penWidthBox.getText()));
				}
				catch(Exception fail){
					createErrorMessage("Please input a valid double.");
				}
			}
		});

		RightMenu.getChildren().addAll(inputView.initializeTextArea(submit, myResourceBundle), clearScreen, penWidthBox, backgroundLabel, 
				backgroundColorChooser, lineColorLabel, strokeColorChooser);
		RightMenu.setAlignment(Pos.CENTER);
		return RightMenu;
	}

	/**
	 * Create the Tab-menu and set the content for the tabs
	 * @param submit
	 * @return Menu
	 */
	private TabPane initializeControlTabs(Button submit){

		TabPane Menu = new TabPane();
		Tab controlTab = new Tab();
		controlTab.setText("Controls");
		controlTab.setContent(initializeRightMenu(submit));

		Tab historyTab = new Tab();
		historyTab.setText("History");

		myHistory = new History();
		historyTab.setContent(myHistory.getMyContents());

		Menu.getTabs().addAll(controlTab , historyTab);
		Menu.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		return Menu;
	}

	/**
	 * Create Menu located at the top of the BorderPane. Contains options for opening a new window, closing the program,
	 * and accessing the help page.
	 * @return MenuBar
	 */
	private MenuBar createMenu(){
		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu(myResourceBundle.getString("FilePrompt"));

		MenuItem menuItemExit = new MenuItem(myResourceBundle.getString("ExitPrompt"));
		menuItemExit.setOnAction(e ->{
			System.exit(0);
		});

		MenuItem menuItemNew = new MenuItem(myResourceBundle.getString("NewPrompt"));
		menuItemNew.setOnAction(e ->{
			Stage newStage = new Stage();
			new Controller(newStage);
		});

		Menu menuHelp = new Menu (myResourceBundle.getString("HelpPrompt"));

		MenuItem menuItemHelp = new MenuItem(myResourceBundle.getString("DocumentationPrompt"));
		menuItemHelp.setOnAction(e -> {
			displayHelpPage();
		});

		menuFile.getItems().addAll(menuItemExit, menuItemNew);
		menuHelp.getItems().add(menuItemHelp);
		menuBar.getMenus().addAll(menuFile, menuHelp);

		return menuBar;
	}

	/**
	 * Opens up the help page in a web browser. If there are errors, display error message
	 */
	private void displayHelpPage(){
		URL url = getClass().getResource("help.html");
		try {
			Desktop.getDesktop().browse(url.toURI());
		} catch (IOException e) {
		} catch (URISyntaxException e) {
		}
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
		return myHistory;
	}

	public Canvas getTurtleCanvas(){
		return turtleCanvas;
	}
}