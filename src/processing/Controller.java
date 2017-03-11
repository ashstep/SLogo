package processing;

import visuals.View;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import command.ArgumentNumberException;
import command.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import parser.Node;
import parser.ParserHelper;
import turtle.Turtle;
import turtle.TurtleState;
import visuals.SplashPage;
import visuals.TurtleView;

/**
 * 
 * @author Vishnu Gottiparthy
 * The controller in SLogo command parsing. 
 * Sends user input to the command parser and relays
 * information between the turtle and the GUI.
 */

public class Controller extends AlertDisplayer {

	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private String language = "English";
	private static Turtle turtle;
	private File myImageFile;
	private SplashPage splash;
	private ParserHelper parserhelper;
	
	private String DEFAULT_LANGUAGE_FILE = "src/resources/languages/Languages.properties";
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private static final String DEFAULT_IMAGE = "src/images/turtle_default.png";

	public Controller(Stage s){
		theStage = s;
		turtle = new Turtle();
		parserhelper = new ParserHelper();

		myImageFile = new File(DEFAULT_IMAGE); 

		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		buildSplashPage();

		theStage.setScene(splash.getScene());
		theStage.show();
	}

	/**
	 * Creates the initial splash page for the program upon launch.
	 * Contains a turtle image selector and the run program button.
	 */
	private void buildSplashPage(){
		ComboBox<String> languageSelector = buildComboBox();

		Button start = new Button(myResourceBundle.getString("StartPrompt"));
		start.setOnAction(event -> makeView());

		Button uploadImage = new Button(myResourceBundle.getString("UploadPrompt"));
		uploadImage.setOnAction(e->chooseImage());

		splash = new SplashPage(start, uploadImage, languageSelector);
	}
	
	/**
	 * Creates the <code>ComboBox</code> to choose languages based 
	 * on the list of languages in the properties file
	 */
	private ComboBox<String> buildComboBox(){
		
		try{
			//File IO - read list of languages
			BufferedReader fr = new BufferedReader(new FileReader(
					new File(DEFAULT_LANGUAGE_FILE)));
			List<String> myLanguages = fr.lines().collect(Collectors.toList());
			fr.close();
			ObservableList<String> languages = FXCollections.observableArrayList(myLanguages);	

			//Create ComboBox
			ComboBox<String> languageSelector = new ComboBox<String>(languages);
			languageSelector.valueProperty().addListener((obs, oVal, nVal) -> changeResourceBundle(nVal));
			languageSelector.setPromptText(myResourceBundle.getString("ChooseLanguagePrompt"));
			return languageSelector;
		}
		catch(Exception e){
			createErrorMessage(myResourceBundle.getString("LanguageErrorPrompt"));
			return new ComboBox<String>();
		}
	}
	
	/**
	 * Changes the resource bundle to the newly selected language
	 * @param newLanguage the language to switch to 
	 */
	private void changeResourceBundle(String newLanguage){
		language = newLanguage;
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}


	/**
	 * Creates the main turtle program view.
	 */
	private void makeView(){
		
			Button submit = new Button(myResourceBundle.getString("SubmitPrompt"));
			submit.setMaxWidth(View.WIDTH / 2);

			//When history button is clicked, run its command automatically
			submit.setOnAction(e -> {
				submitActions();
			});		

			Button clearScreen = new Button (myResourceBundle.getString("Clear"));
			clearScreen.setOnAction(e->{
				theView.clearScreen();
				parseCommands("home");
			});
			
			theView = new View(myImageFile, submit, clearScreen, myResourceBundle);
			
			//When user clicks on Canvas, move turtle there
			theView.getTurtleCanvas().setOnMouseClicked(e -> {
				clickActions(e);
			});
			
			theStage.setScene(theView.getScene());
	}

	/**
	 * Sets the actions for the submit command button in the input view. 
	 */
	private void submitActions(){
		try{
			theView.getMyHistory().updateHistory(theView.getCommandString());
			for(Button b:theView.getMyHistory().getMyButtons()){
				b.setOnAction(q -> parseCommands(b.getText()));
			}
			parseCommands(theView.getCommandString());
		}
		catch(Exception e){
			createErrorMessage(myResourceBundle.getString("ErrorPrompt"));
		}
	}

	/**
	 * Handle moving the turtle to mouse click location
	 * @param e Click event
	 */
	private void clickActions(MouseEvent e){
		Double clickXCoord = e.getX();
		Double clickYCoord = e.getY();
		System.out.println("Clicked " + clickXCoord + ", " + clickYCoord);
		parseCommands("setxy" + " " + (clickXCoord - TurtleView.WIDTH/2)
				+ " " + (TurtleView.HEIGHT/2 - clickYCoord));
	}
	
	/**
	 * Summons a window for the user to select a image file for the turtle
	 */
	private void chooseImage(){
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(myResourceBundle.getString("SelectPrompt"));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
				"*.png", "*.jpg"));
		myImageFile= fileChooser.showOpenDialog(theStage);
		
		try{
			String ImageName = myImageFile.toURI().toString();
			Alert alert = createInformationMessage(myResourceBundle.getString("ImagePrompt"));
			ImageView myTurtle = new ImageView(new Image(ImageName));
			alert.setGraphic(myTurtle);
			alert.show();
		}

		catch (Exception e){
			Alert alert = new Alert(AlertType.ERROR, myResourceBundle.getString("SelectPrompt"));
			alert.showAndWait();
		}
	}
	
	/**
	 * Sends a raw <code>String</code> to the <code>CommandParser</code> for parsing
	 * @param cmd Raw command <code>String</code> input by the user
	 */
	private void parseCommands(String cmd){
		try {
			parserhelper.parseCommand(cmd, language);
			for(Node each : parserhelper.getFinalArrayList()){
				System.out.println("current command **** " + each.getCommand());

				Command command = each.getCommandObject();
				for(int i = 0; i < command.getNumArgs(); i++ ){
					
					System.out.println("adding arguments for children : " + each.getSpecificChild(i).getCommand());
					command.addArg(Double.parseDouble(each.getSpecificChild(i).getCommand()));
				}
				command.treeArgs(each);
				turtle.process(command);
				theView.updateTurtle(turtle.getState());
				 System.out.println("Turtle is at " + turtle.getState().getX() + ", " + 
						turtle.getState().getY() + " heading at " + turtle.getState().getAngle());
			}
			parserhelper.getFinalArrayList().clear();
		} 
		catch (ArgumentNumberException e) {
			createErrorMessage(myResourceBundle.getString("InvalidNumPrompt"));
		}
	}

	public static TurtleState getTurtleState(){
		return turtle.getState();
	}
}