package processing;

import visuals.View;

import java.io.File;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.CommandParser;
import parser.Node;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.CommandProcessException;
import turtle.Turtle;
import visuals.SplashPage;

public class Controller {
	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private String language = "English";
	private ComboBox<String> languageSelector;
	private CommandParser parser;
	private Turtle turtle;
	private File myImageFile;
	private Button uploadImage;
	
	public Controller(Stage s){
		theStage = s;
		parser = new CommandParser();
		turtle = new Turtle();
		
		String[] myLanguages = new String[] { "English", "Spanish", "Chinese" };
		ObservableList<String> languages = FXCollections.observableArrayList(myLanguages);	
		languageSelector = new ComboBox<String>(languages);
		languageSelector.valueProperty().addListener((obs, oVal, nVal) -> changeResourceBundle(nVal));
		languageSelector.setPromptText("Choose a Language");

		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		
		Button start = new Button(myResourceBundle.getString("StartPrompt"));
		start.setOnAction(event -> makeView());
		
		uploadImage = new Button(myResourceBundle.getString("UploadPrompt"));
		uploadImage.setOnAction(e->chooseImage());
		
		SplashPage splash = new SplashPage(start, uploadImage, languageSelector);
		
		theStage.setScene(splash.getScene());
		theStage.show();
	}
	
	private void makeView(){
		try{
		Button submit = new Button(myResourceBundle.getString("SubmitPrompt"));
		submit.setMaxWidth(View.WIDTH / 2);
		System.out.println("reached here");
		submit.setOnAction(e -> submitActions());
		
		theView = new View(myImageFile, submit, myResourceBundle);
		theView.updateTurtle(turtle.getState());
		theStage.setScene(theView.getScene());
		}
		catch (Exception e){
			Alert alert = new Alert(AlertType.ERROR, "Please upload a file!");
			alert.showAndWait();
		}
	}
	
	private void submitActions(){
		theView.inputView.getMyHistory().updateHistory(theView.getCommandString());
		parseCommands(theView.getCommandString());
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
	}
	
	/**
	 * Changes the resource bundle to the newly selected langugage
	 * @param newLanguage
	 */
	private void changeResourceBundle(String newLanguage){
		language = newLanguage;
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}

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
			
		} catch (CommandProcessException e) {
			e.printStackTrace();
		} catch (ArgumentNumberException e) {
			e.printStackTrace();
		}
		
		theView.updateTurtle(turtle.getState());
		System.out.println("Turtle is at " + turtle.getState().getX() + ", " + turtle.getState().getY());
		//parser.resetCommand(cmd);
	}
}
