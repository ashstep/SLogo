package processing;

import visuals.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.CommandParser;
import parser.Node;
import turtle.Command;
import turtle.Turtle;
import visuals.SplashPage;

/**
 * 
 * @author Vishnu Gottiparthy
 * The controller in SLogo command parsing. 
 * Sends user input to the command parser and relays
 * information between the turtle and the GUI.
 */

public class Controller extends ErrorDisplayer {
	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private String language = "English";
	private CommandParser parser;
	private Turtle turtle;
	private File myImageFile;
	private Button uploadImage;
	private String ImageName;
	private Alert alert;
	private SplashPage splash;
	
	public static final String DEFAULT_IMAGE = "src/images/turtle01.png";

	public Controller(Stage s){
		theStage = s;
		parser = new CommandParser();
		turtle = new Turtle();
		
		ImageName = DEFAULT_IMAGE; 
		myImageFile = new File(DEFAULT_IMAGE); 
		
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		buildSplashPage();
		
		theStage.setScene(splash.getScene());
		theStage.show();
	}
	
	/**
	 * Creates the <code>ComboBox</code> to choose languages based 
	 * on the list of languages in the properties file
	 */
	private ComboBox<String> buildComboBox(){
		try{
			BufferedReader fr = new BufferedReader(new FileReader(
					new File("src/resources/languages/Languages.properties")));
			List<String> myLanguages = fr.lines().collect(Collectors.toList());
			fr.close();
			ObservableList<String> languages = FXCollections.observableArrayList(myLanguages);	
			
			ComboBox<String> languageSelector = new ComboBox<String>(languages);
			languageSelector.valueProperty().addListener((obs, oVal, nVal) -> changeResourceBundle(nVal));
			languageSelector.setPromptText("Choose a Language");
			return languageSelector;
		}
		catch(Exception e){
			createErrorMessage("Cannot read languages list");
			return new ComboBox<String>();
		}
	}

	private void buildSplashPage(){
		ComboBox<String> languageSelector = buildComboBox();
		
		Button start = new Button(myResourceBundle.getString("StartPrompt"));
		start.setOnAction(event -> makeView());

		Button uploadImage = new Button(myResourceBundle.getString("UploadPrompt"));
		uploadImage.setOnAction(e->chooseImage());

		splash = new SplashPage(start, uploadImage, languageSelector);
	}
	
	/**
	 * Creates the main program view
	 */
	private void makeView(){
		
			Button submit = new Button(myResourceBundle.getString("SubmitPrompt"));
			submit.setMaxWidth(View.WIDTH / 2);
			System.out.println("reached here");

			//When history button is clicked, run its command automatically
			submit.setOnAction(e -> {
				submitActions();
			});		

			theView = new View(myImageFile, submit, myResourceBundle);
			theView.updateTurtle(turtle.getState());
			
			//When user clicks on Canvas, move turtle there
			theView.getTurtleCanvas().setOnMouseClicked(e ->{
				Double clickXCoord = e.getX();
				Double clickYCoord = e.getY();
				
				parseCommands("setxy" + " " + clickXCoord.toString() + " " + clickYCoord.toString());
			});
			
			
			theStage.setScene(theView.getScene());
		
		/*
		catch (Exception e){
			createErrorMessage("Please upload an file!");
		}
		*/
	}

	/**
	 * Sets the actions for the submit button in the command entry view
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
			createErrorMessage("Please input a command before pressing submit.");
		}
		
	}

	/**
	 * Summons a window for the user to select a image file for the turtle
	 */
	public void chooseImage(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a turtleImage");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
				"*.png", "*.jpg"));
		myImageFile= fileChooser.showOpenDialog(theStage);
		
			ImageName = myImageFile.toURI().toString();
			alert = new Alert(AlertType.INFORMATION, "You have selected the image above for this simulation");
			ImageView myTurtle = new ImageView(new Image(ImageName));
			alert.setGraphic(myTurtle);
			alert.show();
		
		/*
		catch (Exception e){
			alert = new Alert(AlertType.ERROR, "Please select an image!");
			alert.showAndWait();
		}
		*/
	}

	/**
	 * Changes the resource bundle to the newly selected langugage
	 * @param newLanguage
	 */
	private void changeResourceBundle(String newLanguage){
		language = newLanguage;
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}

	/**
	 * Sends a raw <code>String</code> to the <code>CommandParser</code> for parsing
	 * @param cmd Raw command <code>String</code>
	 */
	private void parseCommands(String cmd){
		//old test
		//parser.parseInputtedCommand(cmd);
		//Node node = parser.buildTree();

		//new:
		System.out.println("command to be parsed: " +cmd);
		Node starting = parser.initTreeRecurse(parser.treeTwoParseCommand(cmd));
		Command command = starting.getCommandObject();

		System.out.println("Turtle is at " + turtle.getState().getX() + ", " + turtle.getState().getY());

		//old test
		//Command command = node.getCommandObject();

		try {
			//old
			//command.treeArgs(node);
			//turtle.process(command);
			command.treeArgs(starting);
			turtle.process(command);

		} catch (Exception e) {
			theView.createErrorMessage(e.getClass().toString());
		}

		theView.updateTurtle(turtle.getState());
		System.out.println("Turtle is at " + turtle.getState().getX() + ", " + turtle.getState().getY());
		//parser.resetCommand(cmd);
	}
}