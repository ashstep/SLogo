package visuals;
import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import processing.Controller;
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
	private StackPane stack;
	private Scene theScene;
	private ResourceBundle myResourceBundle;
	private Button clearScreen;
	public InputView inputView;
	public ITurtleView turtleView;
	private History myHistory;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private final int SPACING = 10;
	private String helpUrl = "http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php";
	
	private Canvas TurtleView;
	private GraphicsContext gc;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	
	public View(File myImageFile, Button submit, ResourceBundle myResourceBundle) {
		System.out.println("start view");

		inputView = new InputView();
		turtleView = new TurtleView();
		this.myResourceBundle = myResourceBundle;
		
		//System.out.println("reached here 3");
		
		BP = new BorderPane();

		BP.setRight(initializeControlTabs(submit));
		inputView.setBackground(backgroundColorChooser, BP);
		
		BP.setTop(createMenu());
		
		//moved up
		stack = new StackPane();
		//System.out.println("stack-child is " + stack.getChildren());
		System.out.println("reached here 4");
		System.out.println("stack is " + stack);
		
		TurtleView = turtleView.initializeGraphicContent();
		inputView.setStroke(strokeColorChooser, gc);
		stack.getChildren().addAll(TurtleView, turtleView.initializeTurtle(myImageFile));
		
		System.out.println("stackchild is " + stack.getChildren());	
		System.out.println("stack is INITIAL"+ stack);
		
		BP.setLeft(stack);

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
		
		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();	
		
		RightMenu.getChildren().addAll(inputView.initializeTextArea(submit, myResourceBundle), backgroundLabel, backgroundColorChooser, lineColorLabel, strokeColorChooser);
		return RightMenu;
	}
	
	private TabPane initializeControlTabs(Button submit){
		
		TabPane Menu = new TabPane();
		Tab controlTab = new Tab();
		controlTab.setText("Controls");
		controlTab.setContent(initializeRightMenu(submit));
		Tab historyTab = new Tab();
		historyTab.setText("History");
		myHistory = new History();
		historyTab.setContent(myHistory.getMyContents());
		Menu.getTabs().addAll(controlTab,historyTab);
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
		try{
			Desktop.getDesktop().browse(new URI(helpUrl));
		}
		catch (IOException e){
			createErrorMessage(myResourceBundle.getString("NotificationError"));
		}
		catch (URISyntaxException e){
			createErrorMessage(myResourceBundle.getString("NotificationError"));
		}
	}
	
	public void updateTurtle(TurtleState newTurtle){
		turtleView.updateTurtle(newTurtle);
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
	
	public History getMyHistory(){
		return myHistory;
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